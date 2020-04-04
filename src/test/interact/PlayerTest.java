package test.interact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.base.Coordinate;
import interact.Player;
import logic.GameController;
import logic.Sprites;

public class PlayerTest {
	Player test1;
	Player test2;
	
	@BeforeEach
	protected void setUpBeforeEachTest() {
		test1 = new Player(0, 8, 1, new Coordinate(0,8), 1, 16);
		test2 = new Player(16, 8, 2, new Coordinate(16,8), 2, 0);
		test1.setOtherPlayer(test1);
		test2.setOtherPlayer(test2);
	}
	
	
	
	@Test
	void testGetSymbol() {
		assertEquals(Sprites.Player1,test1.getSymbol());
		assertEquals(Sprites.Player2,test2.getSymbol());
	}
}
