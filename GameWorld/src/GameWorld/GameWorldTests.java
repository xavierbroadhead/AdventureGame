package GameWorld;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.junit.Test;

public class GameWorldTests {
	/* Added to Tests.java file
	Player player = new Player(1, new Position(0,0));
	Game game = new Game(player);
	
	@Test
	public void testTilesTilNORTH() {
		player.setDirection(Player.Direction.NORTH);
		assert(game.tilesTilWall() == 0);
	}
	@Test
	public void testTilesTilEAST() {
		player.setDirection(Player.Direction.EAST);
		assert(game.tilesTilWall() == 0);
	}	
	@Test
	public void testTilesTilSOUTH() {
		player.setDirection(Player.Direction.SOUTH);
		assert(game.tilesTilWall() == 2);
	}	
	@Test
	public void testTilesTilWEST() {
		player.setDirection(Player.Direction.WEST);
		assert(game.tilesTilWall() == 0);
	}
	@Test
	public void testIsAccessible1() {
		Position[][] buffer = game.getMaps().get(1).getMap();
		assert(buffer[0][1] == null);
	}
	@Test
	public void testIsAccessible() {
		Position[][] buffer = game.getMaps().get(1).getMap();
		assert(buffer[0][0] instanceof Position);
	}
	@Test
	public void testToMap() {
		Position[] buffer = {new Position(3,2), new Position(1,2), new Position(2,2)};
		Position[][] buffer2d = game.positionsToMap(buffer);
		assert(buffer2d[2][3] instanceof Position && buffer2d[2][1] instanceof Position 
			   && buffer2d[2][2] instanceof Position);
	}
	@Test
	public void testWallForward() {
		assert(!game.wallForward());
	}	
	@Test
	public void testWallRight() {
		assert(game.wallRight());
	}	
	@Test
	public void testWallBehind() {
		assert(game.wallBehind());
	}	
	@Test
	public void testWallLeft() {
		assert(game.wallLeft());
	}
	@Test
	public void testRightCorner() {
		assert(!game.hasRightCorner());
	}
	@Test
	public void testLeftCorner() {
		assert(game.hasLeftCorner());
	}
	@Test
	public void testPickup() {
		Position[][] buffer = game.getMaps().get(1).getMap();
		player.setPosition(buffer[2][2]);
		player.pickup();
		assert(!player.getInventory().isEmpty());
	}
	@Test
	public void testMoveValid() {
		assert(player.moveValid(Player.Direction.SOUTH, 1, game));
	}
	@Test
	public void testMovePlayer() {
		Position[][] buffer = game.getMaps().get(1).getMap();
		player.movePlayer(Player.Direction.SOUTH, game);
		assert(player.getPosition().equals(buffer[1][0]));
	}
	@Test
	public void testGetLeft() {
		assert(player.getLeft() == Player.Direction.EAST);
	}	
	@Test
	public void testGetRight() {
		assert(player.getRight() == Player.Direction.WEST);
	}
	@Test
	public void testGetBehind() {
		assert(player.getBehind() == Player.Direction.NORTH);
	}
	@Test
	public void testRequestPosition() {
		Position[][] buffer = game.getMaps().get(1).getMap();
		player.setPosition(buffer[2][2]);
		assert(player.requestPosition(Player.Direction.NORTH, game) == (buffer[1][2]));
		assert(player.requestPosition(Player.Direction.EAST, game) == buffer[2][3]);
		assert(player.requestPosition(Player.Direction.SOUTH, game) == (buffer[3][2]));
		assert(player.requestPosition(Player.Direction.WEST, game) == (buffer[2][1]));
	}
	@Test
	public void testAddItem() {
		player.addItem(new Key(0, 0, null, null, null, 0, null));
		assert(!player.getInventory().isEmpty());
	}*/
}
