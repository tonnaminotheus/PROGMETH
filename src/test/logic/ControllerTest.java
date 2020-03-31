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
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(1, 1).getClass());
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(2, 1).getClass());
		
	}
	
	@Test
	public void testAddVertical() throws addBarricadeFail
	{
		
		GameController.addVerticalBarricade(1, 1);
		//GameController.printmapcheck();
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(1, 0).getClass());
		assertEquals(BarricadeTile.class, GameController.getCurrentMap().getEntity(1, 1).getClass());
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
		//GameController.printmapcheck();
		assertEquals(WhiteTile.class, GameController.getCurrentMap().getEntity(1, 0).getClass());
		assertEquals(WhiteTile.class, GameController.getCurrentMap().getEntity(1, 2).getClass());
	}
	
	@Test
	public void move() throws moveFail 
	{
		GameController.move(GameController.getPlayer1(), 2, 8, GameController.getPlayer1().getX(), GameController.getPlayer1().getY());
		//GameController.printmapcheck();
		assertEquals(BlackTile.class, GameController.getCurrentMap().getEntity(0, 8).getClass());
		assertEquals(Player.class, GameController.getCurrentMap().getEntity(2, 8).getClass());
	}
	
	@Test
	public void checkbfs1()
	{
		/* 0 1 2 3 4 5 6 7 8 9 A B C D E F G
		 * 
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 P w b w b w b w b w b w b w b w P
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 */
		assertEquals(true,GameController.checkbfs(GameController.getPlayer1(), 16));
		assertEquals(true,GameController.checkbfs(GameController.getPlayer2(), 0));
		
	}
	
	@Test
	public void checkbfs2() throws moveFail, addBarricadeFail
	{
		/* 0 1 2 3 4 5 6 7 8 9 A B C D E F G
		 * 
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 B B B B w w w w w w w w w w w w w
		 *	 b w P B b w b w b w b w b w b w P
		 *	 B B B B w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b w b w b w b w b w b w b w b w b
		 */
		GameController.move(GameController.getPlayer1(), 2, 8, 0,8);
		GameController.addHorizontalBarricade(1, 9);
		GameController.addHorizontalBarricade(1, 7);
		GameController.addVerticalBarricade(3, 8);
		
		assertEquals(false,GameController.checkbfs(GameController.getPlayer1(), 16));
		assertEquals(true,GameController.checkbfs(GameController.getPlayer2(), 0));
		
	}
	
	
	@Test
	public void checkbfs3() throws addBarricadeFail
	{
		/* 0 1 2 3 4 5 6 7 8 9 A B C D E F G
		 * 
		 *	 b w b w b B b w b w b w b w b w b
		 *	 w w B B B B w w w w w w w w w w w
		 *	 b B b w b B b w b w b w b w b w b
		 *	 w B w w w w w w w w w w w w w w w
		 *	 b B b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b B b w b w b w b w b w b w b w b
		 *	 w B w w w w w w w w w w w w w w w
		 *	 P B b w b w b w b w b w b w b w P
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b B b w b w b w b w b w b w b w b
		 *	 w B w w w w w w w w w w w w w w w
		 *	 b B b w b w b w b w b w b w b w b
		 *	 w w w w w w w w w w w w w w w w w
		 *	 b B b w b w b w b w b w b w b w b
		 *	 w B w w w w w w w w w w w w w w w
		 *	 b B b w b w b w b w b w b w b w b
		 */
		GameController.addVerticalBarricade(1, 1);
		GameController.addVerticalBarricade(1, 5);
		GameController.addVerticalBarricade(1, 9);
		GameController.addVerticalBarricade(1, 13);
		GameController.addVerticalBarricade(3, 15);
		GameController.addHorizontalBarricade(3, 13);
		
		assertEquals(false,GameController.checkbfs(GameController.getPlayer1(), 16));
		assertEquals(false,GameController.checkbfs(GameController.getPlayer2(), 0));
		
	}

}
