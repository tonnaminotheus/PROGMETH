package test.interact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import interact.BlackTile;
import interact.WhiteTile;
import logic.Sprites;

class WhiteTileTest {

	WhiteTile test=new WhiteTile(0,0);
	@Test
	void testGetSymbol() {
		assertEquals(Sprites.WhiteTile,test.getSymbol());
	}

}
