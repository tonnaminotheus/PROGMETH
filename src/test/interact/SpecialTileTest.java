package test.interact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.base.Coordinate;
import interact.Player;
import interact.SpecialTile;
import interact.WhiteTile;
import logic.GameController;
import logic.Sprites;

public class SpecialTileTest {

	SpecialTile test = new SpecialTile(0, 0);
	Player player1 = new Player(0, 8, 1, new Coordinate(0,8), 1, 16);
	Player player2 = new Player(16, 8, 2, new Coordinate(16,8), 2, 0);
	
	@BeforeEach
	void setUpBeforeEachTest() {
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
	}

	@Test
	void testGetSymbol() {
		assertEquals(Sprites.SpecialTile, test.getSymbol());
	}

	@Test
	void testAddBarricade() {
		SpecialTile.addBarricade(player1);
		assertEquals(11, player1.getHaveBaricade());
	}

}
