package GameWorld;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Book extends Item {
  private boolean magical;
  private int code;

  public Book(int weight, int ID, String description, String title, Integer map, Boolean magical,
      String name, Icon icon) {
    super(weight, ID, description, title, map, icon);
    this.magical = magical;
    this.code = code;
  }

  public boolean isMagical() {
    return magical;
  }

  public String read() {
    return description;
  }
  public void setCode(int code) {
	  this.code = code;
  }
  public int getCode() {
	  return code;
  }
}
