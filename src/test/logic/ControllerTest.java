package test.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interact.BarricadeTile;
import interact.BlackTile;
import interact.Player;
import interact.SpecialTile;
import interact.WhiteTile;
import logic.GameController;
import logic.Sprites;
import logic.addBarricadeFail;
import logic.moveFail;
import logic.removeBarricadeFail;

public class ControllerTest {

	/*
	 * Initiate Map construct
	 * 
	 * 
	 */

	@BeforeEach
	void setUpBeforeEachTest() {
		GameController.IntializeMap();
	}

	@Test
	void testInitialize() {
		for (int j = 16; j >= 0; j--) {
			for (int i = 0; i < 17; i++) {
				if ((i == 0 && j == 8) || (i == 16 && j == 8)) {
					if (i == 0 && j == 8) {
						assertEquals(Player.class, GameController.getCurrentMap().getEntity(i, j).getClass());
					} else {
						assertEquals(Player.class, GameController.getCurrentMap().getEntity(i, j).getClass());
					}
				}
				else if(i==8&&j==8)
				{
					assertEquals(SpecialTile.class, GameController.getCurrentMap().getEntity(i, j).getClass());
				}
				else if (i % 2 == 0 && j % 2 == 0) {
					assertEquals(BlackTile.class, GameController.getCurrentMap().getEntity(i, j).getClass());
				} else {
					assertEquals(WhiteTile.class, GameController.getCurrentMap().getEntity(i, j).getClass());
				}
			}
		}
	}
	
	
	@Test
	public void testAddHorizontal() throws addBarricadeFail
	{
		
		GameController.addHorizontalBarricade(1, 1);
		//GameController.printmapcheck();
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(0, 1).getClass());
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(2, 1).getClass());
		
	}
	
	@Test
	public void testAddVertical() throws addBarricadeFail
	{
		
		GameController.addVerticalBarricade(1, 1);
		//GameController.printmapcheck();
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(1, 0).getClass());
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(1, 2).getClass());
	}
	
	@Test
	public void removeBarricade() throws removeBarricadeFail, addBarricadeFail
	{
		
		GameController.addVerticalBarricade(1, 1);
		//GameController.printmapcheck();
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(1, 0).getClass());
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(1, 2).getClass());
		GameController.removeBarricade(1, 0);
		GameController.printmapcheck();
		assertEquals(WhiteTile.class, GameController.getCurrentMap().getEntity(1, 0).getClass());
		assertEquals(WhiteTile.class, GameController.getCurrentMap().getEntity(1, 2).getClass());
	}
	
	@Test
	public void move() throws moveFail 
	{
		GameController.move(GameController.getPlayer1(), 2, 8, GameController.getPlayer1().getX(), GameController.getPlayer1().getY());
		GameController.printmapcheck();
		assertEquals(BlackTile.class, GameController.getCurrentMap().getEntity(0, 8).getClass());
		assertEquals(Player.class, GameController.getCurrentMap().getEntity(2, 8).getClass());
	}
	
	
	
	

	/*
	 * initiate map construct player1 (8,0,10) get x get y getBarricade ;8,0,10
	 * 
	 * 
	 * setBarricade 4 getbarricade ; 4
	 * 
	 * construct player2 (4,8,6) setOtherPlayer1 setOtherPlayer2
	 * 
	 * 
	 * getOtherPlayer Player1 Player2
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	// test all barricade
	/*
	 * initiate map p1 (8,0,10)
	 * 
	 * 
	 * 
	 */

}
