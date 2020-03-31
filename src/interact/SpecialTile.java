package interact;

import logic.Sprites;
import logic.moveFail;
import logic.removeBarricadeFail;

import java.util.Scanner;

import interact.Player;
import logic.GameController;

public class SpecialTile extends Tile {

	public SpecialTile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public static void getAction(Player e) throws moveFail {
		int random = (int) (Math.random() * 10);
		if (random % 10 == 0) {
			move1(e);
		} else if (random % 10 == 1) {
			addBarricade(e);
		} else if (random % 10 == 2)
			move1(e.getOtherPlayer());
		else {
			removeBarricade();
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
