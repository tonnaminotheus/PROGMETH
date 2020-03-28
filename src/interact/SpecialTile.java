package interact;


import logic.Sprites;
import interact.Player;
import logic.GameController;



public class SpecialTile extends Tile{

	
	
	public SpecialTile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public static void getAction(Player e)
	{
		double random=Math.random();//random later
		if(random<=0.33)
		{
			move2(e);
		}
		else if(random<=0.66)
		{
			addBarricade(e);
		}
		else
		{
			removeBarricade();
		}
	}
	
	public static void move2(Player e)
	{
		double random=Math.random();//random later
		int dir=(int)(random*100-1)/25;
		int [] dirx= {0,1,0,-1};
		int [] diry= {-1,0,1,0};
		GameController.move(e,e.getX()+2*dirx[dir],e.getY()+2*diry[dir]);
		GameController.move(e,e.getX()+2*dirx[dir],e.getY()+2*diry[dir]);
	}
	
	public static void addBarricade(Player e)
	{
		e.setBarricade(e.getHaveBaricade()+1);
	}
	
	public static void removeBarricade()
	{
		//check have barricade in map
		//get input click on barricade tile
		//remove both BarricadeTile that have same index
		//chage that possition to white barricade
	}
	
	public int getSymbol()
	{
		return Sprites.SpecialTile;
	}
	
	
	
	
	
	
}
