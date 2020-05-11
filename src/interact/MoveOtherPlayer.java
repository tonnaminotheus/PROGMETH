package interact;

import gui.ControlPane;
import gui.ControlPane2;
import logic.GameController;
import logic.moveFail;

public class MoveOtherPlayer extends SpecialTile{
	public MoveOtherPlayer(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getAction(Player e) throws moveFail{
		moveRandom(e.getOtherPlayer());
		String playermessage = GameController.getTurn() % 2 == 1
			? "Player 1 Special move Player2"
			: "Player 2 Special move Player1";
		ControlPane.setNoti(playermessage);
		ControlPane.labelUpdate();
		ControlPane2.labelUpdate();
	}
	
	public static void moveRandom(Player e) throws moveFail {
		double random = Math.random();
		int dir = (int) (random * 100 - 1) / 25;
		int[] dirx = { 0, 1, 0, -1 };
		int[] diry = { -1, 0, 1, 0 };
		GameController.move(e, e.getX() + 2 * dirx[dir], e.getY() + 2 * diry[dir], e.getX(), e.getY());
	}
}
