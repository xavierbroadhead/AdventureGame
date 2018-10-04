package GameWorld;

public class Position {
	private int x;
	private int y;
	private Item item = null;
	private Camera camera;
	
	public Position(int x, int y, Camera camera) {
		this.x = x;
		this.y = y;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public Item getItem() {
		return item;
	}
	public Camera getCamer() {
		return camera;
	}
	public void addItem(Item item) {
		this.item = item;
	}
	public void removeItem() {
		this.item = null;
	}
	public boolean containsItem() {
		if (this.item != null) return true;
		else return false;
	}
}
