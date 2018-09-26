package GameWorld;

public class Game {
	public Position[][] accessible;
	
	public Game(Position[][] accessible) {
		this.accessible = accessible;
	}
	public boolean isAccessible(Position position) {
		for (Position[] p : accessible) {
			for (Position p1 : p) {
				if (p1.equals(position)) return true;
			}
		}
		return false;
	}
}
