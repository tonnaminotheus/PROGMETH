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
		int random = (int) (Math.random() * 100)%6;
		if (random==0) {
			move1(e);
		} else if (random ==1) {
			addBarricade(e);
		} else if (random == 2)
			move1(e.getOtherPlayer());
		else if(random ==3){
			removeBarricade();
		}
		else if(random ==4){
			deleteAllSpecialTile();
		}
		else {
			deleteAllBarricade();
		}
	}
	
	public static void heal(Player e){
		e.heal();
	}
	
	public static void placeBomb() {
		ArrayList<BlackTile> placeBombTile=GameController.getCurrentMap().getSpawnTile();
		if(!placeBombTile.isEmpty()){
			Scanner scanner = new Scanner(System.in);
			System.out.println("input x y");
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			System.out.println(x+" "+y);
			if(GameController.getCurrentMap().getEntity(x,y).isBlackTile()) {
				GameController.getCurrentMap().removeEntity(x, y);
				GameController.getCurrentMap().addEntity(new ExplodingTile(x,y), x, y);
			}
			else
			{
				System.out.println("Fuckkkkkkkkk");
			}
				
		}
		else {
			System.out.print("sory you can not place ExplodingTile at any Tiles on the map");
		}
	
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
		System.out.println("--Congratulation! You get one barricade--");
		e.setBarricade(e.getHaveBaricade() + 1);
	}

	public static void removeBarricade() {
		// check have barricade in map
		// get input click on barricade tile
		// remove both BarricadeTile that have same index
				// change that position to white barricade
		System.out.println("--Remove one barricade--");
		if(!GameController.checkHaveBarricadeOnMap())
		{
			System.out.println("sorry there is no barricade on the map");
			return;
		}
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("input x y");
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			// scanner.nextLine();
			try {
				GameController.removeBarricade(x, y);
				break;
			} catch (removeBarricadeFail e) {
				System.out.println("please try again: " + e.message);
			}
		}
	}

	public int getSymbol() {
		return Sprites.SpecialTile;
	}

}
