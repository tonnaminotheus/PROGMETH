package interact;

import gui.ControlPane;
import gui.ControlPane2;
import logic.GameController;

public class AddBomb extends SpecialTile{
	public AddBomb(int x, int y) {
		super(x, y);
	}
	public void getAction(Player e){
		e.setHaveExploding(e.getHaveExploding()+1);
		String playermessage = GameController.getTurn() % 2 == 1
				? "Player 1 Special get bomb"
				: "Player 2 Special get bomb";
		ControlPane.setNoti(playermessage);
		ControlPane.labelUpdate();
		ControlPane2.labelUpdate();
	}
}
