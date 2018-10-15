import Applications.ApplicationWindow;
import GameWorld.Game;
import GameWorld.Player;
import java.util.ArrayList;
import javax.swing.JButton;
import mapEditor.MapEditor;
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
    ApplicationWindow.main(null);
  }
  
  
  /**
   * First buttons work.
<<<<<<< HEAD
   
=======
   */
>>>>>>> 1a378d5ad834f57a6707669cd08af2f0642eff64
  @Test
  public void checkbuttons() {
    Player player = null;
    Game game = new Game(player);
    MapEditor mapEditor = new MapEditor(game);
<<<<<<< HEAD
    ArrayList<JButton> buttons = mapEditor.getButtons();
    for (JButton butt : buttons) {
      butt.doClick();
    }
  }*/
=======
    mapEditor.setVisible(true);
    for (JButton butt : mapEditor.getButtons()) {
      butt.doClick();
    }
  }
>>>>>>> 1a378d5ad834f57a6707669cd08af2f0642eff64
}