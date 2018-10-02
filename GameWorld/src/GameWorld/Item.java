package GameWorld;


import javax.swing.Icon;

public abstract class Item {
	
	protected int weight;
	protected Position position;
	protected int ID;
	protected String description;
	protected String title;
	
	public Item(int weight, Position position, int ID, String description, String title) {
		this.weight = weight;
		this.position = position;
		this.ID = ID;
		this.description = description;
		this.title = title;
	}
	
	public int getWeight() {
		return weight;
	}
	public Position getPosition() {
		return position;
	}
	public int getItemID() {
		return ID;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public String toString() {
		return title;
	}
	
}
