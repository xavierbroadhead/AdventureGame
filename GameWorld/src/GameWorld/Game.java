package GameWorld;

import java.util.HashMap;

public class Game {
	private HashMap<Integer, Map> maps;
	public Player player;
	
	public Game(HashMap<Integer, Map> maps, Player player) {
		this.maps = maps;
		this.player = player;
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
		for (Position[] p : buffer.getAccessiblePositions()) {
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
