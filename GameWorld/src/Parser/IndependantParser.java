package Parser;

import GameWorld.Book;
import GameWorld.Key;
import GameWorld.Door;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import GameWorld.Game;
import GameWorld.Item;
import GameWorld.Map;
import GameWorld.Player;
import GameWorld.Position;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * A version of the Parser that uses javax.xml instead of the external jdom2 library
 * @author Callum
 *
 */
public class IndependantParser {
  
  private List<Map> mapObjects = new ArrayList<>();
  private List<Door> doorObjects = new ArrayList<>();
  private static final int MAP_GRIDSIZE = 5;
  
  /**
   * Create a Game object from an XML save file
   * @param file
   * @return
   */
  public Game loadFromFile (File file) {
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(file);
      
      Element rootNode =  (Element) doc.getDocumentElement();
      Node playerNode = rootNode.getElementsByTagName("Player").item(0);
      NodeList maps = rootNode.getElementsByTagName("Map");
      NodeList doors = rootNode.getElementsByTagName("Door");
      
      // fills list of Map objects
      for (int i = 0; i < maps.getLength(); i++) {
        mapObjects.add(parseMap(maps.item(i)));
      }
      
      // fills list of Door objects
      for (int i = 0; i < doors.getLength(); i++) {
        doorObjects.add(parseDoor(doors.item(i)));
      }
      
      // creates the player object
      Player player = parsePlayer(playerNode);
      
      // uses alternate constructor for Game
      Game game = new Game(mapObjects, doorObjects, player);
      return game;
      
    } 
    catch (Exception e) {e.printStackTrace();}
    return null;
  }

  /**
   * Logic for parsing the Player (used by loadFromFile)
   * @param player
   * @return
   */
  private Player parsePlayer (Node player) {
      Element el = (Element) player;
      
      int startMap = Integer.parseInt(el.getAttribute("StartMap"));
      Position pos = parseFindPosition(el.getElementsByTagName("FindPosition").item(0));
      
      Player p = new Player(startMap, pos);
      
      // fill the player's inventory
      NodeList inventory = ((Element) el.getElementsByTagName("Inventory").item(0)).getElementsByTagName("Item");
      for (int i = 0; i < inventory.getLength(); i++) {
        p.addItem(parseItem(inventory.item(i)));
      }
      return p;
  }
  
  /**
   * Logic for parsing a Map (used by loadFromFile)
   * @param map
   * @return
   */
  private Map parseMap (Node map) {
    Position[][] grid = new Position[MAP_GRIDSIZE][MAP_GRIDSIZE];
    Element el = (Element) map;
    NodeList positions = el.getElementsByTagName("Position");
    int listCount = 0; // used to keep track of which child we are up to
    
    // positions are placed into grid row by row - therefore it is very important
    // that the positions are in the right order
    for (int row = 0; row < MAP_GRIDSIZE; row++) {
      for (int col = 0; col < MAP_GRIDSIZE; col++) {
        Position pos = parsePosition(positions.item(listCount)); 
        grid[row][col] = pos;
        listCount++; // listCount iterated
      }
    }
    return new Map(grid);
  }
  
  private Position parsePosition (Node position) {
    Element el = (Element) position;
    Node xVal = el.getElementsByTagName("xVal").item(0);
    Node yVal = el.getElementsByTagName("yVal").item(0);
    
    // get x and y values
    int x = Integer.parseInt(xVal.getTextContent());
    int y = Integer.parseInt(yVal.getTextContent());
    
    // returns null if x or y are less than 0, indicating the position is
    // innaccessible for the player
    if (x < 0 || y < 0) {
      return null;
    }
    
    // otherwise create the position, add its item and return it
    Position pos = new Position(x, y);
    Item item = parseItem(el.getElementsByTagName("Item").item(0));
    pos.addItem(item);
    return pos;
  }
  
  /**
   * Finds a Position in the maps, based on the map, x and y value (used by loadFromFile)
   * @param findPosition
   * @return
   */
  private Position parseFindPosition (Node findPosition) {
    Element el = (Element) findPosition;
    int mp = Integer.parseInt(el.getElementsByTagName("Map").item(0).getTextContent());
    
    // check if the element points to a null position
    if (mp < 0) {return null;}
    
    // otherwise, get the x and y values of the position
    Node xVal = el.getElementsByTagName("xVal").item(0);
    Node yVal = el.getElementsByTagName("yVal").item(0);
    int x = Integer.parseInt(xVal.getTextContent());
    int y = Integer.parseInt(yVal.getTextContent());
    
    // get the correct map as a 2D array of Positions
    Position[][] currentMap = mapObjects.get(mp).getMap();

    // iterate over the map; looking for a Position with the same coordinates as xVal and yVal
    // and returning it
    for (int row = 0; row < MAP_GRIDSIZE; row++) {
      for (int col = 0; col < MAP_GRIDSIZE; col++) {
        Position p = currentMap[row][col];
        if (p != null) {           
          if (p.getx() == x && p.gety() == y) {return p;}
        }
      }
    }
    
    // returns null if we can't find the position 
    return null;
  }
  
  /**
   * Logic for parsing an Item (used by loadFromFile)
   * @param item
   * @return
   */
  private Item parseItem (Node item) {
    Element el = (Element) item;
    String type = el.getAttribute("Type");
    
    // returns null early if the Type of the item is 'Empty' (meaning the child elements do not exist)
    if (type.equals("Empty")) {return null;}
    
    // extract the children
    Node weight = el.getElementsByTagName("Weight").item(0);
    Node ID = el.getElementsByTagName("ID").item(0);
    Node description = el.getElementsByTagName("Description").item(0);
    Node title = el.getElementsByTagName("Title").item(0);
    Node map = el.getElementsByTagName("Map").item(0);
    Node icon = el.getElementsByTagName("Icon").item(0);
    
    // create shared parameter objects
    int w = Integer.parseInt(weight.getTextContent());
    int id = Integer.parseInt(ID.getTextContent());
    String descr = description.getTextContent();
    String ttl = title.getTextContent();
    Integer mp = Integer.getInteger(map.getTextContent());
    Icon i = parseIcon(icon);
    
    // Implementation specific parsing
    if (type.equals("Key")) {
      Node door = el.getElementsByTagName("DoorID").item(0);
      int doorID = Integer.parseInt(door.getTextContent());
      return new Key(w, id, descr, ttl, mp, doorID, i);
    }
    if (type.equals("Book")) {
      String contents = el.getElementsByTagName("Contents").item(0).getTextContent();
      boolean isMagical = Boolean.valueOf(el.getElementsByTagName("Magical").item(0).getTextContent());
      return new Book(w, id, descr, ttl, mp, isMagical, contents, i);
    }
    
    // shouldn't get to here
    return null;
  }
  
  /**
   * Logic for parsing a Door (used by loadFromFile)
   * @param door
   * @return
   */
  private Door parseDoor (Node door) {
    Element el = (Element) door;
    
    Node locked = el.getElementsByTagName("Locked").item(0);
    Node map = el.getElementsByTagName("Map").item(0);
    Node ID = el.getElementsByTagName("ID").item(0);
    Node link = el.getElementsByTagName("Link").item(0);
    Node doorPosition = el.getElementsByTagName("DoorPosition").item(0);
    Node linkPosition = el.getElementsByTagName("LinkPosition").item(0);
    Node direction = el.getElementsByTagName("Direction").item(0);
    
    boolean isLocked = Boolean.valueOf(locked.getTextContent());
    Integer mapVal = Integer.getInteger(map.getTextContent());
    int id = Integer.parseInt(ID.getTextContent());
    Integer linkVal = Integer.getInteger(link.getTextContent());
    Position doorPos = parseFindPosition(doorPosition);
    Position linkPos = parseFindPosition(linkPosition);
    
    return new Door(isLocked, mapVal, id, linkVal, doorPos, linkPos);
  }
  
  /**
   * Logic for parsing an Icon (filename of the image used is saved in xml file)
   * used by loadFromFile
   * @param icon
   * @return
   */
  private Icon parseIcon (Node icon) {
    String filename = icon.getTextContent();
    return new ImageIcon(filename);
  }


  
