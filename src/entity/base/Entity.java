package entity.base;

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


public abstract class Entity {
	private int x;
	private int y;

	public Entity(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

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
		return (this.getClass() == SpecialTile.class)
				||(this.getClass()==RandomTile.class)
				||(this.getClass()==RemoveAllBarricade.class)
				||(this.getClass()==RemoveAllSpecialTile.class)
				||(this.getClass()==AddBomb.class)
				||(this.getClass()==GetBarricade.class)
				||(this.getClass()==GetHeal.class)
				||(this.getClass()==MovePlayer.class)
				||(this.getClass()==MoveOtherPlayer.class);
	}
	
	public boolean isExplodingTile() {
		return this.getClass() == ExplodingTile.class;
	}

}
