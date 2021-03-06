package interact;

import entity.base.Coordinate;

public class BarricadeTile extends Tile {

	private Coordinate other1;
	private Coordinate other2;
	public BarricadeTile(int x, int y, int X1, int Y1,int X2,int Y2) {
		super(x, y);
		this.other1 = new Coordinate(X1,Y1);
		this.other2 = new Coordinate(X2,Y2);
	}
	
	public Coordinate getOther1()
	{
		return this.other1;
	}
	
	public Coordinate getOther2()
	{
		return this.other2;
	}
}
