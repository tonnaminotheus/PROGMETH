package interact;

import application.Main;
import gui.ControlPane;
import gui.ControlPane2;
import logic.GameController;

public class RemoveAllSpecialTile extends SpecialTile{

	
	public RemoveAllSpecialTile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getAction(Player e){
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
		String playermessage ="remove all special tile";
		ControlPane.setNoti(playermessage);
		ControlPane.labelUpdate();
		ControlPane2.labelUpdate();
		Main.fieldPane.setFieldPane(Main.fieldPane);
	}

}
