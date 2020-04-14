package interact;

import gui.ControlPane;
import logic.GameController;

public class GetBarricade extends SpecialTile{
	public GetBarricade(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void getAction(Player e){
		String playermessage = GameController.getTurn() % 2 == 1
				? "Player 1 Special get barricade"
				: "Player 2 Special get barricade";
		ControlPane.setNoti(playermessage);
		e.setBarricade(e.getHaveBaricade() + 1);
	}
}
