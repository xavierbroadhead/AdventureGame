package GameWorld;

import javax.swing.Icon;

public class Book extends Item {
  private boolean magical;
  private String contents;

  public Book(int weight, int ID, String description, String title, Integer map, boolean magical,
      String contents, Icon icon) {
    super(weight, ID, description, title, map, icon);
    this.magical = magical;
    this.contents = contents;
  }

  public boolean isMagical() {
    return magical;
  }

  public String read() {
    return contents;
  }
}
