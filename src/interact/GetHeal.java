package interact;

import gui.ControlPane;
import gui.ControlPane2;
import logic.GameController;

public class GetHeal extends SpecialTile implements Special{
	public GetHeal(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getAction(Player e){
		e.heal();
		String playermessage = GameController.getTurn() % 2 == 1
				? "Player 1 Special heal"
				: "Player 2 Special heal";
		ControlPane.setNoti(playermessage);
		ControlPane.labelUpdate();
		ControlPane2.labelUpdate();
	}
}
