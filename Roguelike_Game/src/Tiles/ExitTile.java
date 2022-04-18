package Tiles;

import java.awt.Color;
import java.awt.Graphics;

public class ExitTile extends Tile {

	public ExitTile(int x, int y) {
		super(TileType.ExitTile);
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		
		g.setColor(new Color(100, 60, 40));
		g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
		g.setColor(new Color(255, 255, 0));
		g.fillRect(x, y, TILE_WIDTH - 2, TILE_HEIGHT - 2);
		
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public boolean hasItem() {
		return false;
	}
}

