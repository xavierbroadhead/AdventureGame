package MapEditor;

import GameWorld.Game;
import GameWorld.Player;
import org.junit.Test;



public class MapEditorTests {

  /**
   * First check to make sure the map editor does not fail on load. 
   */
  @Test
  public void checkInitialise() {
    Player player = null;
    Game game = new Game(player);
    MapEditor map = new MapEditor(game);
    map.main(null);
  }
  
  
  /**
   * First buttons work.
   */
  @Test
  public void checkbuttons() {
    Player player = null;
    Game game = new Game(player);
    MapEditor map = new MapEditor(game);
    //Check all of the buttons
  }
}
