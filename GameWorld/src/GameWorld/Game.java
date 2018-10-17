package GameWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

public class Game {
	private HashMap<Integer, Map> maps;
	private Player player;
	private HashMap<Integer, Door> doors;

	
	public Game(Player player) {
		maps = new HashMap<Integer, Map>();
		doors = new HashMap<Integer, Door>();
		Position[][] map1 = {{new Position(0,0), null, new Position(2,0), new Position(3,0), null},
							{new Position(0,1), null, new Position(2,1), null, null},
							{new Position(0,2), new Position(1,2), new Position(2,2), null, null}
							,{null, null, null, null, null}
							,{null, null, null, null, null}};
		
		Position[][] map2 = {{new Position(0,0), null, null, null, null},
							{new Position(0,1), null, null, null, null},
							{new Position(0,2), new Position(1,2), null, null, null},
							{null, new Position(1,3), null, new Position(3,3), new Position(4,3)},
							{null, new Position(1,4), new Position(2,4), new Position(3,4), null}};
		
		Position[][] map3 = {{new Position(0,0), new Position(1,0), null, null, null},
							{null, new Position(1,1), null, null, null},
							{null, new Position(1,2), null, null, new Position(4,2)},
							{null, new Position(1,3), null, null, new Position (4,3)},
							{null, new Position(1,4), new Position(2,4), new Position(3,4), new Position(4,4)}};
		
		Door door1 = new Door(true, 1, 1, 2, map1[0][3], map2[0][0]);
		Door door2 = new Door(false, 2, 2, 1, map2[0][0], map1[3][0]);
		Door door3 = new Door(true, 2, 3, 3, map2[3][4], map3[0][0]);
		Door door4 = new Door(false, 3, 4, 2, map3[0][0], map2[4][3]);
		
		map1[0][3].setDoor(door1);
		map2[0][0].setDoor(door2);
		map2[3][4].setDoor(door3);
		map3[0][0].setDoor(door4);
		doors.put(1, door1);
		doors.put(2, door2);
		doors.put(3, door3);
		doors.put(4, door4);
    
		Key key = new Key(1, 1, "A key with no markings." , "Key", 1, door1.getID(), new ImageIcon());
		Key key2 = new Key(1, 2, "Its a key. You notice the letter Z inscribed on it.", "Key", 2, door3.getID(), new ImageIcon());
		map1[2][2].addItem(key);
		map2[4][2].addItem(key2);
    
		maps.put(1, new Map(map1));
		maps.put(2, new Map(map2));
		maps.put(3, new Map(map3));
		
		this.player = player;
	}
	
	
	/**
	 * Alternative constructor used when game is being created from an XML file. Sets the fields based on parameters passed into the constructor
	 * TODO: Keys currently doesn't get filled as the XML file does not store a list of all the keys
	 * @param mapList
	 * @param doorList
	 * @param p
	 */
	public Game(List<Map> mapList, List<Door> doorList, Player p) {
	  // fill maps
	  for (int i = 0; i < mapList.size(); i++) {
	    maps.put(i, mapList.get(i));
	  }
	  
	  // fill doors
	  for (int i = 0; i < doorList.size(); i++) {
      doors.put(i, doorList.get(i));
    }
	  
	  // set player
	  this.player = p;
	}
	
	public HashMap<Integer, Map> getMaps(){
		return maps;
	}
	

	
	public HashMap<Integer, Door> getDoors(){
    return doors;
  }
	/** Returns true if the position on the given map number is accessible
	 * 
	 * 
	 * @param position - The position we are checking
	 * @param mapNum - The map we want to check
	 * @return - True if the position is within the accessible array stored in the given map
	 */
	public boolean isAccessible(Position position, Integer mapNum) {
		if (position == null) return false;
		try {
			Position[][] buffer = maps.get(mapNum).getMap();
			int x = position.getx();
			int y = position.gety();
			if (buffer[y][x] != null) return true;
			else return false;
		}
		catch (ArrayIndexOutOfBoundsException exception) {
			return false;
		}
	}
	
