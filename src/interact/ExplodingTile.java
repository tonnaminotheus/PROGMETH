package interact;

import logic.GameController;
import logic.moveFail;

public class ExplodingTile extends Tile implements Special{

	public ExplodingTile(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void getAction(Player player) throws moveFail {
		int playerX=player.getX();
		int playerY=player.getY();
		GameController.getCurrentMap().removeEntity(playerX, playerY);
		if(player.getSide()==1) {
			GameController.getCurrentMap().removeEntity(0,8);
			GameController.getCurrentMap().addEntity(new BlackTile(playerX,playerY),playerX,playerY);
			player.setX(0);
			player.setY(8);
			player.damaged();
			GameController.getCurrentMap().addEntity(player,0,8);
		}
		else {
			GameController.getCurrentMap().removeEntity(16,8);
			GameController.getCurrentMap().addEntity(new BlackTile(playerX,playerY),playerX,playerY);
			player.setX(16);
			player.setY(8);
			player.damaged();
			GameController.getCurrentMap().addEntity(player,16,8);
		}
	}

}
