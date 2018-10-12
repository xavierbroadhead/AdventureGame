package GameWorld;

import org.junit.Test;

public class GameWorldTests {
	
	Player player = new Player(1, new Position(0,0));
	Game game = new Game(player);
	
	@Test
	public void test1() {
		player.setDirection(Player.Direction.SOUTH);
		assert(game.tilesTilWall() != 0);
	}
	
}