	/** Takes in an array of Positions, returns a 5x5 2D array of Positions correctly ordered as a map.
	 * 
	 * 
	 * @param pos - Array of Positions we want to convert to a map
	 * @return - 2D array of Positions
	 */
	public Position[][] positionsToMap(Position[] pos){
		Position[][] buffer = {{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null}};
		for (Position p : pos) {
			if (buffer[p.gety()][p.getx()] == null) buffer[p.gety()][p.getx()] = p;
		}
		return buffer;
	}
		
	/** Returns number of tiles until a trap door.
	 * 
	 * 
	 * @return number of tiles until a trap door, or 0 if there is no door in front of us
	 */
	public int tilesTilDoor() {
		int x = player.getPosition().getx();
		int y = player.getPosition().gety();
		Position[][] buffer = this.maps.get(player.currentMapInteger()).getMap();
		List<Door> doorsInRoom = new ArrayList<Door>();
		for (Door door : doors.values()) {
			if (door.getMap() == player.currentMapInteger()) doorsInRoom.add(door);
			}
		for (int i = 1; i < tilesTilWall(); i++) {
			if (player.getDirection() == Player.Direction.NORTH) {
				for (Door door : doorsInRoom) {
					if (buffer[y-i][x] == door.getDoorPosition()) return i;
				}
			}
			if (player.getDirection() == Player.Direction.EAST) {
				for (Door door : doorsInRoom) {
					if (buffer[y][x+i] == door.getDoorPosition()) return i;
				}
			}
			if (player.getDirection() == Player.Direction.SOUTH) {
				for (Door door : doorsInRoom) {
					if (buffer[y+i][x] == door.getDoorPosition()) return i;
				}
			}
			if (player.getDirection() == Player.Direction.WEST) {
				for (Door door : doorsInRoom) {
					if (buffer[y][x-i] == door.getDoorPosition()) return i;
				}
			}
		}
		return 0;
	}
	
	/** Checks if there is an item in the corridor in front of the player.
	 * 
	 * 
	 * @return true if there is an item in the corridor in front of the player
	 */
	public boolean itemInCorridor() {
		int x = player.getPosition().getx();
		int y = player.getPosition().gety();
		int i = 1;
		Player.Direction direction = player.getDirection();
		Integer integer = player.currentMapInteger();
		Position[][] currentMap = this.maps.get(integer).getMap();
		try {
			while(true) {
				if (direction == Player.Direction.NORTH) {
					if (isAccessible(currentMap[y - i][x], integer)) {
						if (currentMap[y - 1][x].containsItem()) {
							return true;
						}
						else i++;

					}
					else break;
				}
				else if (direction == Player.Direction.EAST) {
					if (isAccessible(currentMap[y][x + i], integer)) {
						if(currentMap[y][x + i].containsItem()) {
							return true;
						}
						else i++;
					}
					else break;
				}
				else if (direction == Player.Direction.SOUTH) {
					if (isAccessible(currentMap[y + i][x], integer)) {
						if(currentMap[y + i][x].containsItem()) {
							return true;
						}
						else i++;
					}
					else break;
				}
				else if (direction == Player.Direction.WEST) {
					if (isAccessible(currentMap[y][x - i], integer)) {
						if(currentMap[y][x - i].containsItem()) {
							return true;
						}
						else i++;
					}
					else break;
				}
			}
			return false;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			return false;
		}
	}
	/** Counts the amount of tiles in front of the player until an there is an item.
	 * 
	 * 
	 * @return the number of tiles in front of the player until there is an item, 
	 * or -1 if there was an error or no item was found
	 */
	public int tilesTilItem() {
		int x = player.getPosition().getx();
		int y = player.getPosition().gety();
		int i = 0;
		Player.Direction direction = player.getDirection();
		Integer integer = player.currentMapInteger();
		Position[][] currentMap = this.maps.get(integer).getMap();
		try {
			if(itemInCorridor()) {
				for (i = 0; i < tilesTilWall(); i++) {
					if (direction == Player.Direction.NORTH) {
						if (currentMap[y - i][x].containsItem()) {
							return i;
						}
					}
					else if (direction == Player.Direction.EAST) {
						if (currentMap[y][x + i].containsItem()) {
							return i;
						}
					}
					else if (direction == Player.Direction.SOUTH) {
						if (currentMap[y + i][x].containsItem()) {
							return i;
						}
					}
					else if (direction == Player.Direction.WEST) {
						if (currentMap[y][x - i].containsItem()) {
							return i;
						}
					}
				}
			}
			return i;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			return i;
		}
	}
	/** Checks if there is a wall in front of the current position
	 * 
	 * 
	 * @return true if there is a wall in front of the player
	 */
	public boolean wallForward() {
		int x = this.player.getPosition().getx();
		int y = this.player.getPosition().gety();
		Player.Direction direction = player.getDirection();
		Integer current = player.currentMapInteger();
		Position[][] currentMap = this.maps.get(current).getMap();
		try {
			if(direction == Player.Direction.NORTH) {
				if (isAccessible(currentMap[y-1][x], current))
					return false;
			}
			else if(direction == Player.Direction.EAST) {
				if (isAccessible(currentMap[y][x+1], current))
					return false;
			}
			else if(direction == Player.Direction.SOUTH) {
				if(isAccessible(currentMap[y+1][x], current))
					return false;
			
			}
			else if(direction == Player.Direction.WEST) {
				if(isAccessible(currentMap[y][x-1], current))
					return false;
			}
			return true;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			return true;
		}
	}	
	
