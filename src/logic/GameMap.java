package logic;

import java.util.ArrayList;

import interact.BarricadeTile;
import interact.BlackTile;
import interact.SpecialTile;
import interact.Tile;
import interact.WhiteTile;
import entity.base.Entity;

public class GameMap {
	private Cell[][] cellmap;
	private int width;
	private int height;

	private ArrayList<Entity> allEntity;

	public GameMap(int column, int row) {

		allEntity = new ArrayList<Entity>();

		setWidth(column);
		setHeight(row);

		cellmap = new Cell[row][column];
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				cellmap[i][j] = new Cell();
			}
		}
	}

	public GameMap() {

		allEntity = new ArrayList<Entity>();
		cellmap = new Cell[100][100];
		int column = 17;
		int row = 17;

		setWidth(column);
		setHeight(row);
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				cellmap[i][j] = new Cell();
				if (i == 8 && j == 8) {
					addEntity(new SpecialTile(i, j), i, j);
				} else if (i % 2 == 0 && j % 2 == 0) {
					addEntity(new BlackTile(i, j), i, j);
				} else {
					addEntity(new WhiteTile(i, j), i, j);
				}

			}

		}

	}

	public void printMap() {
		for (Cell[] row : cellmap) {
			String rowstring = "";
			for (Cell c : row) {
				rowstring += c.getSymbol() + " ";
			}
			System.out.println(rowstring);
		}
	}

	public Cell[][] getMap() {
		return cellmap;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean addEntity(Entity e, int x, int y) {
		allEntity.add(e);
		e.setX(x);
		e.setY(y);
		boolean b = cellmap[x][y].setEntity(e);

		return b;
	}

	public Entity getEntity(int x, int y) {
		return cellmap[x][y].getEntity();
	}

	public void removeEntity(int x, int y) {

		allEntity.remove(cellmap[x][y].getEntity());
		cellmap[x][y].removeEntity();
	}

	public ArrayList<Entity> getAllEntity() {
		return allEntity;
	}
}