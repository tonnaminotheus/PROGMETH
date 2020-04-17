package logic;

import interact.AddBomb;
import interact.BarricadeTile;
import interact.BlackTile;
import interact.ExplodingTile;
import interact.GetBarricade;
import interact.GetHeal;
import interact.MoveOtherPlayer;
import interact.MovePlayer;
import interact.Player;
import interact.RandomTile;
import interact.RemoveAllBarricade;
import interact.RemoveAllSpecialTile;
import interact.SpecialTile;
import interact.WhiteTile;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;

import application.Main;
import entity.base.Coordinate;
import entity.base.Entity;
import gui.ControlPane;
import gui.ControlPane2;
import gui.FieldCell;
import gui.FieldPane;

public class GameController {

	public static GameMap gameMap;

	private static Player player1;
	private static Player player2;
	private static int turn = 1;
	private static boolean isWin;

	public static void IntializeMap(int player1index, int player2index) {
		setIsWin(false);
		player1 = new Player(0, 8, player1index,new Coordinate(0,8),1,16);
		player2 = new Player(16, 8, player2index,new Coordinate(16,8),2,16);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
		gameMap = new GameMap();
		gameMap.removeEntity(0, 8);
		gameMap.removeEntity(16, 8);
		gameMap.addEntity(player1, 0, 8);
		gameMap.addEntity(player2, 16, 8);
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
	
	public static void increaseTurn() {
		GameController.setTurn(GameController.getTurn()+1);
	}

	public static SpecialTile spawnSpecialTile() {
		ArrayList<BlackTile> spawnTile = gameMap.getSpawnTile();
		int random = (int) (Math.random() * 100) % spawnTile.size();
		int randomX = spawnTile.get(random).getX();
		int randomY = spawnTile.get(random).getY();
		int ran = (int) (Math.random() * 100) % 8;
		if (checkIsPossitionOnBoard(randomX, randomY)) {
			if (getCurrentMap().getEntity(randomX, randomY).isBlackTile()) {
				
				Entity e = null;
				if(ran==0)
					e=new RandomTile(randomX, randomY);
				else if(ran==1)
					e=new RemoveAllBarricade(randomX, randomY);
				else if(ran==2)
					e=new RemoveAllSpecialTile(randomX, randomY);
				else if(ran==3)
					e=new AddBomb(randomX, randomY);
				else if(ran==4)
					e=new GetBarricade(randomX, randomY);
				else if(ran==5)
					e=new GetHeal(randomX, randomY);
				else if(ran==6)
					e=new MovePlayer(randomX, randomY);
				else if(ran==7)
					e=new MoveOtherPlayer(randomX, randomY);
				
				getCurrentMap().removeEntity(randomX, randomY);
				getCurrentMap().addEntity(e,randomX,randomY);
				Main.fieldPane.setFieldCell(new FieldCell(e),Main.fieldPane);
				return (SpecialTile) e;
			}
		}
		return null;
	}
	
	public static void placeBomb(int x,int y) throws Exception{
		if(GameController.getCurrentMap().getEntity(x, y).isBlackTile()&&x!=16&&x!=0) {
			ExplodingTile e=new ExplodingTile(x,y);
			GameController.getCurrentMap().removeEntity(x, y);
			GameController.getCurrentMap().addEntity(e, x, y);
			Main.fieldPane.setFieldCell(new FieldCell(e),Main.fieldPane);
			String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 placed bomb"
					: "Player 2 placed bomb";
			ControlPane.setNoti(playermessage);
			ControlPane.labelUpdate();
			ControlPane2.labelUpdate();
			MediaPlayer operate;
			Media musicFile=new Media(ClassLoader.getSystemResource("FuzzyBeep.mp3").toString());
			operate = new MediaPlayer(musicFile);
			operate.setVolume(0.05);
			operate.setAutoPlay(true);
			ControlPane2.labelUpdate();
		}else {
			String playermessage = "can not place bomb there";
			ControlPane.setNoti(playermessage);
			ControlPane.labelUpdate();
			ControlPane2.labelUpdate();
			throw new Exception("can not place bomb there");
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
		if (checkIsPossitionOnBoard(x, y) && checkIsPossitionOnBoard(posx, posy)) {
			boolean isWhite = GameController.getCurrentMap().getEntity((posx + x) / 2, (posy + y) / 2).isWhiteTile();
			boolean isBlack = GameController.getCurrentMap().getEntity(posx, posy).isBlackTile();
			boolean isExplodingTile = GameController.getCurrentMap().getEntity(posx, posy).isExplodingTile();
			boolean isSpecial = GameController.getCurrentMap().getEntity(posx, posy).isSpecialTile();
			boolean isInRange = (Math.abs(posx - x) <= 2 ? true : false) && (Math.abs(posy - y) <= 2 ? true : false)
					&& (posx == x || posy == y);
			if (isWhite && (isBlack || isSpecial||isExplodingTile) && isInRange) {
				// change variable name
				int playerX = player.getX();
				int playerY = player.getY();
				player.setX(posx);
				player.setY(posy);
				Entity now = getCurrentMap().getEntity(posx, posy);
				
				
				
				//Main.fieldPane.set
				
				
				
				
				
				
				
				getCurrentMap().removeEntity(posx, posy);
				getCurrentMap().removeEntity(playerX, playerY);
				getCurrentMap().addEntity(player, posx, posy);
				BlackTile b =new BlackTile(playerX, playerY);
				getCurrentMap().addEntity(b, playerX, playerY);
				FieldCell pp = new FieldCell(player);
				
				Main.fieldPane.setFieldCell(pp,Main.fieldPane);
				PathTransition pathTransition = new PathTransition();
				pathTransition.setDuration(Duration.millis(1000));
				Line path = new Line((y-posy)*40+30,(x-posx)*40+30,30,30);
				pathTransition.setPath(path);
				pathTransition.setNode(pp);
				pathTransition.play();
				
				
				
				Main.fieldPane.setFieldCell(new FieldCell(b),Main.fieldPane);
				Main.fieldPane.setFieldCell(pp,Main.fieldPane);
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 moved"
						: "Player 2 moved";
				
				
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
				
				if (now.isSpecialTile()) {
					MediaPlayer operate;
					Media musicFile=new Media(ClassLoader.getSystemResource("PinDrop.mp3").toString());
					operate = new MediaPlayer(musicFile);
					operate.setVolume(0.3);
					operate.setAutoPlay(true);
					((SpecialTile)now).getAction(player);
					ControlPane2.labelUpdate();
				}
				else if(now.isExplodingTile()){
					MediaPlayer operate;
					Media musicFile=new Media(ClassLoader.getSystemResource("Bomb.mp3").toString());
					operate = new MediaPlayer(musicFile);
					operate.setVolume(0.05);
					operate.setAutoPlay(true);
					((ExplodingTile)now).getAction(player);
					ControlPane2.labelUpdate();
				}else {
					MediaPlayer operate;
					Media musicFile=new Media(ClassLoader.getSystemResource("ButtonPush.mp3").toString());
					operate = new MediaPlayer(musicFile);
					operate.setVolume(0.05);
					operate.setAutoPlay(true);
					ControlPane2.labelUpdate();
				}
			} else {
				String playermessage = "you cannot move there";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
				throw new moveFail("you cannot move there");
			}
		} else {
			throw new moveFail("The position is out of the board");
		}
	}

	public static void removeBarricade(int x, int y) throws removeBarricadeFail {
		if (checkIsPossitionOnBoard(x, y)) {
			if (GameController.getCurrentMap().getEntity(x, y).isBarricadeTile()) {
				BarricadeTile bar1 = (BarricadeTile) getCurrentMap().getEntity(x, y);
				BarricadeTile bar2 = (BarricadeTile) getCurrentMap().getEntity(bar1.getOther1().getX(),
						bar1.getOther1().getY());
				BarricadeTile bar3 = (BarricadeTile) getCurrentMap().getEntity(bar1.getOther2().getX(),
						bar1.getOther2().getY());
				int x1 = bar1.getX();
				int y1 = bar1.getY();
				int x2 = bar2.getX();
				int y2 = bar2.getY();
				int x3 = bar3.getX();
				int y3 = bar3.getY();
				getCurrentMap().removeEntity(x1, y1);
				getCurrentMap().removeEntity(x2, y2);
				getCurrentMap().removeEntity(x3, y3);
				WhiteTile w1=new WhiteTile(x1, y1);
				WhiteTile w2=new WhiteTile(x2, y2);
				WhiteTile w3=new WhiteTile(x3, y3);
				getCurrentMap().addEntity(w1, x1, y1);
				getCurrentMap().addEntity(w2, x2, y2);
				getCurrentMap().addEntity(w3, x3, y3);
				Main.fieldPane.setFieldCell(new FieldCell(w1),Main.fieldPane);
				Main.fieldPane.setFieldCell(new FieldCell(w2),Main.fieldPane);
				Main.fieldPane.setFieldCell(new FieldCell(w3),Main.fieldPane);
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 removed barricade"
						: "Player 2 removed barricade";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			} else {
				String playermessage = "The chosen position is not BarricadeTile";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
				throw new removeBarricadeFail("The chosen position is not BarricadeTile");
			}
		} else {
			throw new removeBarricadeFail("The position is out of the map");
		}
	}

	public static void addVerticalBarricade(int x, int y) throws addBarricadeFail {
		if (checkIsPossitionOnBoard(x, y) && checkIsPossitionOnBoard(x, y + 1) && checkIsPossitionOnBoard(x, y - 1)) {
			Entity upEntity = getCurrentMap().getEntity(x, y - 1);
			Entity downEntity = getCurrentMap().getEntity(x, y + 1);
			Entity midEntity = getCurrentMap().getEntity(x, y);
			if (upEntity.getClass() == WhiteTile.class && downEntity.getClass() == WhiteTile.class
					&& midEntity.getClass() == WhiteTile.class && x % 2 == 1 && y % 2 == 1) {
				getCurrentMap().removeEntity(upEntity.getX(), upEntity.getY());
				getCurrentMap().removeEntity(downEntity.getX(), downEntity.getY());
				getCurrentMap().removeEntity(midEntity.getX(), midEntity.getY());
				BarricadeTile b1=new BarricadeTile(x, y - 1, x, y + 1, x, y);
				BarricadeTile b2=new BarricadeTile(x, y + 1, x, y - 1, x, y);
				BarricadeTile b3=new BarricadeTile(x, y, x, y - 1, x, y + 1);
				getCurrentMap().addEntity(b1, x, y - 1);
				getCurrentMap().addEntity(b2, x, y + 1);
				getCurrentMap().addEntity(b3, x, y);
				Main.fieldPane.setFieldCell(new FieldCell(b1),Main.fieldPane);
				Main.fieldPane.setFieldCell(new FieldCell(b2),Main.fieldPane);
				Main.fieldPane.setFieldCell(new FieldCell(b3),Main.fieldPane);
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 place barricade"
						: "Player 2 place barricade";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			} else {
				String playermessage = "can not place barricade there";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
				throw new addBarricadeFail("There is barricade on that tile");
			}
		} else {
			String playermessage = "can not place barricade there";
			ControlPane.setNoti(playermessage);
			ControlPane.labelUpdate();
			ControlPane2.labelUpdate();
			throw new addBarricadeFail("The position is out of the map");
		}
	}

	public static void addHorizontalBarricade(int x, int y) throws addBarricadeFail {
		if (checkIsPossitionOnBoard(x, y) && checkIsPossitionOnBoard(x - 1, y) && checkIsPossitionOnBoard(x + 1, y)) {
			Entity leftEntity = getCurrentMap().getEntity(x - 1, y);
			Entity rightEntity = getCurrentMap().getEntity(x + 1, y);
			Entity midEntity = getCurrentMap().getEntity(x, y);
			if (leftEntity.getClass() == WhiteTile.class && rightEntity.getClass() == WhiteTile.class
					&& midEntity.getClass() == WhiteTile.class && x % 2 == 1 && y % 2 == 1) {
				getCurrentMap().removeEntity(leftEntity.getX(), leftEntity.getY());
				getCurrentMap().removeEntity(rightEntity.getX(), rightEntity.getY());
				getCurrentMap().removeEntity(midEntity.getX(), midEntity.getY());
				BarricadeTile b1=new BarricadeTile(x - 1, y, x + 1, y, x, y);
				BarricadeTile b2=new BarricadeTile(x + 1, y, x - 1, y, x, y);
				BarricadeTile b3=new BarricadeTile(x, y, x - 1, y, x + 1, y);
				getCurrentMap().addEntity(b1, x - 1, y);
				getCurrentMap().addEntity(b2, x + 1, y);
				getCurrentMap().addEntity(b3, x, y);
				Main.fieldPane.setFieldCell(new FieldCell(b1),Main.fieldPane);
				Main.fieldPane.setFieldCell(new FieldCell(b2),Main.fieldPane);
				Main.fieldPane.setFieldCell(new FieldCell(b3),Main.fieldPane);
				String playermessage = GameController.getTurn() % 2 == 1 ? "Player 1 place barricade"
						: "Player 2 place barricade";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
			} else {
				String playermessage = "can not place barricade there";
				ControlPane.setNoti(playermessage);
				ControlPane.labelUpdate();
				ControlPane2.labelUpdate();
				throw new addBarricadeFail("There is barricade on that tile");
			}
		} else {
			String playermessage = "can not place barricade there";
			ControlPane.setNoti(playermessage);
			ControlPane.labelUpdate();
			ControlPane2.labelUpdate();
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
		boolean ch1=false;
		boolean ch2=false;
		while (!q.isEmpty()) {
			Coordinate topQueue = q.get(0);
			int[] dirx = { 0, 2, 0, -2 };
			int[] diry = { 2, 0, -2, 0 };
			if (topQueue.getX() == finish) {
				ch1=true;
			}
			if (topQueue.getX() == player.getSpawn().getX()&&topQueue.getY()==player.getSpawn().getY()) {
				ch2=true;
			}
			for (int i = 0; i < 4; i++) {
				if (checkIsPossitionOnBoard(topQueue.getX() + dirx[i], topQueue.getY() + diry[i])) {
					boolean isBlackTile = getCurrentMap()
							.getEntity(topQueue.getX() + dirx[i], topQueue.getY() + diry[i]).isBlackTile();
					boolean isSpecialTile = getCurrentMap()
							.getEntity(topQueue.getX() + dirx[i], topQueue.getY() + diry[i]).isSpecialTile();
					boolean isExplodingTile = getCurrentMap()
							.getEntity(topQueue.getX() + dirx[i], topQueue.getY() + diry[i]).isExplodingTile();
					boolean isWhiteTile = getCurrentMap()
							.getEntity(topQueue.getX() + dirx[i] / 2, topQueue.getY() + diry[i] / 2).isWhiteTile();
					
					if (!visit[topQueue.getX() + dirx[i]][topQueue.getY() + diry[i]]
							&& (isWhiteTile && (isBlackTile || isSpecialTile||isExplodingTile))) {
						q.add(new Coordinate(topQueue.getX() + dirx[i], topQueue.getY() + diry[i]));
						visit[topQueue.getX() + dirx[i]][topQueue.getY() + diry[i]] = true;
					}
				}
			}
			q.remove(0);
		}
		return ch1&&ch2;
	}

	public static void printmapcheck() {
		for (int j = 16; j >= 0; j--) {
			System.out.print(j + "\t");
			for (int i = 0; i < 17; i++) {
				if (getCurrentMap().getEntity(i, j).isBarricadeTile()) {
					System.out.print("B");
				} else if (getCurrentMap().getEntity(i, j).isBlackTile()) {
					System.out.print("O");
				} else if (getCurrentMap().getEntity(i, j).isPlayer()) {
					System.out.print("P");
				} else if (getCurrentMap().getEntity(i, j).isWhiteTile()) {
					System.out.print(" ");
				}else if (getCurrentMap().getEntity(i, j).isExplodingTile()) {
					System.out.print("E");
				} else {
					System.out.print("S");
				}
			}
			System.out.println("");
		}
		System.out.print(" \t");
		for (int i = 0; i < 17; i++) {
			if (i < 10)
				System.out.print(i);
			else if (i == 10)
				System.out.print("A");
			else if (i == 11)
				System.out.print("B");
			else if (i == 12)
				System.out.print("C");
			else if (i == 13)
				System.out.print("D");
			else if (i == 14)
				System.out.print("E");
			else if (i == 15)
				System.out.print("F");
			else
				System.out.print("G");
		}
		System.out.println("");
		System.out.println("");
	}

}
