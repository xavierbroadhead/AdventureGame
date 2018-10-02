package GameWorld;

public class Key extends Item {
	
	private Door door;
	
	public Key(int weight, Position position, int ID, String description, String title, Door door) {
		super(weight, position, ID, description, title);
		this.door = door;
	}
	
	public Door getDoor() {
		return door;
	}
	
}
