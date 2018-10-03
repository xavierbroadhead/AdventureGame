package GameWorld;

public class Book extends Item {
	private boolean magical;
	private String contents;
	
	public Book(int weight, Position position, int ID, String description, String title, boolean magical, String contents) {
		super(weight, position, ID, description, title);
		this.magical = magical;
		this.contents = contents;
	}
	public boolean isMagical() {
		return magical;
	}
	public String read() {
		return contents;
	}
}
