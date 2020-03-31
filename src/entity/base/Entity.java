package entity.base;

import interact.BarricadeTile;
import interact.BlackTile;
import interact.ExplodingTile;
import interact.Player;
import interact.SpecialTile;
import interact.WhiteTile;
import logic.GameController;

public abstract class Entity {
	private int x;
	private int y;

	public Entity(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public abstract int getSymbol();

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean isPlayer() {
		return this.getClass() == Player.class;
	}

	public boolean isBarricadeTile() {
		return this.getClass() == BarricadeTile.class;
	}

	public boolean isWhiteTile() {
		return this.getClass() == WhiteTile.class;
	}

	public boolean isBlackTile() {
		return this.getClass() == BlackTile.class;
	}

	public boolean isSpecialTile() {
		return this.getClass() == SpecialTile.class;
	}
	
	public boolean isExplodingTile() {
		return this.getClass() == ExplodingTile.class;
	}

}
