package GameWorld;

public class Position {
  private int x;
  private int y;
  private Item item = null;
  private Door door = null;
  private Camera camera;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getx() {
    return x;
  }
  public void setDoor(Door door) {
	  this.door = door;
  }
  public Door getDoor() {
	  return door;
  }
  public int gety() {
    return y;
  }

  public Item getItem() {
    return item;
  }

  public void addCamera(Camera camera) {
    this.camera = camera;
  }

  public Camera getCamera() {
    return camera;
  }

  public void addItem(Item item) {
    this.item = item;
  }

  public void removeItem() {
    this.item = null;
  }

  public boolean containsItem() {
    if (this.item != null)
      return true;
    else
      return false;
  }
}