	/** Checks if there is a wall to the right of the current position
	 * 
	 * 
	 * @return true if there is a wall to the right of the player
	 */
	public boolean wallRight() {
		int x = this.player.getPosition().getx();
		int y = this.player.getPosition().gety();
		Player.Direction direction = player.getDirection();
		Integer current = player.currentMapInteger();
		Position[][] currentMap = this.maps.get(current).getMap();
		try {
			if(direction == Player.Direction.NORTH) {
				if (isAccessible(currentMap[y][x+1], current))
					return false;
			}
			else if(direction == Player.Direction.EAST) {
				if (isAccessible(currentMap[y+1][x], current))
					return false;
			}
			else if(direction == Player.Direction.SOUTH) {
				if(isAccessible(currentMap[y][x-1], current))
					return false;
			
			}
			else if(direction == Player.Direction.WEST) {
				if(isAccessible(currentMap[y-1][x], current))
					return false;
			}
			return true;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			return true;
		}
	}
	
	/** Check if there is a wall behind the current position
	 * 
	 * 
	 * @return true if there is a wall behind player
	 */
	public boolean wallBehind() {
		int x = this.player.getPosition().getx();
		int y = this.player.getPosition().gety();
		Player.Direction direction = player.getDirection();
		Integer current = player.currentMapInteger();
		Position[][] currentMap = this.maps.get(current).getMap();
		try {
			if(direction == Player.Direction.NORTH) {
				if (isAccessible(currentMap[y+1][x], current))
					return false;
			}
			else if(direction == Player.Direction.EAST) {
				if (isAccessible(currentMap[y][x-1], current))
					return false;
			}
			else if(direction == Player.Direction.SOUTH) {
				if(isAccessible(currentMap[y-1][x], current))
					return false;		
			}
			else if(direction == Player.Direction.WEST) {
				if(isAccessible(currentMap[y][x+1], current))
					return false;
			}
			return true;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			return true;
		}
	}	
	
	/** Check if there is a wall to the left of the current position
	 * 
	 * 
	 * @return true if there is a wall to the left of the player
	 */
	public boolean wallLeft() {
		int x = this.player.getPosition().getx();
		int y = this.player.getPosition().gety();
		Player.Direction direction = player.getDirection();
		Integer current = player.currentMapInteger();
		Position[][] currentMap = this.maps.get(current).getMap();
		try {
			if(direction == Player.Direction.NORTH) {
				if (isAccessible(currentMap[y][x-1], current))
					return false;
			}
			else if(direction == Player.Direction.EAST) {
				if (isAccessible(currentMap[y-1][x], current))
					return false;
			}
			else if(direction == Player.Direction.SOUTH) {
				if(isAccessible(currentMap[y][x+1], current))
					return false;

			}
			else if(direction == Player.Direction.WEST) {
				if(isAccessible(currentMap[y+1][x], current))
					return false;
			}
			return true;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			return true;
		}
	}
	
