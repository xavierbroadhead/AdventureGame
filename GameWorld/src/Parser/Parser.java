package Parser;

import GameWorld.Book;
import GameWorld.Key;
import GameWorld.Door;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import GameWorld.Game;
import GameWorld.Item;
import GameWorld.Map;
import GameWorld.Player;
import GameWorld.Position;
import GameWorld.Player.Direction;

/**
 * Refer to Parser > savegameformat.txt for a detailed look at the XML hierarchy
 */

public class Parser {

  private List<Map> mapObjects = new ArrayList<>();
  private List<Door> doorObjects = new ArrayList<>();

  /**
   * Create a Game object from an xml save file
   * (call this to load from a file)
   * TODO: May need to add Furniture functionality
   * @param fname
   *          filename (obtained from a file chooser of some sort)
   * @return
   */
  public Game loadFromFile(String fname) {
    File file = new File(fname);
    SAXBuilder sBuilder = new SAXBuilder();

    try {
      Document document = sBuilder.build(file);
      Element gameElement = document.getRootElement();
      Element playerElement = gameElement.getChild("Player");
      List<Element> maps = gameElement.getChildren("Map");
      List<Element> doors = gameElement.getChildren("Door");

      // Fills List of Map objects. Currently these have nowhere to go and
      // just sit in the list. Later these will be passed to the game object
      // It is important that this step is done first
      for (Element mp : maps) {
        mapObjects.add(parseMap(mp));
      }

      // Fills list of Door objects
      for (Element door : doors) {
        doorObjects.add(parseDoor(door));
      }

      // calls parsePlayer, creating the player object
      Player player = parsePlayer(playerElement);

      Game game = new Game(player);
      // TODO: need to construct a way of adding mapObjects and doorObjects to game (will involve a fair
      // amount of coding in Game class)
      return game;

    } catch (JDOMException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Logic for parsing the Player (used by loadFromFile)
   * @param player
   * @return player object generated from file
   * @throws DataConversionException
   */
  private Player parsePlayer(Element player) throws DataConversionException {

    int startMap = player.getAttribute("StartMap").getIntValue();
    Position pos = parseFindPosition(player.getChild("FindPosition"));

    Player p = new Player(startMap, pos);

    // fill the player's inventory
    Element inventory = player.getChild("Inventory");
    List<Element> items = inventory.getChildren("Item");
    for (Element i : items) {
      p.addItem(parseItem(i));
    }

    return p;
  }

  /**
   * Logic for parsing a Map (used by loadFromFile)
   * @param map
   * @return
   */
  private Map parseMap(Element map) {
    Position[][] grid = new Position[5][5];
    List<Element> positions = map.getChildren("Position");
    int listCount = 0; // used to keep track of which child we are up to

    // positions are placed into grid row by row - therefore it is very important
    // that the positions are in the right order
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        Position pos = parsePosition(positions.get(listCount));
        grid[row][col] = pos;
        listCount++; // listCount iterated
      }
    }

    return new Map(grid);
  }

  /**
   * Logic for parsing a Position (used by loadFromFile)
   *
   * @param position
   * @return
   */
  private Position parsePosition(Element position) {
    Element xVal = position.getChild("xVal");
    Element yVal = position.getChild("yVal");
    Item item = parseItem(position.getChild("Item"));
    // get x and y values
    int x = Integer.parseInt(xVal.getText());
    int y = Integer.parseInt(yVal.getText());

    // returns null if x or y are less than 0, indicating the position is
    // innaccessible for the player
    if (x < 0 || y < 0) {
      return null;
    }

    // otherwise create the position, add its item and return it
    Position pos = new Position(x, y);
    pos.addItem(item);
    return pos;
  }

  /**
   * Finds a Position in the maps, based on the map, x and y value (used by loadFromFile)
   */
  private Position parseFindPosition(Element findPosition) {
    Element map = findPosition.getChild("Map");
    Element xVal = findPosition.getChild("xVal");
    Element yVal = findPosition.getChild("yVal");

    int mp = Integer.parseInt(map.getText());
    int x = Integer.parseInt(xVal.getText());
    int y = Integer.parseInt(yVal.getText());

    // get the correct map as a 2D array of Positions
    Position[][] currentMap = mapObjects.get(mp).getMap();

    // iterate over the map; looking for a Position with the same coordinates as xVal and yVal
    // and returning it
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        Position p = currentMap[row][col];
        if (p != null) {           // may need to be .equals
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
  private Item parseItem(Element item) {

    String type = item.getAttribute("Type").getValue();

    // returns null early if the Type of the item is 'Empty' (meaning the child elements do not exist)
    if (type.equals("Empty")) {return null;}

    // extract the children
    Element weight = item.getChild("Weight");  //int
    Element ID = item.getChild("ID");
    Element description = item.getChild("Description"); // string
    Element title = item.getChild("Title"); // string
    Element map = item.getChild("Map"); // integer (int)
    Element icon = item.getChild("Icon"); // icon

    // create parameter objects
    int w = Integer.parseInt(weight.getText());
    int id = Integer.parseInt(ID.getText());
    String descr = description.getText();
    String ttl = title.getText();
    Integer mp = Integer.getInteger(map.getText());
    Icon i = parseIcon(icon);

    // Implementation specific parsing
    if (type.equals("Key")) {
      Element door = item.getChild("DoorID");
      int doorID = Integer.parseInt(door.getText());
      return new Key(w, id, descr, ttl, mp, doorID, i);
    }
    if (type.equals("Book")) {
      String contents = item.getChild("Contents").getText();
      boolean isMagical = Boolean.valueOf(item.getChild("Magical").getText());
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
  private Door parseDoor (Element door) {
    Element locked = door.getChild("Locked");
    Element map = door.getChild("Map");
    Element ID = door.getChild("ID");
    Element link = door.getChild("Link");
    Element doorPosition = door.getChild("DoorPosition");
    Element linkPosition = door.getChild("LinkPosition");
    Element direction = door.getChild("Direction");

    boolean isLocked = Boolean.valueOf(locked.getText());
    int mapVal = new Integer(map.getText());
    int id = Integer.parseInt(ID.getText());
    Integer linkVal = new Integer(link.getText());
    Position doorPos = parseFindPosition(doorPosition);
    Position linkPos = parseFindPosition(linkPosition);

    // determine the Direction (set to North as default)
    Player.Direction dir = Direction.NORTH;
    String dirStr = direction.getText();
    if (dirStr.equals("SOUTH")) {dir = Direction.SOUTH;}
    else if (dirStr.equals("EAST")) {dir = Direction.EAST;}
    else if (dirStr.equals("WEST")) {dir = Direction.WEST;}

    return new Door(isLocked, mapVal, id, linkVal, doorPos, linkPos, dir);
  }

  /**
   * Logic for parsing an Icon (filename of the image used is saved in xml file)
   * used by loadFromFile
   */
  private Icon parseIcon(Element icon) {
    String filename = icon.getText();
    return new ImageIcon(filename);
  }



  /**
   * Create an xml save file from a list of maps, doors, and a Player
   * 
   * Note from Christian - 
   * CHANGED THE INPUT TO FIT THE REST OF THE PROGRAM and you can just use 
   * .values to get your lists. Also added the file the info will be saved to from the 
   * selector in Mapeditor and ApplicationWindow classes.
   */
  public void saveToFile(HashMap<Integer, Map> maps, HashMap<Integer, Door> doors, Player player, File file) {
    // root element
    Element gameElement = new Element("Game");
    Document doc = new Document(gameElement);

    // Map element
    //for (Map m : maps) {
      //Element mapElement = new Element("Map");
    
    //}
  }


}
