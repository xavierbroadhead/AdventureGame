package GameWorld;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Camera {
	private File NORTH;
	private File EAST;
	private File SOUTH;
	private File WEST;
	
	public Camera(File NORTH, File EAST, File SOUTH, File WEST) {
		this.NORTH = NORTH;
		this.EAST = EAST;
		this.SOUTH = SOUTH;
		this.WEST = WEST;
	}
	public File getNorth() {
		return NORTH;
	}
	public File getEast() {
		return EAST;
	}
	public File getSouth() {
		return SOUTH;
	}
	public File getWest() {
		return WEST;
	}
	public List<File> asList() {
		return Arrays.asList(NORTH, EAST, SOUTH, WEST);
	}
}
