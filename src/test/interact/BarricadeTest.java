package test.interact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import interact.BarricadeTile;
import logic.Sprites;


public class BarricadeTest {
	
	BarricadeTile test1=new BarricadeTile(1,0,1,1);
	BarricadeTile test2=new BarricadeTile(1,1,1,0);
	
	@Test
	void testGetSymbol() {
		assertEquals(Sprites.Barricade,test1.getSymbol());
		assertEquals(Sprites.Barricade,test2.getSymbol());
	}
	
	
	
	
}
