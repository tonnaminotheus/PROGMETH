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
		// System.out.println("Finish Initialize");

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

	public static boolean getIsWin() {
		return isWin;
	}

	public static void setTurn(int t) {
		turn = t;
	}

	public static void spawnSpecialTile() {
		int randomX = (int) (Math.random() * 16);
		int randomY = (int) (Math.random() * 16);
		try {
			if (getCurrentMap().getEntity(randomX, randomY).isBlackTile()) {

				int[] dirX = { 0, 2, 0, -2 };
				int[] dirY = { 2, 0, -2, 0 };
				boolean check = true;
				for (int i = 0; i < 4; i++) {
					if (!GameController.getCurrentMap().getEntity(randomX + dirX[i], randomY + dirY[i]).isBlackTile()) {
						check = false;
					}
				}
				if (check) {
					System.out.println("spawnSuccess");
					getCurrentMap().removeEntity(randomX, randomY);
					getCurrentMap().addEntity(new SpecialTile(randomX, randomY), randomX, randomY);
				}
			}
		} catch (Exception e) {
		}
	}

	public static boolean checkHaveBarricadeOnMap() {
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				if (GameController.getCurrentMap().getEntity(i, j).isBarricadeTile()) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkIsPossitionOnBoard(int x, int y) {
		return (x < 17 && x >= 0) && (y < 17 && y >= 0);
	}

	public static void move(Player player, int posx, int posy, int x, int y) throws moveFail {
		// dir 0 up 1 right 2 down 3 left
		// check class is blackTile check barricade tile
		// this.setX(x); +2
		// this.setY(y); +2
		// check white tile
		if (checkIsPossitionOnBoard(x, y) && checkIsPossitionOnBoard(posx, posy)) {
			System.out.println("check pos out of board");
			boolean isWhite = GameController.getCurrentMap().getEntity((posx + x) / 2, (posy + y) / 2).isWhiteTile();
			boolean isBlack = GameController.getCurrentMap().getEntity(posx, posy).isBlackTile();
			boolean isSpecial = GameController.getCurrentMap().getEntity(posx, posy).isSpecialTile();
			boolean isInRange = (Math.abs(posx - x) <= 2 ? true : false) && (Math.abs(posy - y) <= 2 ? true : false)
					&& (posx == x || posy == y);
			if (isWhite && (isBlack || isSpecial) && isInRange) {
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
				if (now.isSpecialTile()) {
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
		} else {
			// System.out.println("check pos out of board");
			throw new moveFail("The position is out of the board");
		}
	}

	public static void removeBarricade(int x, int y) throws removeBarricadeFail {
		if (checkIsPossitionOnBoard(x, y)) {
			if (GameController.getCurrentMap().getEntity(x, y).isBarricadeTile()) {
				BarricadeTile bar1 = (BarricadeTile) getCurrentMap().getEntity(x, y);
				BarricadeTile bar2 = (BarricadeTile) getCurrentMap().getEntity(bar1.getOther().getX(),
						bar1.getOther().getY());
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
		} else {
			throw new removeBarricadeFail("The possition is out of the map");
		}
	}

	public static void addVerticalBarricade(int x, int y) throws addBarricadeFail {
		if (checkIsPossitionOnBoard(x, y)) {
			Entity upEntity = getCurrentMap().getEntity(x, y - 1);
			Entity downEntity = getCurrentMap().getEntity(x, y + 1);
			if (upEntity.getClass() == WhiteTile.class && downEntity.getClass() == WhiteTile.class) {
				getCurrentMap().removeEntity(upEntity.getX(), upEntity.getY());
				getCurrentMap().removeEntity(downEntity.getX(), downEntity.getY());
				getCurrentMap().addEntity(new BarricadeTile(x, y - 1, x, y + 1), x, y - 1);
				getCurrentMap().addEntity(new BarricadeTile(x, y + 1, x, y - 1), x, y + 1);
			} else {
				throw new addBarricadeFail("There is barricade on that tile");
			}
		} else {
			throw new addBarricadeFail("The position is out of the map");
		}
	}

	public static void addHorizontalBarricade(int x, int y) throws addBarricadeFail {
		if (checkIsPossitionOnBoard(x, y)) {
			Entity leftEntity = getCurrentMap().getEntity(x - 1, y);
			Entity rightEntity = getCurrentMap().getEntity(x + 1, y);
			if (leftEntity.getClass() == WhiteTile.class && rightEntity.getClass() == WhiteTile.class) {
				getCurrentMap().removeEntity(leftEntity.getX(), leftEntity.getY());
				getCurrentMap().removeEntity(rightEntity.getX(), rightEntity.getY());
				getCurrentMap().addEntity(new BarricadeTile(x - 1, y, x + 1, y), x - 1, y);
				getCurrentMap().addEntity(new BarricadeTile(x + 1, y, x - 1, y), x + 1, y);
			} else {
				throw new addBarricadeFail("There is barricade on that tile");
			}
		} else {
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
			Coordinate topQueue = q.get(0);
			int[] dirx = { 0, 2, 0, -2 };
			int[] diry = { 2, 0, -2, 0 };
			if (topQueue.getX() == finish) {
				return true;
			}
			for (int i = 0; i < 4; i++) {
				if (checkIsPossitionOnBoard(topQueue.getX() + dirx[i],topQueue.getY() + diry[i])) {
					boolean isBlackTile = getCurrentMap().getEntity(topQueue.getX() + dirx[i], topQueue.getY() + diry[i])
							.isBlackTile();
					boolean isSpecialTile = getCurrentMap().getEntity(topQueue.getX() + dirx[i], topQueue.getY() + diry[i])
							.isSpecialTile();
					boolean isWhiteTile = getCurrentMap().getEntity(topQueue.getX() + dirx[i] / 2, topQueue.getY() + diry[i] / 2)
							.isWhiteTile();
					if (!visit[topQueue.getX() + dirx[i]][topQueue.getY() + diry[i]] && (isWhiteTile && (isBlackTile || isSpecialTile))) {
						q.add(new Coordinate(topQueue.getX() + dirx[i], topQueue.getY() + diry[i]));
						visit[topQueue.getX() + dirx[i]][topQueue.getY() + diry[i]] = true;
					}
				}
			}
			q.remove(0);
		}
		return false;

	}

	public static void printmapcheck() {
		for (int j = 16; j >= 0; j--) {
			for (int i = 0; i < 17; i++) {
				if (getCurrentMap().getEntity(i, j).isBarricadeTile()) {
					System.out.print("B");
				} else if (getCurrentMap().getEntity(i, j).isBlackTile()) {
					System.out.print("_");
				} else if (getCurrentMap().getEntity(i, j).isPlayer()) {
					System.out.print("P");
				} else if (getCurrentMap().getEntity(i, j).isWhiteTile()) {
					System.out.print(" ");
				} else {
					System.out.print("S");
				}
			}
			System.out.println("");
		}
	}

}