	/** Checks how many tiles from player's current position and current direction until they hit a wall
	 * 
	 * 
	 * @return integer representing number of tiles directly in front of player until hitting a wall
	 */
	public int tilesTilWall() {
		int i = 1;
		int x = this.player.getPosition().getx();
		int y = this.player.getPosition().gety();
		Player.Direction direction = this.player.getDirection();
		Position[][] currentMap = this.maps.get(player.currentMapInteger()).getMap();
		Integer integer = player.currentMapInteger();
		
		if ((y == 0 && direction == Player.Direction.NORTH) ||
				(x == 0 && y == 0 && (direction == Player.Direction.NORTH || direction == Player.Direction.WEST))||
				(x == 4 && y == 0 && (direction == Player.Direction.NORTH || direction == Player.Direction.EAST))||
				(x == 4 && direction == Player.Direction.EAST) ||
				(x == 4 && y == 4 && (direction == Player.Direction.EAST || direction == Player.Direction.SOUTH)) ||
				(y == 4 && direction == Player.Direction.SOUTH) ||
				(x == 0 && y == 4 && (direction == Player.Direction.SOUTH || direction == Player.Direction.WEST))||
				(x == 0 && direction == Player.Direction.WEST))
			return 0;
		
		try{
			while(true) {
				if (direction == Player.Direction.NORTH) {
					if (isAccessible(currentMap[y - i][x], integer)) {
						i++;
					}
					else break;
				}
				else if (direction == Player.Direction.EAST) {
					if (isAccessible(currentMap[y][x + i], integer)) {
						i++;
					}
					else break;
				}
				else if (direction == Player.Direction.SOUTH) {
					if (isAccessible(currentMap[y + i][x], integer)) {
						i++;
					}
					else break;
				}
				else if (direction == Player.Direction.WEST) {
					if (isAccessible(currentMap[y][x - i], integer)) {
						i++;
					}
					else break;
				}
			}
			return i - 1;
		}
		catch (ArrayIndexOutOfBoundsException exception){
			return i - 1;
		}
	}
	
	/** Check if there is a left corner in front of our current position within our vision.
	 * 
	 * 
	 * @return - true if there is a left corner in front of player's current position and direction.
	 */
	public boolean hasLeftCorner() {
		Position[][] currentMap = maps.get(player.currentMapInteger()).getMap();
		int wall = tilesTilWall();
		if(wall == 0) return false;
		int x = this.player.getPosition().getx();
		int y = this.player.getPosition().gety();
		Integer integer = player.currentMapInteger();
		Player.Direction direction = this.player.getDirection();
		
		if ((y == 0 && (direction == Player.Direction.EAST || direction == Player.Direction.NORTH)) ||
				(x == 0 && y == 0 &&(direction == Player.Direction.NORTH || direction == Player.Direction.EAST))||
				x == 4 && y == 0 &&(direction == Player.Direction.SOUTH || direction == Player.Direction.EAST)||
				(x == 4 && (direction == Player.Direction.SOUTH || direction == Player.Direction.EAST)) ||
				(x == 4 && y == 4 &&(direction == Player.Direction.SOUTH || direction == Player.Direction.WEST))||
				(y == 4 && (direction == Player.Direction.WEST || direction == Player.Direction.SOUTH))||
				(x == 0 && y == 4 &&(direction==Player.Direction.NORTH || direction == Player.Direction.WEST))||
				(x == 0 && direction == Player.Direction.NORTH || direction == Player.Direction.WEST))
			return false;
		
		try {
			if (direction == Player.Direction.NORTH) {
				if (isAccessible(currentMap[y - wall][x - 1], integer)) {
					return true;
				}
			}
			else if (direction == Player.Direction.EAST) {
				if (isAccessible(currentMap[y - 1][x + wall], integer)) {
					return true;
				}
			}		
			else if (direction == Player.Direction.SOUTH) {
				if (isAccessible(currentMap[y + wall][x + 1], integer)) {
					return true;
				}
			}		
			else if (direction == Player.Direction.WEST) {
				if (isAccessible(currentMap[y + 1][x - wall], integer)) {
					return true;
				}
			}
			return false;
		}
		catch (ArrayIndexOutOfBoundsException exception){
			return false;
		}
	}
	
