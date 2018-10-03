package GameWorld;

public class Game {
	public Position[][] accessible;
	public Player player;
	
	public Game(Position[][] accessible, Player player) {
		this.accessible = accessible;
		this.player = player;
	}
	public boolean isAccessible(Position position) {
		for (Position[] p : accessible) {
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
	 * @return true if movement was successful
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
				if(player.moveValid(player.getBehind())){
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
		
		if (buffer == player.getPosition() && player.moveValid(dir)) {
			player.setPosition(player.requestPosition(dir));
			return true;
		}
		else return false;
	}
}
