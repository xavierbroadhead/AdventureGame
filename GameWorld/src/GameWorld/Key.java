package GameWorld;

public class Key extends Item {
	
	private Door door;
	
	public Key(int weight, Position position, int ID, String description, String title, Integer map, Door door) {
		super(weight, position, ID, description, title, map);
		this.door = door;
	}
	
	public Door getDoor() {
		return door;
	}
	
}
