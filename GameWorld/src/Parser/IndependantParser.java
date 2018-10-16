package Parser;

import GameWorld.Book;
import GameWorld.Key;
import GameWorld.Door;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
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
import GameWorld.Player.Direction;

import javax.xml.parsers.*;
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
    
    // determine the Direction (set to North as default)
    Player.Direction dir = Direction.NORTH;
    String dirStr = direction.getTextContent();
    if (dirStr.equals("SOUTH")) {dir = Direction.SOUTH;}
    else if (dirStr.equals("EAST")) {dir = Direction.EAST;}
    else if (dirStr.equals("WEST")) {dir = Direction.WEST;}
    
    return new Door(isLocked, mapVal, id, linkVal, doorPos, linkPos, dir);
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








}





















