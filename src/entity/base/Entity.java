package entity.base;

import interact.BarricadeTile;
import interact.BlackTile;
import interact.Player;
import interact.SpecialTile;
import interact.WhiteTile;
import logic.GameController;

public abstract class Entity {
	private int x;
	private int y;
	
	
	public Entity(int x,int y)
	{
		this.setX(x);
		this.setY(y);
	}
	
	
	public abstract int getSymbol();
	
	public void setX(int x)
	{
		this.x=x;
	}
	
	public void setY(int y)
	{
		this.y=y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public boolean is_Player()
	{
		return this.getClass()==Player.class;
	}
	
	public boolean is_BarricadeTile()
	{
		return this.getClass()==BarricadeTile.class;
	}
	
	public boolean is_WhiteTile()
	{
		return this.getClass()==WhiteTile.class;
	}
	
	public boolean is_BlackTile()
	{
		return this.getClass()==BlackTile.class;
	}
	
	public boolean is_SpecialTile()
	{
		return this.getClass()==SpecialTile.class;
	}
	
	
	
}
