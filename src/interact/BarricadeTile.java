package interact;

import logic.Sprites;
import logic.addBarricadeFail;
import logic.removeBarricadeFail;
import entity.base.Entity;
import logic.GameController;

public class BarricadeTile extends Tile {

	public int otherx;
	public int othery;

	// public Coordinate other1;
	// public Coordibnate other2;
	public BarricadeTile(int x, int y, int X, int Y) {
		super(x, y);
		this.otherx = X;
		this.othery = Y;
		// this.other1=other1;
		// this.other2=other2;
	}

	public int getSymbol() {
		return Sprites.Barricade;
	}
}
