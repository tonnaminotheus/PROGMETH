package test.interact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import interact.BlackTile;
import logic.Sprites;

class BlackTileTest {

	
	BlackTile test=new BlackTile(0,0);
	@Test
	void testGetSymbol() {
		assertEquals(Sprites.BlackTile,test.getSymbol());
	}

}
