package GameWorld;

public class Door {

  private boolean locked;
  private Integer map;
  private int doorID;
  private Integer link;
  private Position doorPosition;
  private Position linkPosition;
  private Player.Direction doorDirection;
  private Game game;

  public Door(boolean locked, Integer map, int doorID, Integer link, Position doorPosition, Position linkPosition,
      Player.Direction direction) {
    this.locked = locked;
    this.map = map;
    this.doorID = doorID;
    this.link = link;
    this.doorPosition = doorPosition;
    this.linkPosition = linkPosition;
    this.doorDirection = direction;
  }

  public int getID() {
    return doorID;
  }

  public Integer getMap() {
    return map;
  }

  public boolean isLocked() {
    return locked;
  }

  public Integer getLink() {
    return link;
  }

  public Position getDoorPosition() {
    return doorPosition;
  }

  public Position getLinkPosition() {
    return linkPosition;
  }

  public Player.Direction getDirection() {
    return doorDirection;
  }

  public void setLock(boolean locked) {
    this.locked = locked;
  }

  public void setDoorPosition(Position position) {
    this.doorPosition = position;
  }

  public void setLinkPosition(Position position) {
    this.linkPosition = position;
  }

  public void setDirection(Player.Direction direction) {
    this.doorDirection = direction;
  }

  /**
   * Checks if the player has the correct key to open this door.
   * 
   * 
   * @param player
   *          the player we are checking
   * @return true if the player has the correct key to open the door
   */
  public boolean hasKey(Player player) {
    for (Item item : player.getInventory()) {
      if (item instanceof Key && ((Key) item).getDoor() == this) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the player can open the door, and if it can, updates player's map
   * to the next one
   * 
   * @param player
   *          the player that is opening the door
   * @return true if we could move to the next room
   */
  public boolean openDoor(Player player) {
    if (this.hasKey(player)) {
      Position[][] buffer = game.getMaps().get(this.link).getMap();
      player.updateMap(link);
      player.setPosition(buffer[0][0]);
      return true;
    } else
      return false;
  }
}
