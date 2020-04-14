package interact;

import gui.ControlPane;
import gui.ControlPane2;
import logic.GameController;
import logic.moveFail;

public class RemoveAllBarricade extends SpecialTile{

	public RemoveAllBarricade(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void getAction(Player e){
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
		String playermessage ="remove all barricade tile";
		ControlPane.setNoti(playermessage);
		ControlPane.labelUpdate();
		ControlPane2.labelUpdate();
	}
	

}
