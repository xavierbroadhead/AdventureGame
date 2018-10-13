package GameWorld;

import javax.swing.Icon;

public abstract class Item {

  protected int weight;
  protected int ID;
  protected String description;
  protected String title;
  protected Integer map;
  protected Icon icon;

  public Item(int weight, int ID, String description, String title, Integer map, Icon icon) {
    this.weight = weight;
    this.ID = ID;
    this.description = description;
    this.title = title;
    this.map = map;
    this.icon = icon;
  }

  public int getWeight() {
    return weight;
  }

  public int getItemID() {
    return ID;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return title;
  }

  public Integer currentMap() {
    return map;
  }

}
