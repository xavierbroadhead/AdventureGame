package GameWorld;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class Game {
	private HashMap<Integer, Map> maps;
	private Player player;
	private HashMap<Integer, Key> keys;
	private HashMap<Integer, Door> doors;

	public Game(Player player) {
		maps = new HashMap<Integer, Map>();
		this.keys = new HashMap<Integer, Key>();
		this.doors = new HashMap<Integer, Door>();
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

		Position[][] map3 = {{new Position(0,0), new Position(1,0), new Position(2,0), null, null},
							{null, null, new Position(2,1), null, new Position(4,1)},
							{null, new Position(1,2), new Position(2,2), null, new Position(4,2)},
							{null, new Position(1,3), null, new Position(3,3), new Position (4,3)},
							{new Position(0,4), new Position(1,4), new Position(2,4), new Position(3,4), null}};

		Door door1 = new Door(true, 1, 1, 2, map1[3][0], map2[0][0], Player.Direction.NORTH);
		Door door2 = new Door(false, 2, 2, 1, map2[0][0], map1[3][0], Player.Direction.NORTH);
		Door door3 = new Door(true, 2, 3, 3, map2[4][3], map3[0][0], Player.Direction.EAST);
		Door door4 = new Door(false, 3, 4, 2, map3[0][0], map2[4][3], Player.Direction.NORTH);
		this.doors.put(1, door1);
		this.doors.put(2, door2);
		this.doors.put(3, door3);
		this.doors.put(4, door4);


		maps.put(1, new Map(map1));
		maps.put(2, new Map(map2));
		maps.put(3, new Map(map3));

		this.player = player;
	}

	public HashMap<Integer, Map> getMaps(){
		return maps;
	}

	public HashMap<Integer, Key> getKeys(){
    return keys;
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
		Position[][] buffer = maps.get(mapNum).getMap();
		int x = position.getx();
		int y = position.gety();
		if (buffer[x][y] != null) return true;
		else return false;
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
			if (buffer[p.getx()][p.gety()] == null) buffer[p.getx()][p.gety()] = p;
		}
		return buffer;
	}

	/** Checks how many tiles from player's current position and current direction until they hit a wall
	 *
	 *
	 * @return integer representing number of tiles directly in front of player until hitting a wall
	 */
	public int tilesTilWall() {
		int i = 1;

		Integer currentInteger = player.currentMapInteger();
		Map map = maps.get(currentInteger);
		Position[][] currentMap = map.getMap();

		while(true) {
			if (this.player.getDirection() == Player.Direction.NORTH) {
				if (currentMap[this.player.getPosition().getx()][this.player.getPosition().gety() - i] != null) {
					i++;
				}
				else break;
			}
			else if (this.player.getDirection() == Player.Direction.EAST) {
				if (currentMap[this.player.getPosition().getx() + i][this.player.getPosition().gety()] != null) {
					i++;
				}
				else break;
			}
			else if (this.player.getDirection() == Player.Direction.SOUTH) {
				if (currentMap[this.player.getPosition().getx()][this.player.getPosition().gety() + i] != null) {
					i++;
				}
				else break;
			}
			else if (this.player.getDirection() == Player.Direction.WEST) {
				if (currentMap[this.player.getPosition().getx() - i][this.player.getPosition().gety()] != null) {
					i++;
				}
				else break;
			}
		}
		return i - 1;
	}

	/** Check if there is a left corner in front of our current position within our vision.
	 *
	 *
	 * @return - true if there is a left corner in front of player's current position and direction.
	 */
	public boolean hasLeftCorner() {
		Position[][] currentMap = maps.get(player.currentMapInteger()).getMap();
		int wall = tilesTilWall();
		if (this.player.getDirection() == Player.Direction.NORTH) {
			if (currentMap[this.player.getPosition().getx() - 1][this.player.getPosition().gety() - wall] != null) {
				return true;
			}
		}
		else if (this.player.getDirection() == Player.Direction.EAST) {
			if (currentMap[this.player.getPosition().getx() + wall][this.player.getPosition().gety() + 1] != null) {
				return true;
			}
		}
		else if (this.player.getDirection() == Player.Direction.SOUTH) {
			if (currentMap[this.player.getPosition().getx() + 1][this.player.getPosition().gety() + wall] != null) {
				return true;
			}
		}
		else if (this.player.getDirection() == Player.Direction.WEST) {
			if (currentMap[this.player.getPosition().getx() - wall][this.player.getPosition().gety() - 1] != null) {
				return true;
			}
		}
		return false;
	}

	/** Check if there is a corner to the right of our current position within our vision.
	 *
	 *
	 * @return - true if there is a right corner in front of the player's current position and direction.
	 */
	public boolean hasRightCorner() {
		Position[][] currentMap = maps.get(player.currentMapInteger()).getMap();
		int wall = tilesTilWall();
		if (this.player.getDirection() == Player.Direction.NORTH) {
			if (currentMap[this.player.getPosition().getx() + 1][this.player.getPosition().gety() - wall] != null) {
				return true;
			}
		}
		else if (this.player.getDirection() == Player.Direction.EAST) {
			if (currentMap[this.player.getPosition().getx() + wall][this.player.getPosition().gety() - 1] != null) {
				return true;
			}
		}
		else if (this.player.getDirection() == Player.Direction.SOUTH) {
			if (currentMap[this.player.getPosition().getx() - 1][this.player.getPosition().gety() + wall] != null) {
				return true;
			}
		}
		else if (this.player.getDirection() == Player.Direction.WEST) {
			if (currentMap[this.player.getPosition().getx() - wall][this.player.getPosition().gety() + 1] != null) {
				return true;
			}
		}
		return false;
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
				if(player.moveValid(player.getBehind(), player.currentMapInteger())){
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
		}
		else return false;
	}
}
