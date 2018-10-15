package GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private Integer currentMap;
  private Position position;
  private List<Item> inventory;
  private Direction direction;
  private static Game game;

  public Player(int startMap, Position position) {
    this.currentMap = startMap;
    this.position = position;
    this.direction = Direction.NORTH;
    inventory = new ArrayList<Item>();
  }

  public enum Direction {
    NORTH, EAST, SOUTH, WEST;
  }

  public Direction getDirection() {
    return direction;
  }

  public void updateMap(int map) {
    this.currentMap = map;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public Integer currentMapInteger() {
    return currentMap;
  }

  public Position getPosition() {
    return position;
  }

  public List<Item> getInventory() {
    return inventory;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Returns names of all items in inventory in an array of strings
   * 
   * @return - An array containing the names of all items in iterating player's
   *         inventory
   */
  public String[] inventoryToString() {
    String[] buffer = { "" };
    for (Item i : this.getInventory()) {
      buffer[this.getInventory().indexOf(i)] = i.toString();
    }
    return buffer;
  }

  /**
   * Pick up an item that player is standing on top of
   * 
   * @return - True if the player could pick up the item
   */
  public boolean pickup() {
    if (this.position.containsItem()) {
      this.inventory.add(position.getItem());
      this.position.removeItem();
      return true;
    } else
      return false;
  }

  /**
   * Check if proposed movement is allowed
   * 
   * @param dir
   *          - The direction to check if the move is valid in
   * @return - True if movement is allowed
   */
  public boolean moveValid(Direction dir, Integer mapNum, Game game) {
    return game.isAccessible(this.requestPosition(dir, game), this.currentMap);
  }

  /**
   * Move the player in given direction
   * 
   * @param dir
   *          - direction in which we want to move the player
   * @return - True if movement was successful
   */
  public boolean movePlayer(Direction dir, Game game) {
    if (moveValid(dir, this.currentMap, game)) {
      this.position = this.requestPosition(dir, game);
      return true;
    } else
      return false;
  }

  /**
   * Returns direction 90 degrees to the LEFT of the player
   * 
   * 
   * @return - Direction to the left of the player
   */
  public Direction getLeft() {
    if (this.direction == Direction.NORTH) {
      return Direction.WEST;
    } else if (this.direction == Direction.EAST) {
      return Direction.NORTH;
    } else if (this.direction == Direction.SOUTH) {
      return Direction.EAST;
    }
    // assumed west
    else
      return Direction.SOUTH;
  }

  /**
   * Returns direction 90 degrees to the RIGHT of the player
   * 
   * 
   * @return - Direction to the left of the player
   */
  public Direction getRight() {
    if (this.direction == Direction.NORTH) {
      return Direction.EAST;
    } else if (this.direction == Direction.EAST) {
      return Direction.SOUTH;
    } else if (this.direction == Direction.SOUTH) {
      return Direction.WEST;
    }
    // assumed west
    else
      return Direction.NORTH;
  }

  /**
   * Returns direction 180 degrees behind player
   * 
   * 
   * @return - Direction behind player's current position
   */
  public Direction getBehind() {
    if (this.direction == Direction.NORTH) {
      return Direction.SOUTH;
    } else if (this.direction == Direction.EAST) {
      return Direction.WEST;
    } else if (this.direction == Direction.SOUTH) {
      return Direction.NORTH;
    }
    // assumed west
    else
      return Direction.EAST;
  }

  /**
   * Returns the position in the requested direction of the player
   * 
   * @param dir
   *          - Direction you need the new position relative to the player's
   *          current position to be in
   */
  public Position requestPosition(Direction dir, Game game) {
    Position[][] buffer = game.getMaps().get(this.currentMap).getMap();
    int x = this.position.getx();
    int y = this.position.gety();
    try{
    if (dir == null)
      return null;
    else if (dir == Direction.NORTH) {
      return buffer[y-1][x];
    } else if (dir == Direction.EAST) {
      return buffer[y][x+1];
    } else if (dir == Direction.SOUTH) {
      return buffer[y+1][x];
    }
    // assumed west
    else {
      return buffer[y][x-1];
    }
    }
    catch(ArrayIndexOutOfBoundsException exception){
      return this.position;
  }

  // used by Parser to fill inventory from file
  public void addItem(Item parseItem) {
    inventory.add(parseItem);
  }
}
