package logic;

import interact.BarricadeTile;
import interact.BlackTile;
import interact.Player;
import interact.SpecialTile;
import interact.WhiteTile;

import java.util.ArrayList;

import entity.base.Coordinate;
import entity.base.Entity;

public class GameController {

	public static GameMap gameMap;

	private static Player player1;
	private static Player player2;
	private static int player1index;
	private static int player2index;
	private static int turn = 1;
	private static boolean is_win;

	public static void IntializeMap() {
		setGameWin(false);
		player1 = new Player(0, 8, player1index);
		player2 = new Player(16, 8, player2index);
		gameMap = new GameMap();
		gameMap.removeEntity(0, 8);
		gameMap.removeEntity(16, 8);
		gameMap.addEntity(player1, 0, 8);
		gameMap.addEntity(player2, 16, 8);
		System.out.println("Finish Initialize");

	}

	public static Player get_Player1() {
		return GameController.player1;
	}

	public static Player get_Player2() {
		return GameController.player2;
	}

	public static int get_turn() {
		return turn;
	}

	public static GameMap getCurrentMap() {
		// TODO Auto-generated method stub
		return gameMap;
	}

	public static void setGameWin(boolean stat) {
		is_win = stat;
	}

	public static boolean is_win() {
		return is_win;
	}

	public static void set_turn(int t) {
		turn = t;
	}

	public static boolean move(Player player, int x, int y) {
		// dir 0 up 1 right 2 down 3 left
		// check class is blackTile check barricade tile
		// this.setX(x); +2
		// this.setY(y); +2
		// check white tile
		if (getCurrentMap().getEntity(x, y).is_BlackTile() || getCurrentMap().getEntity(x, y).is_SpecialTile()) {
			// change varrible name
			int xx = player.getX();
			int yy = player.getY();
			player.setX(x);
			player.setY(y);
			Entity now = getCurrentMap().getEntity(x, y);
			getCurrentMap().removeEntity(x, y);
			getCurrentMap().removeEntity(xx, yy);
			getCurrentMap().addEntity(player, x, y);
			getCurrentMap().addEntity(new BlackTile(xx, yy), xx, yy);
			if (now.is_SpecialTile()) {
				SpecialTile.getAction(player);
			}
			return true;
		} else {
			return false;
		}
	}

	public static boolean removeBarricade(int x, int y) {
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
			return true;
		} else {
			return false;
		}
	}

	public static boolean addVerticalBarricade(int x, int y) {
		Entity e1 = getCurrentMap().getEntity(x, y - 1);
		Entity e2 = getCurrentMap().getEntity(x, y + 1);
		if (e1.getClass() == WhiteTile.class && e2.getClass() == WhiteTile.class) {
			getCurrentMap().removeEntity(e1.getX(), e1.getY());
			getCurrentMap().removeEntity(e2.getX(), e2.getY());
			getCurrentMap().addEntity(new BarricadeTile(x, y - 1, x, y + 1), x, y - 1);
			getCurrentMap().addEntity(new BarricadeTile(x, y + 1, x, y - 1), x, y + 1);
			return true;
		} else {
			return false;
		}
	}

	public static boolean addHorisontalBarricade(int x, int y) {
		Entity e1 = getCurrentMap().getEntity(x - 1, y);
		Entity e2 = getCurrentMap().getEntity(x + 1, y);
		if (e1.getClass() == WhiteTile.class && e2.getClass() == WhiteTile.class) {
			getCurrentMap().removeEntity(e1.getX(), e1.getY());
			getCurrentMap().removeEntity(e2.getX(), e2.getY());
			getCurrentMap().addEntity(new BarricadeTile(x - 1, y, x + 1, y), x - 1, y);
			getCurrentMap().addEntity(new BarricadeTile(x + 1, y, x - 1, y), x + 1, y);
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkbfs(Player player, int finish) {
		int xx = player.getX();
		int yy = player.getY();
		ArrayList<Coordinate> q = new ArrayList<>();
		boolean[][] visit = new boolean[17][17];
		visit[xx][yy] = true;
		q.add(new Coordinate(xx, yy));
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

}
