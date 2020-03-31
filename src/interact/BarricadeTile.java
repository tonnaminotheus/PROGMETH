package interact;

import logic.Sprites;
import logic.addBarricadeFail;
import logic.removeBarricadeFail;
import entity.base.Coordinate;
import entity.base.Entity;
import logic.GameController;

public class BarricadeTile extends Tile {

	private Coordinate other;

	// public Coordinate other1;
	// public Coordinate other2;
	public BarricadeTile(int x, int y, int X, int Y) {
		super(x, y);
		this.other = new Coordinate(x,y);
		// this.other1=other1;
		// this.other2=other2;
	}
	
	public Coordinate getOther()
	{
		return this.other;
	}

	public int getSymbol() {
		return Sprites.Barricade;
	}
}
