package interact;

import entity.base.Coordinate;
import entity.base.Entity;
import logic.GameController;
import logic.Sprites;

public class Player extends Entity {
	private int index;
	private int haveBarricade;
	private int haveExploding;
	private Player otherPlayer;
	private Coordinate spawn;
	private int side;
	private int lp;
	private int finish;

	public Player(int x, int y, int index,Coordinate spawn,int side,int finish) {
		super(x, y);
		this.setIndex(index);
		this.setBarricade(10);
		this.setLp(3);
		this.setHaveExploding(5);
		this.spawn=spawn;
		this.setSide(side);
		this.setFinish(finish);
		// TODO Auto-generated constructor stub
	}
	
	public void setFinish(int finish) {
		this.finish = finish;
	}
	
	public int getFinish() {
		return finish;
	}
	
	public void setSide(int side) {
		this.side = side;
	}
	
	public int getSide() {
		return side;
	}
	
	
	public void setSpawn(Coordinate spawn) {
		this.spawn = spawn;
	}
	
	public Coordinate getSpawn() {
		return spawn;
	}
	
	public int getHaveExploding() {
		return haveExploding;
	}
	
	public void setHaveExploding(int haveExploding) {
		this.haveExploding = haveExploding;
	}

	public int getLp() {
		return lp;
	}

	public void setLp(int lp) {
		this.lp = lp;
	}

	public void setOtherPlayer(Player otherPlayer) {
		this.otherPlayer = otherPlayer;
	}

	public Player getOtherPlayer() {
		return otherPlayer;
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

	public void heal() {
		this.lp = Math.min(this.lp + 1, 3);
	}

	public void damaged() {
		this.lp = this.lp - 1;
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
