package interact;

import entity.base.Coordinate;
import entity.base.Entity;

public class Player extends Entity {
	private int index;
	private int haveBarricade;
	private int haveExploding;
	private int haveRemoveBarricade;
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
		this.setHaveExploding(3);
		this.setHaveRemoveBarricade(5);
		this.spawn=spawn;
		this.setSide(side);
		this.setFinish(finish);
	}
	
	public int getHaveRemoveBarricade() {
		return haveRemoveBarricade;
	}
	
	public void setHaveRemoveBarricade(int haveRemoveBarricade) {
		this.haveRemoveBarricade = haveRemoveBarricade;
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
	

}