	/** Check if there is a corner to the right of our current position within our vision.
	 * 
	 * 
	 * @return - true if there is a right corner in front of the player's current position and direction.
	 */
	public boolean hasRightCorner() {
		Position[][] currentMap = maps.get(player.currentMapInteger()).getMap();
		int wall = tilesTilWall();
		if (wall == 0) return false;
		int x = this.player.getPosition().getx();
		int y = this.player.getPosition().gety();
		Player.Direction direction = this.player.getDirection();
		Integer integer = player.currentMapInteger();
		
		if ((y == 0 && (direction == Player.Direction.WEST || direction == Player.Direction.NORTH)) ||
				(x == 0 && y == 0 &&(direction == Player.Direction.WEST || direction == Player.Direction.SOUTH))||
				x == 4 && y == 0 &&(direction == Player.Direction.WEST || direction == Player.Direction.NORTH)||
				(x == 4 && (direction == Player.Direction.NORTH || direction == Player.Direction.EAST)) ||
				(x == 4 && y == 4 &&(direction == Player.Direction.EAST || direction == Player.Direction.NORTH))||
				(y == 4 && (direction == Player.Direction.EAST || direction == Player.Direction.SOUTH))||
				(x == 0 && y == 4 &&(direction==Player.Direction.EAST || direction == Player.Direction.SOUTH))||
				(x == 0 && (direction == Player.Direction.SOUTH || direction == Player.Direction.WEST)))
			return false;
			
		try{
			if (direction == Player.Direction.NORTH) {
				if (isAccessible(currentMap[y - wall][x + 1], integer)) {
					return true;
				}
			}
			else if (direction == Player.Direction.EAST) {
				if (isAccessible(currentMap[y + 1][x + wall], integer)) {
					return true;
				}
			}		
			else if (direction == Player.Direction.SOUTH) {
				if (isAccessible(currentMap[y + wall][x - 1], integer)) {
					return true;
				}
			}		
			else if (direction == Player.Direction.WEST) {
				if (isAccessible(currentMap[y - 1][x - wall], integer)) {
					return true;
				}
			}
			return false;
		}
		catch (ArrayIndexOutOfBoundsException exception){
			return false;
		}
	}
	
	/**
	 * Moves the player using WASD keys
	 * 
	 * @param keyPressed - The key on the keyboard pressed 
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
				if(player.moveValid(player.getBehind(), player.currentMapInteger(), this)){
					player.setPosition(player.requestPosition(player.getBehind(), this));
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
		if (buffer == player.getPosition() && player.moveValid(dir, player.currentMapInteger(), this)) {
			player.setPosition(player.requestPosition(dir, this));
			return true;
		}
		else return false;
	}

  public void setMaps(HashMap<Integer, Map> newMaps) {
    maps = newMaps;
  }
  
  public void setDoors(HashMap<Integer, Door> newDoors) {
    doors = newDoors;
  }
  


  public Player getPlayer() {
    return this.player;
  }
  
  /* was for in game changes but not happening anymore
  public Game clone() {
    Player player = new Player(this.player.getcurrentMap(), this.player.getPosition());
    Game newGame = new Game(player);
    newGame.copyInfo(this);
    return newGame;
  }

  
   * get all of the information in game in to the current game.
   * @param game the game whose information is being copied. 
   
  private void copyInfo(Game game) {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.maps.get(0).getMap()[i][j] = game.maps.get(0).getMap()[i][j];
        this.maps.get(1).getMap()[i][j] = game.maps.get(1).getMap()[i][j];
        this.maps.get(2).getMap()[i][j] = game.maps.get(2).getMap()[i][j];
      }
    }
    //Clone doors
    //Clone Keys
    
  }*/
}