///////////////////////////////////SAVING/////////////////////////////////////////

  
  /**
   * Save the current game state (maps, doors and the player) in an XML file
   * @param mapHash HashMap of all Maps (passed in this format to match the rest of the program) however here it is immediately converted to a List
   * @param doorHash HashMap of all Doors (passed in this format to match the rest of the program) however here it is immediately converted to a List
   * @param player object representing the player
   * @param file file where the data is to be saved to
   */
  public void saveToFile (HashMap<Integer, Map> mapHash, HashMap<Integer, Door> doorHash, Player player, File file) {
    // change HashMaps to Collections for easier use
    Collection<Map> maps = mapHash.values();
    Collection<Door> doors = doorHash.values();
    
    try {  
      // create document
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.newDocument();
      
      // create root element
      Element game = doc.createElement("Game");
      doc.appendChild(game);
      
      // create map, player, door elements
      for (Map m : maps) {game.appendChild(saveMap(m, doc));}
      game.appendChild(savePlayer(player, doc));
      for (Door d : doors) {game.appendChild(saveDoor(d, doc));}
      
      // save the document to an XML file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(file);
      transformer.transform(source, result);
    }
    catch (Exception e) {e.printStackTrace();}
    
  }
  
  /**
   * saves a Player object as an XML element (used by saveToFile)
   * @param p
   * @param doc
   * @return
   */
  private Element savePlayer (Player p, Document doc) {
    Element player = doc.createElement("Player");
    player.setAttribute("StartMap", p.currentMapInteger().toString());
    
    // save position and inventory, then return
    player.appendChild(saveFindPosition(p.getPosition(), p.currentMapInteger(), "FindPosition", doc));
    player.appendChild(saveInventory(p.getInventory(), doc));
    return player;
    }
  
  /**
   * saves the player's inventory as an XML element (used by saveToFile)
   * @param items
   * @param doc
   * @return
   */
  private Element saveInventory (List<Item> items, Document doc) {
    Element inventory = doc.createElement("Inventory");
    // saves the Items in the inventory
    for (Item i : items) {inventory.appendChild(saveItem(i, doc));}
    return inventory;
  }
  
  /**
   * saves a Map object as an XML element (used by saveToFile)
   * @param m
   * @param doc
   * @return
   */
  private Element saveMap (Map m, Document doc) {
    Element map = doc.createElement("Map");
    
    // save each Position in the map
    for (int row = 0; row < MAP_GRIDSIZE; row++) {
      for (int col = 0; col < MAP_GRIDSIZE; col++) {
        map.appendChild(savePosition(m.getMap()[row][col], doc));
      }
    }
    return map;
    }
  
  /**
   * Saves a Position object as an XML element (used by saveToFile)
   * @param p if p is null, the position's xVal and yVal are saved as -1
   * @param doc
   * @return
   */
  private Element savePosition (Position p, Document doc) {
    Element position = doc.createElement("Position");
    
    if (p != null) {
      // save x and y values
      Element xVal = doc.createElement("xVal");
      Element yVal = doc.createElement("yVal");
      xVal.appendChild(doc.createTextNode(Integer.toString(p.getx())));
      yVal.appendChild(doc.createTextNode(Integer.toString(p.gety())));
      position.appendChild(xVal);
      position.appendChild(yVal);
      
      // save item
      position.appendChild(saveItem(p.getItem(), doc));
    }
    // only x and y value are saved here, both set at -1
    else {
      Element xVal = doc.createElement("xVal");
      Element yVal = doc.createElement("yVal");
      xVal.appendChild(doc.createTextNode("-1"));
      yVal.appendChild(doc.createTextNode("-1"));
      position.appendChild(xVal);
      position.appendChild(yVal);
    }
 
    return position;
  }
  
  /**
   * Another way of saving a Position; used to save the position of Doors and the player. It is essentially a reference to a Position saved in a given map
   * @param p the position this element is pointing to
   * @param map the map where the position is located
   * @param title the title for this type of expression changes depending on where it is being called from, hence the parameter.
   * @param doc
   * @return an Element object
   */
  private Element saveFindPosition (Position p, int map, String title, Document doc) {
    Element findPosition = doc.createElement(title);
    
    // if position is null; save by representing the Map as -1 and returning early
    if (p == null) {
      findPosition.appendChild(doc.createElement("Map").appendChild(doc.createTextNode("-1")));
      return findPosition;
    }
    
    // otherwise, add appropriate elements to store map, x and y values before returning
    findPosition.appendChild(doc.createElement("Map").appendChild(doc.createTextNode(Integer.toString(map - 1))));
    findPosition.appendChild(doc.createElement("xVal").appendChild(doc.createTextNode(Integer.toString(p.getx()))));
    findPosition.appendChild(doc.createElement("yVal").appendChild(doc.createTextNode(Integer.toString(p.gety()))));
    return findPosition;
  }
  
  /**
   * Saves an Item object as an XML element
   * @param i
   * @param doc
   * @return
   */
  private Element saveItem (Item i, Document doc) {
    Element item = doc.createElement("Item");
    
    // if i is null, set the type and return early
    if (i == null) {
      item.setAttribute("Type", "Empty");
      return item;
    }
    
    // if i is a Key, set the type and add "DoorID" child
    if (i instanceof Key) {
      item.setAttribute("Type", "Key");
      Key k = (Key) i;
      item.appendChild(doc.createElement("DoorID").appendChild(doc.createTextNode(Integer.toString(k.getDoor()))));
    }
    
    // if i is a a Book, set the type and add "Contents" and "Magical" children
    if (i instanceof Book) {
      item.setAttribute("Type", "Book");
      Book b = (Book) i;
      item.appendChild(doc.createElement("Contents").appendChild(doc.createTextNode(b.read())));
      item.appendChild(doc.createElement("Magical").appendChild(doc.createTextNode(Boolean.toString(b.isMagical()))));
    }
    
    
    
    // add generic parameters and return
   item.appendChild(doc.createElement("Weight").appendChild(doc.createTextNode(Integer.toString(i.getWeight()))));
   item.appendChild(doc.createElement("ID").appendChild(doc.createTextNode(Integer.toString(i.getItemID()))));
   item.appendChild(doc.createElement("Description").appendChild(doc.createTextNode(i.getDescription())));
   item.appendChild(doc.createElement("Title").appendChild(doc.createTextNode(i.toString())));
   item.appendChild(doc.createElement("Map").appendChild(doc.createTextNode(Integer.toString(i.currentMap() - 1))));
   item.appendChild(saveIcon(i, doc));
    return item;
    }
  
  /**
   * Saves a Door object as an XML element
   * @param d
   * @param doc
   * @return
   */
  private Element saveDoor (Door d, Document doc) {
    Element door = doc.createElement("Door");
    
    // add parameters and return
    door.appendChild(doc.createElement("Locked").appendChild(doc.createTextNode(Boolean.toString(d.isLocked()))));
    door.appendChild(doc.createElement("Map").appendChild(doc.createTextNode(Integer.toString(d.getMap()))));
    door.appendChild(doc.createElement("ID").appendChild(doc.createTextNode(Integer.toString(d.getID()))));
    door.appendChild(doc.createElement("Link").appendChild(doc.createTextNode(Integer.toString(d.getLink()))));
    door.appendChild(saveFindPosition(d.getDoorPosition(), d.getMap(), "DoorPosition", doc));
    door.appendChild(saveFindPosition(d.getLinkPosition(), d.getLink(), "LinkPosition", doc));
    return door;
  }
  
  /**
   * Saves the file path for an icon
   * @param i
   * @param doc
   * @return
   */
  private Element saveIcon (Item i, Document doc) {
    Element icon = doc.createElement("Icon");
    
    if (i instanceof Key) {
      icon.appendChild(doc.createTextNode("/Resources/key.gif"));
    }
    if (i instanceof Book) {
      icon.appendChild(doc.createTextNode("/Resources/scroll.gif"));
    }
    return icon;
    }
}
