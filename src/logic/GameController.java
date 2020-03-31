package logic;

import interact.BarricadeTile;
import interact.BlackTile;
import interact.Player;
import interact.SpecialTile;
import interact.WhiteTile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import entity.base.Coordinate;
import entity.base.Entity;

public class GameController {

	public static GameMap gameMap;

	private static Player player1;
	private static Player player2;
	private static int player1index;
	private static int player2index;
	private static int turn = 1;
	private static boolean isWin;

	public static void IntializeMap() {
		setIsWin(false);
		player1 = new Player(0, 8, player1index);
		player2 = new Player(16, 8, player2index);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
		gameMap = new GameMap();
		gameMap.removeEntity(0, 8);
		gameMap.removeEntity(16, 8);
		gameMap.addEntity(player1, 0, 8);
		gameMap.addEntity(player2, 16, 8);
		//System.out.println("Finish Initialize");

	}

	public static Player getPlayer1() {
		return GameController.player1;
	}

	public static Player getPlayer2() {
		return GameController.player2;
	}

	public static int getTurn() {
		return turn;
	}

	public static GameMap getCurrentMap() {
		// TODO Auto-generated method stub
		return gameMap;
	}

	public static void setIsWin(boolean stat) {
		isWin = stat;
	}

	public static boolean getIs_win() {
		return isWin;
	}

	public static void setTurn(int t) {
		turn = t;
	}

	public static void spawnSpecialTile() {
		int randomX = (int) (Math.random() * 16);
		int randomY = (int) (Math.random() * 16);
		if (getCurrentMap().getEntity(randomX, randomY).is_BlackTile()) {

			int[] dirX = { 0, 2, 0, -2 };
			int[] dirY = { 2, 0, -2, 0 };
			boolean check = true;
			for (int i = 0; i < 4; i++) {
				if (!GameController.getCurrentMap().getEntity(randomX + dirX[i], randomY + dirY[i]).is_BlackTile()) {
					check = false;
				}
			}
			if (check) {
				System.out.println("spawnSuccess");
				getCurrentMap().removeEntity(randomX, randomY);
				getCurrentMap().addEntity(new SpecialTile(randomX, randomY), randomX, randomY);
			}
		}
	}

	public static void move(Player player, int posx, int posy, int x, int y) throws moveFail {
		// dir 0 up 1 right 2 down 3 left
		// check class is blackTile check barricade tile
		// this.setX(x); +2
		// this.setY(y); +2
		// check white tile
		try {
			boolean isWhite = GameController.getCurrentMap().getEntity((posx + x) / 2, (posy + y) / 2).is_WhiteTile();
			boolean isBlack = GameController.getCurrentMap().getEntity(posx, posy).is_BlackTile();
			boolean isSpecial = GameController.getCurrentMap().getEntity(posx, posy).is_SpecialTile();
			boolean t4 = (Math.abs(posx - x) <= 2 ? true : false) && (Math.abs(posy - y) <= 2 ? true : false)
					&& (posx == x || posy == y);
			if (isWhite && (isBlack || isSpecial) && t4) {
				// change variable name
				int playerX = player.getX();
				int playerY = player.getY();
				player.setX(posx);
				player.setY(posy);
				Entity now = getCurrentMap().getEntity(posx, posy);
				getCurrentMap().removeEntity(posx, posy);
				getCurrentMap().removeEntity(playerX, playerY);
				getCurrentMap().addEntity(player, posx, posy);
				getCurrentMap().addEntity(new BlackTile(playerX, playerY), playerX, playerY);
				if (now.is_SpecialTile()) {
					SpecialTile.getAction(player);
				}
			} else {
				if (!isWhite)
					throw new moveFail("blocked by barricade");
				else if (!(isBlack || isSpecial))
					throw new moveFail("you cannot move to the chosen tile");
				else
					throw new moveFail("The position is out of movable range");

			}
		} catch (moveFail e) {
			throw new moveFail("The position is out of the board");
		}
	}

	public static void removeBarricade(int x, int y) throws removeBarricadeFail {
		try {
			if (GameController.getCurrentMap().getEntity(x, y).is_BarricadeTile()) {
				BarricadeTile bar1 = (BarricadeTile) getCurrentMap().getEntity(x, y);
				BarricadeTile bar2 = (BarricadeTile) getCurrentMap().getEntity(bar1.otherx, bar1.othery);
				int x1 = bar1.getX();
				int y1 = bar1.getY();
				int x2 = bar2.getX();
				int y2 = bar2.getY();
				getCurrentMap().removeEntity(x1, y1);
				getCurrentMap().removeEntity(x2, y2);
				getCurrentMap().addEntity(new WhiteTile(x1, y1), x1, y1);
				getCurrentMap().addEntity(new WhiteTile(x2, y2), x2, y2);
			} else {
				throw new removeBarricadeFail("The choosen possition is not BarricadeTile");
			}
		} catch (Exception e) {
			throw new removeBarricadeFail("The possition is out of the map");
		}
	}

