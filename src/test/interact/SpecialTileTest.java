package test.interact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import interact.Player;
import interact.SpecialTile;
import interact.WhiteTile;
import logic.Sprites;

public class SpecialTileTest {
	// initiate map 9x9
	/*
	 * setup player1 ( 8 , 10 , 5) setup player2 ( 6 , 14 , 10)
	 * 
	 * use power on player on certain player
	 * 
	 * 
	 * 
	 */

	SpecialTile test = new SpecialTile(0, 0);
	Player player1 = new Player(0,8,1);
	Player player2 = new Player(16,8,2);

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
