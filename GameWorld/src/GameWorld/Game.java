package GameWorld;

import java.util.HashMap;

public class Game {
	private HashMap<Integer, Map> maps;
	public Player player;
	
	public Game(Player player) {
		Position[][] map1 = {{new Position(0,0), null, new Position(0,2), new Position(0,3), null},
							{new Position(1,0), null, new Position(1,2), null, null},
							{new Position(2,0), new Position(2,1), new Position(2,2), null, null}
							,{null, null, null, null, null}
							,{null, null, null, null, null}};
		
		Position[][] map2 = {{new Position(0,0), null, null, null, null},
							{new Position(1,0), null, null, null, null},
							{new Position(2,0), new Position(2,1), null, null, null},
							{null, new Position(3,1), null, new Position(3,3), new Position(3,4)},
							{null, new Position(4,1), new Position(4,2), new Position(4,3), null}};
		
		Position[][] map3 = {{new Position(0,0), new Position(0,1), new Position(0,2), null, null},
							{null, null, new Position(1,2), null, new Position(1,4)},
							{null, new Position(2,1), new Position(2,2), null, new Position(2,4)},
							{null, new Position(3,1), null, new Position(3,3), new Position (3,4)},
							{new Position(4,0), new Position(4,1), new Position(4,2), new Position(4,3), null}};
		
		maps.put(1, new Map(map1));
		maps.put(2, new Map(map2));
		maps.put(3, new Map(map3));
		this.player = player;
	}
	public HashMap<Integer, Map> getMaps(){
		return maps;
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
		Map buffer = maps.get(mapNum);
		for (Position[] p : buffer.getMap()) {
			for (Position p1 : p) {
				if (p1.equals(position)) return true;
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
