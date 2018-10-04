package GameWorld;

import javax.swing.Icon;

public class Key extends Item {
	
	private Door door;
	
	public Key(int weight, Position position, int ID, String description, String title, Integer map, Door door, Icon icon) {
		super(weight, position, ID, description, title, map, icon);
		this.door = door;
	}
	
	public Door getDoor() {
		return door;
	}
	
}
