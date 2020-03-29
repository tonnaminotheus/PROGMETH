package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import logic.GameController;
import logic.addBarricadeFail;
import logic.moveFail;
import logic.removeBarricadeFail;
import entity.base.Entity;
import interact.Player;

class Application {

	@Test
	void test() throws removeBarricadeFail {
		GameController.IntializeMap();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("-----------Turn " + GameController.getTurn() + " -------------");
			for (int j = 16; j >= 0; j--) {
				for (int i = 0; i < 17; i++) {
					Entity check = GameController.getCurrentMap().getEntity(i, j);
					if (check.is_BarricadeTile()) {
						System.out.print("B");
					} else if (check.is_BlackTile()) {
						System.out.print("_");
					} else if (check.is_WhiteTile()) {
						System.out.print(" ");
					} else if (check.is_SpecialTile()) {
						System.out.print("S");
					} else {
						System.out.print("P");
					}
				}
				System.out.println("");
			}
			if (GameController.getTurn() % 5 == 0) {
				GameController.spawnSpecialTile();
			}

			System.out.println("Please choose action");
			System.out.println("(1) move");
			System.out.println("(2) place barricade");
			System.out.println("(3) remove barricade");
			System.out.println("(4) pass the turn");
			System.out.println("(999) Exit");
			String choice = scanner.nextLine();
			String result = "";
			System.out.println(choice);
			if (choice.equals("1")) {
				int x, y, posx, posy;
				if (GameController.getTurn() % 2 == 1) {
					x = GameController.getPlayer1().getX();
					y = GameController.getPlayer1().getY();
					System.out.println("Player1 at possition: " + x + " " + y);
					while (true) {
						System.out.println("input x y");
						posx = scanner.nextInt();
						posy = scanner.nextInt();
						try {
							GameController.move(GameController.getPlayer1(), posx, posy, x, y);
							if (posx >= 12)
								System.out.println("Player 1 is almost reach the goal");
							break;
						} catch (moveFail e) {
							System.out.println("Please try again: " + e.message);
						}
					}
				} else {
					x = GameController.getPlayer2().getX();
					y = GameController.getPlayer2().getY();
					System.out.println("Player2 at possition: " + x + " " + y);
					while (true) {
						posx = scanner.nextInt();
						posy = scanner.nextInt();

						try {
							GameController.move(GameController.getPlayer2(), posx, posy, x, y);
							if (posx <= 4)
								System.out.println("Player 2 is almost reach the goal");
							break;
						} catch (moveFail e) {
							System.out.println("Please try again: " + e.message);
						}
					}
				}
				scanner.nextLine();

			} else if (choice.equals("2")) {
				if (GameController.getTurn() % 2 == 1)
					if (GameController.getPlayer1().getHaveBaricade() == 0) {
						System.out.println("you dont have any barricade left");
						continue;
					} else
						GameController.getPlayer1().setBarricade(GameController.getPlayer1().getHaveBaricade() - 1);
				else if (GameController.getPlayer2().getHaveBaricade() == 0) {
					System.out.println("you dont have any barricade left");
					continue;
				} else
					GameController.getPlayer2().setBarricade(GameController.getPlayer2().getHaveBaricade() - 1);

				// System.out.println(GameController.get_Player1().getHaveBaricade()+"
				// "+GameController.get_Player2().getHaveBaricade());

				System.out.println("(1) place horrisontal");
				System.out.println("(2) place vertical");
				while (true) {
					String ch = scanner.nextLine();
					System.out.println("input x y");
					int x = scanner.nextInt();
					int y = scanner.nextInt();
					// scanner.nextLine();
					if (ch.equals("1")) {
						try {
							GameController.addHorisontalBarricade(x, y);
							if (!GameController.checkbfs(
									GameController.getTurn() % 2 == 1 ? GameController.getPlayer1()
											: GameController.getPlayer2(),
									GameController.getTurn() % 2 == 1 ? 16 : 0)) {
								GameController.removeBarricade(x - 1, y);
								System.out.println("you can not place barricade there please try again");
								continue;
							}
							else
								break;
						} catch (addBarricadeFail e) {
							System.out.println("please try again: " + e.message);
						}
					} else {
						try {
							GameController.addVerticalBarricade(x, y);
							if (!GameController.checkbfs(
									GameController.getTurn() % 2 == 1 ? GameController.getPlayer1()
											: GameController.getPlayer2(),
									GameController.getTurn() % 2 == 1 ? 16 : 0)) {
								GameController.removeBarricade(x, y - 1);
								System.out.println("you can not place barricade there please try again");
								continue;
							}
							else
								break;
						} catch (addBarricadeFail e) {
							System.out.println("please try again: " + e.message);
						}
					}
				}
			}else if(choice.equals("3"))

	{
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
	}else if(choice.equals("999"))
	{
		System.out.println("done");
		break;
	}

	if(GameController.getPlayer1().getX()==16)
	{
		result = "player1 win";
		GameController.setIsWin(true);
	}else if(GameController.getPlayer2().getX()==0)
	{
		result = "player2 win";
		GameController.setIsWin(true);
	}

	if(GameController.getIs_win())
	{
		System.out.println(result);
		break;
	}GameController.setTurn(GameController.getTurn()+1);
}

}

}
