package interact;

import logic.moveFail;
import gui.ControlPane;
import gui.ControlPane2;
import interact.Player;
import logic.GameController;

public class SpecialTile extends Tile implements Special{

	public SpecialTile(int x, int y) {
		super(x, y);
	}
	@Override
	public void getAction(Player e) throws moveFail{}
}
