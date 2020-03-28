package interact;

import entity.base.Entity;
import logic.GameController;
import logic.Sprites;

public class Player extends Entity {
	private int index;
	private int haveBarricade;

	public Player(int x, int y, int index) {
		super(x, y);
		this.setIndex(index);
		this.setBarricade(10);
		// TODO Auto-generated constructor stub
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public void setBarricade(int haveBarricade) {
		this.haveBarricade = haveBarricade;
	}

	public int getHaveBaricade() {
		return this.haveBarricade;
	}

	@Override
	public int getSymbol() {
		// TODO Auto-generated method stub
		if (this.getIndex() == 1)
			return Sprites.Player1;
		else if (this.getIndex() == 2)
			return Sprites.Player2;
		else if (this.getIndex() == 3)
			return Sprites.Player3;
		else if (this.getIndex() == 4)
			return Sprites.Player4;
		else if (this.getIndex() == 5)
			return Sprites.Player5;
		else if (this.getIndex() == 6)
			return Sprites.Player6;
		else
			return Sprites.Player7;
	}

}
