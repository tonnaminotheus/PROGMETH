package entity.base;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x,int y)
	{
		// TODO Auto-generated constructor stub
		this.setX(x);
		this.setY(y);
	}
	
	public void setX(int x)
	{
		this.x=x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
