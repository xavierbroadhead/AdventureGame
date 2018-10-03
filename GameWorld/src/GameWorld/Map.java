package GameWorld;

public class Map {
	
	private Position[][] accessible;
	private Position[][] inaccessible;
	
	public Map(Position[][] accessible, Position[][] inaccessible) {
		this.accessible = accessible;
		this.inaccessible = inaccessible;
	}
	public Position[][] getAccessiblePositions(){
		return accessible;
	}
	public Position[][] getInaccessiblePositions(){
		return inaccessible;
	}
	public void setAccessiblePositions(Position[][] accessible) {
		this.accessible = accessible;
	}
	public void setInaccessiblePositions(Position[][] inaccessible) {
		this.inaccessible = inaccessible;
	}
}
