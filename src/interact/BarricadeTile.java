package interact;

import logic.Sprites;
import logic.addBarricadeFail;
import logic.removeBarricadeFail;
import entity.base.Coordinate;
import entity.base.Entity;
import logic.GameController;

public class BarricadeTile extends Tile {

	private Coordinate other1;
	private Coordinate other2;
	// public Coordinate other1;
	// public Coordinate other2;
	public BarricadeTile(int x, int y, int X1, int Y1,int X2,int Y2) {
		super(x, y);
		this.other1 = new Coordinate(X1,Y1);
		this.other2 = new Coordinate(X2,Y2);
		// this.other1=other1;
		// this.other2=other2;
	}
	
	public Coordinate getOther1()
	{
		return this.other1;
	}
	
	public Coordinate getOther2()
	{
		return this.other2;
	}

	public int getSymbol() {
		return Sprites.Barricade;
	}
}
