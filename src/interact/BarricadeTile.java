package interact;

import logic.Sprites;
import logic.addBarricadeFail;
import logic.removeBarricadeFail;
import entity.base.Entity;
import logic.GameController;

public class BarricadeTile extends Tile{
	
	public int otherx;
	public int othery;
	public BarricadeTile(int x, int y,int X,int Y) {
		super(x, y);
		otherx=X;
		othery=Y;
	}
	
	public int getSymbol()
	{
		return Sprites.Barricade;
	}
}
