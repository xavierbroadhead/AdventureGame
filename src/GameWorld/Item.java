package GameWorld;

import java.awt.Point;

import javax.swing.Icon;

public abstract class Item {
	
	private int weight;
	private Point point;
	private int ID;
	private String description;
	private Icon icon;
	
	public Item(int weight, Point point, int ID, String description, Icon icon) {
		this.weight = weight;
		this.point = point;
		this.ID = ID;
		this.description = description;
		this.icon = icon;
	}
	
	public int getWeight() {
		return weight;
	}
	public Point getPoint() {
		return point;
	}
	public int getItemID() {
		return ID;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public String getDescription() {
		return description;
	}
}
