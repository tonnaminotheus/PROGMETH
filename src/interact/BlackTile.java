package interact;

import logic.Sprites;

public class BlackTile extends Tile {

	public BlackTile(int x, int y) {
		super(x, y);
	}

	public int getSymbol() {
		return Sprites.BlackTile;
	}

}
