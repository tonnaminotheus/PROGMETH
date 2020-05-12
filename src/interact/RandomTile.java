package interact;

import application.Main;
import gui.ControlPane;
import gui.ControlPane2;
import logic.GameController;
import logic.moveFail;

public class RandomTile extends SpecialTile implements Special{

	public RandomTile(int x, int y) {
		super(x, y);
	}
	@Override
	public void getAction(Player e) throws moveFail {
		int random = (int) (Math.random() * 100)%8;
		if (random==0) {
			moveRandom(e);
			String playermessage = GameController.getTurn() % 2 == 1
					? "Player 1 Special move"
					: "Player 2 Special move";
			ControlPane.setNoti(playermessage);
		} else if (random ==1) {
			addBarricade(e);
			String playermessage = GameController.getTurn() % 2 == 1
					? "Player 1 Special get barricade"
					: "Player 2 Special get barricade";
			ControlPane.setNoti(playermessage);
		} else if (random == 2) {
			moveRandom(e.getOtherPlayer());
			String playermessage = GameController.getTurn() % 2 == 1
				? "Player 1 Special move Player2"
				: "Player 2 Special move Player1";
			ControlPane.setNoti(playermessage);
		}else if(random ==3){
			removeBarricade(e);
			String playermessage = GameController.getTurn() % 2 == 1
					? "Player 1 Special get remove barricade"
					: "Player 2 Special get remove barricade";
			ControlPane.setNoti(playermessage);
		}
		else if(random ==4){
			deleteAllSpecialTile();
			String playermessage ="remove all special tile";
			ControlPane.setNoti(playermessage);
		}
		else if(random==5){
			deleteAllBarricade();
			String playermessage ="remove all barricade tile";
			ControlPane.setNoti(playermessage);
		}else if(random==6){
			heal(e);
			String playermessage = GameController.getTurn() % 2 == 1
					? "Player 1 Special heal"
					: "Player 2 Special heal";
			ControlPane.setNoti(playermessage);
		}else {
			placeBomb(e);
			String playermessage = GameController.getTurn() % 2 == 1
					? "Player 1 Special get bomb"
					: "Player 2 Special get bomb";
			ControlPane.setNoti(playermessage);
		}
		ControlPane.labelUpdate();
		ControlPane2.labelUpdate();
		Main.fieldPane.setFieldPane(Main.fieldPane);
	}
	
	public static void heal(Player e){
		e.heal();
	}
	
	public static void placeBomb(Player e) {
		e.setHaveExploding(e.getHaveExploding()+1);
	}
	
	public static void deleteAllSpecialTile()
	{
		for(int i=0;i<17;i++)
		{
			for(int j=0;j<17;j++)
			{
				if(GameController.getCurrentMap().getEntity(i, j).isSpecialTile())
				{
					GameController.getCurrentMap().removeEntity(i, j);
					GameController.getCurrentMap().addEntity(new BlackTile(i,j),i,j);
				}
			}
		}
	}
	
	public static void deleteAllBarricade()
	{
		for(int i=0;i<17;i++)
		{
			for(int j=0;j<17;j++)
			{
				if(GameController.getCurrentMap().getEntity(i, j).isBarricadeTile())
				{
					GameController.getCurrentMap().removeEntity(i, j);
					GameController.getCurrentMap().addEntity(new WhiteTile(i,j),i,j);
				}
			}
		}
	}

	public static void moveRandom(Player e) throws moveFail {
		double random = Math.random();
		int dir = (int) (random * 100 - 1) / 25;
		int[] dirx = { 0, 1, 0, -1 };
		int[] diry = { -1, 0, 1, 0 };
		GameController.move(e, e.getX() + 2 * dirx[dir], e.getY() + 2 * diry[dir], e.getX(), e.getY());
	}

	public static void addBarricade(Player e) {
		e.setBarricade(e.getHaveBaricade() + 1);
	}

	public static void removeBarricade(Player e) {
		e.setHaveRemoveBarricade(e.getHaveRemoveBarricade()+1);
	}

}
