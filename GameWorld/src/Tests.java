import Applications.ApplicationWindow;
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
  
}