package GameWorld;

public class Door {
	
	private boolean locked;
	private int map;
	private int doorID;
	private int link;
	
	public Door(boolean locked, int map, int doorID, int link) {
		this.locked = locked;
		this.map = map;
		this.doorID = doorID;
		this.link = link;
	}
	public int getID() {
		return doorID;
	}
	public int getMap() {
		return map;
	}
	public boolean isLocked() {
		return locked;
	}
	public int getLink() {
		return link;
	}
	public void setLock(boolean locked) {
		this.locked = locked;
	}
	
	/** Checks if the player has the correct key to open this door.
	 * 
	 * 
	 * @param player the player we are checking 
	 * @return true if the player has the correct key to open the door
	 */
	public boolean hasKey(Player player) {
		for (Item item : player.getInventory()) {
			if (item instanceof Key && ((Key) item).getDoor() == this) {
				return true;
			}
		}
		return false;
	}
	
	/** Checks if the player can open the door, and if it can, updates player's map to the next one
	 * 
	 * @param player the player that is opening the door
	 * @return true if we could move to the next room
	 */
	public boolean openDoor(Player player) {
		if (this.hasKey(player)) {
			player.updateMap(link);
			player.setPosition(new Position(0,0));
			return true;
		}
		else return false;
	}
}
