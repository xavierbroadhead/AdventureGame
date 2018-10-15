import Applications.ApplicationWindow;
import GameWorld.Game;
import GameWorld.Player;
import GameWorld.Position;
import MapEditor.MapEditor;
import org.junit.Test;


public class Tests {

  /**
   * Check to make sure the map editor does not fail on load. 
   */
  @Test
  public void mapEditorInitialise() {
    MapEditor.main(null);
  }
  
  /**
   * Check to make sure the application window does not fail on load. 
   */
  @Test
  public void appWindowInitialise() {
    ApplicationWindow appWin = new ApplicationWindow();
    appWin.main(null);
  }
  
  
  /**
   * First buttons work.
   */
  @Test
  public void checkbuttons() {
    Player player = new Player(1, new Position(0, 0));
    Game game = new Game(player);
    MapEditor mapEditor = new MapEditor(game);
  }
}