	public static void addVerticalBarricade(int x, int y) throws addBarricadeFail {
		try {
			Entity e1 = getCurrentMap().getEntity(x, y - 1);
			Entity e2 = getCurrentMap().getEntity(x, y + 1);
			if (e1.getClass() == WhiteTile.class && e2.getClass() == WhiteTile.class) {
				getCurrentMap().removeEntity(e1.getX(), e1.getY());
				getCurrentMap().removeEntity(e2.getX(), e2.getY());
				getCurrentMap().addEntity(new BarricadeTile(x, y - 1, x, y + 1), x, y - 1);
				getCurrentMap().addEntity(new BarricadeTile(x, y + 1, x, y - 1), x, y + 1);
			} else {
				throw new addBarricadeFail("There is barricade on that tile");
			}
		} catch (Exception e) {
			throw new addBarricadeFail("The position is out of the map");
		}
	}

	public static void addHorisontalBarricade(int x, int y) throws addBarricadeFail {
		try {
			Entity e1 = getCurrentMap().getEntity(x - 1, y);
			Entity e2 = getCurrentMap().getEntity(x + 1, y);
			if (e1.getClass() == WhiteTile.class && e2.getClass() == WhiteTile.class) {
				getCurrentMap().removeEntity(e1.getX(), e1.getY());
				getCurrentMap().removeEntity(e2.getX(), e2.getY());
				getCurrentMap().addEntity(new BarricadeTile(x - 1, y, x + 1, y), x - 1, y);
				getCurrentMap().addEntity(new BarricadeTile(x + 1, y, x - 1, y), x + 1, y);
			} else {
				throw new addBarricadeFail("There is barricade on that tile");
			}
		} catch (Exception e) {
			throw new addBarricadeFail("The position is out of the map");
		}
	}

	public static boolean checkbfs(Player player, int finish) {
		int playerX = player.getX();
		int playerY = player.getY();
		ArrayList<Coordinate> q = new ArrayList<>();
		boolean[][] visit = new boolean[17][17];
		visit[playerX][playerY] = true;
		q.add(new Coordinate(playerX, playerY));
		while (!q.isEmpty()) {
			Coordinate aa = q.get(0);
			int[] dirx = { 0, 2, 0, -2 };
			int[] diry = { 2, 0, -2, 0 };
			if (aa.getX() == finish) {
				return true;
			}
			for (int i = 0; i < 4; i++) {
				if (0 <= aa.getX() + dirx[i] && aa.getX() + dirx[i] < 17 && 0 <= aa.getY() + diry[i]
						&& aa.getY() + diry[i] < 17) {
					boolean t1 = getCurrentMap().getEntity(aa.getX() + dirx[i], aa.getY() + diry[i]).is_BlackTile();
					boolean t2 = getCurrentMap().getEntity(aa.getX() + dirx[i], aa.getY() + diry[i]).is_SpecialTile();
					boolean t3 = getCurrentMap().getEntity(aa.getX() + dirx[i] / 2, aa.getY() + diry[i] / 2)
							.is_WhiteTile();
					if (!visit[aa.getX() + dirx[i]][aa.getY() + diry[i]] && (t3 && (t1 || t2))) {
						q.add(new Coordinate(aa.getX() + dirx[i], aa.getY() + diry[i]));
						visit[aa.getX() + dirx[i]][aa.getY() + diry[i]] = true;
					}
				}
			}
			q.remove(0);
		}
		return false;

	}
	
	public static void printmapcheck()
	{
		for (int j = 16; j >= 0; j--) {
			for (int i = 0; i < 17; i++) {
				if(getCurrentMap().getEntity(i, j).is_BarricadeTile())
				{
					System.out.print("B");
				}
				else if(getCurrentMap().getEntity(i, j).is_BlackTile())
				{
					System.out.print("_");
				}
				else if(getCurrentMap().getEntity(i, j).is_Player())
				{
					System.out.print("P");
				}
				else if(getCurrentMap().getEntity(i, j).is_WhiteTile())
				{
					System.out.print(" ");
				}
				else
				{
					System.out.print("S");
				}
			}
			System.out.println("");
		}
	}

}
