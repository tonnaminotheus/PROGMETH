package interact;

import logic.Sprites;
import logic.moveFail;
import logic.removeBarricadeFail;

import java.util.ArrayList;
import java.util.Scanner;

import interact.Player;
import logic.GameController;
import interact.ExplodingTile;

public class SpecialTile extends Tile {

	public SpecialTile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public static void getAction(Player e) throws moveFail {
		int random = (int) (Math.random() * 100)%8;
		if (random==0) {
			move1(e);
		} else if (random ==1) {
			addBarricade(e);
		} else if (random == 2)
			move1(e.getOtherPlayer());
		else if(random ==3){
			removeBarricade(e);
		}
		else if(random ==4){
			deleteAllSpecialTile();
		}
		else if(random==5){
			deleteAllBarricade();
		}else if(random==6){
			heal(e);
		}else {
			placeBomb(e);
		}
	}
	
	public static void heal(Player e){
		e.heal();
	}
	
	public static void placeBomb(Player e) {
		e.setHaveExploding(e.getHaveExploding()+1);
	}
	
	public static void deleteAllSpecialTile()
	{
		for(int i=0;i<17;i++)
		{
			for(int j=0;j<17;j++)
			{
				if(GameController.getCurrentMap().getEntity(i, j).isSpecialTile())
				{
					GameController.getCurrentMap().removeEntity(i, j);
					GameController.getCurrentMap().addEntity(new BlackTile(i,j),i,j);
				}
			}
		}
	}
	
	public static void deleteAllBarricade()
	{
		for(int i=0;i<17;i++)
		{
			for(int j=0;j<17;j++)
			{
				if(GameController.getCurrentMap().getEntity(i, j).isBarricadeTile())
				{
					GameController.getCurrentMap().removeEntity(i, j);
					GameController.getCurrentMap().addEntity(new WhiteTile(i,j),i,j);
				}
			}
		}
	}

	public static void move1(Player e) throws moveFail {
		double random = Math.random();
		int dir = (int) (random * 100 - 1) / 25;
		int[] dirx = { 0, 1, 0, -1 };
		int[] diry = { -1, 0, 1, 0 };
		GameController.move(e, e.getX() + 2 * dirx[dir], e.getY() + 2 * diry[dir], e.getX(), e.getY());
	}

	public static void addBarricade(Player e) {
		e.setBarricade(e.getHaveBaricade() + 1);
	}

	public static void removeBarricade(Player e) {
		e.setHaveRemoveBarricade(e.getHaveRemoveBarricade()+1);
	}

	public int getSymbol() {
		return Sprites.SpecialTile;
	}

}
