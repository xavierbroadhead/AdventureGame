package GameWorld;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Book extends Item {
  private boolean magical;
  private int code;

  /**
   * Instantiates a new book.
   *
   * @param weight the weight
   * @param ID the id
   * @param description the description
   * @param title the title
   * @param map the map
   * @param magical the magical
   * @param name the name
   * @param icon the icon
   */
  public Book(int weight, int ID, String description, String title, Integer map, Boolean magical,
      String name, Icon icon) {
    super(weight, ID, description, title, map, icon);
    this.magical = magical;
    this.code = code;
  }

  /**
   * Checks if is magical.
   *
   * @return true, if is magical
   */
  public boolean isMagical() {
    return magical;
  }

  /**
   * Read.
   *
   * @return the string
   */
  public String read() {
    return description;
  }
  
  /**
   * Sets the code.
   *
   * @param code the new code
   */
  public void setCode(int code) {
	  this.code = code;
  }
  
  /**
   * Gets the code.
   *
   * @return the code
   */
  public int getCode() {
	  return code;
  }
}
