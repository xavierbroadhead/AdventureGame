package GameWorld;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class Game {
  private HashMap<Integer, Map> maps;
  private static Player player;
  private HashMap<Integer, Key> keys;
  private HashMap<Integer, Door> doors;

  public Game(Player player) {
    this.maps = new HashMap<Integer, Map>();
    this.keys = new HashMap<Integer, Key>();
    this.doors = new HashMap<Integer, Door>();
    Position[][] map1 = { { new Position(0, 0), null, new Position(0, 2), new Position(0, 3), null },
        { new Position(1, 0), null, new Position(1, 2), null, null },
        { new Position(2, 0), new Position(2, 1), new Position(2, 2), null, null }, { null, null, null, null, null },
        { null, null, null, null, null } };

    Position[][] map2 = { { new Position(0, 0), null, null, null, null },
        { new Position(1, 0), null, null, null, null }, { new Position(2, 0), new Position(2, 1), null, null, null },
        { null, new Position(3, 1), null, new Position(3, 3), new Position(3, 4) },
        { null, new Position(4, 1), new Position(4, 2), new Position(4, 3), null } };

    Position[][] map3 = { { new Position(0, 0), new Position(0, 1), new Position(0, 2), null, null },
        { null, null, new Position(1, 2), null, new Position(1, 4) },
        { null, new Position(2, 1), new Position(2, 2), null, new Position(2, 4) },
        { null, new Position(3, 1), null, new Position(3, 3), new Position(3, 4) },
        { new Position(4, 0), new Position(4, 1), new Position(4, 2), new Position(4, 3), null } };

    Door door1 = new Door(true, 1, 1, 2, map1[0][3], map2[0][0], Player.Direction.NORTH);
    Door door2 = new Door(false, 2, 2, 1, map2[0][0], map1[0][3], Player.Direction.NORTH);
    Door door3 = new Door(true, 2, 3, 3, map2[3][4], map3[0][0], Player.Direction.EAST);
    Door door4 = new Door(false, 3, 4, 2, map3[0][0], map2[3][4], Player.Direction.NORTH);
    this.doors.put(1, door1);
    this.doors.put(2, door2);
    this.doors.put(3, door3);
    this.doors.put(4, door4);
    
    Key key1 = new Key(1, map1[2][2], 1, "A key with no markings.", "Key", 1, door1, new ImageIcon());
    Key key2 = new Key(1, map2[4][2], 2, "Its a key. You notice the letter Z inscribed on it.", "Key", 2, door3,
        new ImageIcon());
    this.keys.put(1, key1);
    this.keys.put(2, key2);
    
    
    this.maps.put(1, new Map(map1));
    this.maps.put(2, new Map(map2));
    this.maps.put(3, new Map(map3));

    this.player = player;
  }

  public HashMap<Integer, Map> getMaps() {
    return this.maps;
  }

  
  public HashMap<Integer, Key> getKeys() {
    return this.keys;
  }
  
  public HashMap<Integer, Door> getDoors() {
    return this.doors;
  }
  
  
  /**
   * Returns true if the position on the given map number is accessible
   * 
   * 
   * @param position
   *          - The position we are checking
   * @param mapNum
   *          - The map we want to check
   * @return - True if the position is within the accessible array stored in the
   *         given map
   */
  public boolean isAccessible(Position position, Integer mapNum) {
    if (position == null)
      return false;
    Position[][] buffer = maps.get(mapNum).getMap();
    int x = position.getx();
    int y = position.gety();
    if (buffer[x][y] != null)
      return true;
    else
      return false;
  }

  /**
   * Moves the player using WASD keys
   * 
   * @param keyPressed
   *          - The key on the keyboard pressed
   * @return - True if movement was successful
   */
  public boolean movePlayer(char keyPressed) {
    Position buffer = player.getPosition();
    Player.Direction dir = null;
    switch (java.lang.Character.toUpperCase(keyPressed)) {
    case 'W':
      dir = player.getDirection();
      break;
    case 'A':
      dir = player.getLeft();
      player.setDirection(dir);
      break;
    case 'S':
      dir = player.getDirection();
      player.setDirection(dir);
      if (player.moveValid(player.getBehind(), player.currentMapInteger())) {
        player.setPosition(player.requestPosition(player.getBehind()));
      }
      break;
    case 'D':
      dir = player.getRight();
      player.setDirection(dir);
      break;
    }

    if (dir == null) {
      throw new Error("Unknown input");
    }

    if (buffer == player.getPosition() && player.moveValid(dir, player.currentMapInteger())) {
      player.setPosition(player.requestPosition(dir));
      return true;
    } else
      return false;
  }
}
