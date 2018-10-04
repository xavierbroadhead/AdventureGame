package Parser;
import GameWorld.*;
import GameWorld.Map;
import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


/**
 * DATA STRUCTURE FOR XML SAVE FILE: below outlines the hierarchy of the elements in a valid XML save file
 * TODO: add representation and relevant code for storing data on Doors
 * TODO: finish representation and relevant code for Items
 * 
 * <Game>
 * 
 * 	<Player startMap = int>
 * 		<Position>
 * 			<xVal>int</xVal>
 * 			<yVal>int</yVal>
 * 			<Item>stuff; haven't done this part</Item>
 * 		</Position>
 * 		
 * 		<Inventory>
 * 			<Item>stuff; haven't done this part</Item> ++ (there can be any number of items here)
 * 		</Inventory>
 * 	</Player>
 * 
 * 	<Map> x 5; each game has 5 Maps
 * 		<Position>   x 25; there are 25 positions in a Map, forming a 5x5 grid. It is important these positions are stored in a specific order
 * 			<xVal>int</xVal>
 * 			<yVal>int</yVal>
 * 			<Item>stuff; haven't done this part</Item>
 * 		</Position>
 * 	</Map>
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
	 * TODO: make it so Maps can be passed into new Game
	 * @param fname filename (obtained from a file chooser of some sort)
	 * @return
	 */
	public Game loadFromFile (String fname) {
		File file = new File(fname);
		SAXBuilder sBuilder = new SAXBuilder();
		
		try {
			Document document = sBuilder.build(file);
			
			Element gameElement = document.getRootElement();
			Element playerElement = gameElement.getChild("Player");
			List<Element> maps = gameElement.getChildren("Map");
			
			//calls parsePlayer, creating the player object
			Player player = parsePlayer(playerElement);
			
			//creates and fills List of Map objects. Currently these have nowhere to go and just sit in the list. Later these will be passed to the game object
			List<Map> mapObjects = new ArrayList<>();
			for (Element mp : maps) {
				mapObjects.add(parseMap(mp));
			}
		
			Game game = new Game(player);
			// need to construct a way of adding mapObjects to game (will involve a fair amount of coding in Game class)
			return game;
			
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Logic for parsing the Player
	 * TODO: finish inventory loading functionality
	 * @param player
	 * @return player object generated from file
	 * @throws DataConversionException 
	 */
	private Player parsePlayer (Element player) throws DataConversionException {
		
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
	private Map parseMap (Element map) {
		Position[][] grid = new Position[5][5];
		List<Element> positions = map.getChildren("Position");
		int listCount = 0;   //used to keep track of which child we are up to
		
		// positions are placed into grid row by row - therefore it is very important that the positions are in the right order 
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				Position pos = parsePosition(positions.get(listCount));
				grid[row][col] = pos;
				listCount++;    //listCount iterated
			}
		}
		
		return new Map(grid);
	}
	
	
	
	/**
	 * Logic for parsing a Position
	 * @param position
	 * @return
	 */
	private Position parsePosition (Element position) {
		Element xVal = position.getChild("xVal");
		Element yVal = position.getChild("yVal");
		Item item = parseItem(position.getChild("Item"));
		// get x and y values
		int x = Integer.parseInt(xVal.getText());
		int y = Integer.parseInt(yVal.getText());
		
		// create the position, add its item and return it 
		Position pos = new Position(x,y);
		pos.addItem(item);
		return pos;
	}
	
	
	/**
	 * Logic for parsing an Item
	 * TODO: Unfinished; currently just a stub
	 * @param item
	 * @return
	 */
	private Item parseItem (Element item) {
		return null;
	}
	
}