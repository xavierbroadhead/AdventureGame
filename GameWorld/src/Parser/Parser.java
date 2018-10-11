package Parser;

import GameWorld.Key;
import GameWorld.Door;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import GameWorld.Game;
import GameWorld.Item;
import GameWorld.Map;
import GameWorld.Player;
import GameWorld.Position;

/**
 * DATA STRUCTURE FOR XML SAVE FILE: below outlines the hierarchy of the
 * elements in a valid XML save file TODO: add representation and relevant code
 * for storing data on Doors
 *
 * <Game>
 *
 * <Player startMap = int>
 *  <Position>
 *   <xVal>int</xVal>
 *   <yVal>int</yVal>
 *    <Item>stuff; haven't done this part</Item>
 *  </Position>
 *
 * <Inventory> <Item>stuff; haven't done this part</Item> ++ (there can be any
 * number of items here) </Inventory> </Player>
 *
 * <Map> x 5; each game has 5 Maps <Position> x 25; there are 25 positions in a
 * Map, forming a 5x5 grid. It is important these positions are stored in a
 * specific order <xVal>int</xVal> <yVal>int</yVal> <Item>stuff; haven't done
 * this part</Item> </Position> </Map>
 *
 * </Game>
 *
 *
 *
 *
 *
 *
 * @author callum
 *
 */
public class Parser {

  /**
   * Create a Game from an xml save file
   * new Game
   *
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

      // calls parsePlayer, creating the player object
      Player player = parsePlayer(playerElement);

      // creates and fills List of Map objects. Currently these have nowhere to go and
      // just sit in the list. Later these will be passed to the game object
      List<Map> mapObjects = new ArrayList<>();
      for (Element mp : maps) {
        mapObjects.add(parseMap(mp));
      }

      Game game = new Game(player);
      // need to construct a way of adding mapObjects to game (will involve a fair
      // amount of coding in Game class)
      return game;

    } catch (JDOMException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Logic for parsing the Player
   * TODO: needs to determine if the position is already in a Map
   * @param player
   * @return player object generated from file
   * @throws DataConversionException
   */
  private Player parsePlayer(Element player) throws DataConversionException {

    int startMap = player.getAttribute("startMap").getIntValue();
    Position pos = parsePosition(player.getChild("Position"));

    Player p = new Player(startMap, pos);

    // fill the player's inventory (need to finish parseItem for this to work)
    Element inventory = player.getChild("Inventory");
    List<Element> items = inventory.getChildren("Item");
    for (Element i : items) {
      p.addItem(parseItem(i));
    }

    return p;
  }

  /**
   * Logic for parsing a Map
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
   * Logic for parsing a Position
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
   * Logic for parsing an Item
   *
   * @param item
   * @return
   */
  private Item parseItem(Element item) {

    String type = item.getAttribute("Type").getValue();

    // extract the children
    Element weight = item.getChild("Weight");  //int
    Element position = item.getChild("Position"); //position
    Element ID = item.getChild("ID");
    Element description = item.getChild("Description"); //string
    Element title = item.getChild("Title"); //string
    Element map = item.getChild("Map"); //integer (int)
    Element icon = item.getChild("Icon"); //icon

    // create parameter objects
    int w = Integer.parseInt(weight.getText());
    Position pos = parsePosition(position);
    int id = Integer.parseInt(ID.getText());
    String descr = description.getText();
    String ttl = title.getText();
    Integer mp = Integer.getInteger(map.getText());
    Icon i = parseIcon(icon);

    // Implementation specific parsing (uses parseDoor, which is unfinished)
    if (type.equals("Key")) {
      Door door = parseDoor(item.getChild("Door"));
      return new Key(w, pos, id, descr, ttl, mp, door, i);  // need ID
    }

    // add similar if blocks for other types of Item

    // returns null if the type is not specified or type is invalid
    return null;
  }

  // TODO: finish this (currently a stub)
  private Door parseDoor (Element door) {
    return null;
  }

  /**
   * Logic for parsing an Icon (filename of the image used is saved in xml file)
   */
  private Icon parseIcon (Element icon) {
    String filename = icon.getText();
    return new ImageIcon(filename);
  }

}
