package interact;

import gui.ControlPane;
import logic.GameController;

public class GetHeal extends SpecialTile{
	public GetHeal(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void getAction(Player e){
		e.heal();
		String playermessage = GameController.getTurn() % 2 == 1
				? "Player 1 Special heal"
				: "Player 2 Special heal";
		ControlPane.setNoti(playermessage);
	}
}
