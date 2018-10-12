package GameWorld;

import javax.swing.Icon;

public class Key extends Item {

  private int door;

  public Key(int weight, int ID, String description, String title, Integer map, int doorID,
      Icon icon) {
    super(weight, ID, description, title, map, icon);
    this.door = doorID;
  }

  // reference to the Door is now just done via the door's ID. Makes parsing much easier
  public int getDoor() {
    return door;
  }

}
