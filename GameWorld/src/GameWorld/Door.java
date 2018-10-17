package GameWorld;

public class Door {

  private boolean locked;
  private Integer map;
  private int doorID;
  private Integer link;
  private Position doorPosition;
  private Position linkPosition;
  private boolean beenThrough;

  public Door(boolean locked, Integer map, int doorID, Integer link, Position doorPosition, Position linkPosition) {
    this.locked = locked;
    this.map = map;
    this.doorID = doorID;
    this.link = link;
    this.doorPosition = doorPosition;
    this.linkPosition = linkPosition;
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


  public void setLock(boolean locked) {
    this.locked = locked;
  }

  public void setDoorPosition(Position position) {
    this.doorPosition = position;
  }

  public void setLinkPosition(Position position) {
    this.linkPosition = position;
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
      if (item instanceof Key && ((Key) item).getDoor() == doorID) {
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
    public boolean openDoor(Player player, Game game) {
	  int linkx = this.linkPosition.getx();
	  int originalx = this.doorPosition.getx();
	  int linky = this.linkPosition.gety();
	  int originaly = this.doorPosition.gety();
	  
    if (this.hasKey(player)) {
    		if (beenThrough) {
    			beenThrough = false;
    			player.updateMap(map);
    	        Position[][] buffer = game.getMaps().get(this.map).getMap();
    		    player.setPosition(buffer[originaly][originalx]);
    		    return true;
    		}
    		else {
    			beenThrough = true;
    			player.updateMap(link);
    	        Position[][] buffer = game.getMaps().get(this.link).getMap();
    		    player.setPosition(buffer[linky][linkx]);
    		    return true;
    		}
    } 
    else return false;
  }
}
