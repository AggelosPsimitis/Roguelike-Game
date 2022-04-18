package Tiles;

import java.awt.Color;
import java.awt.Graphics;

public class EntranceTile extends Tile {

	public EntranceTile(int x, int y) {
		super(TileType.EntranceTile);
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		if(!isUnknown() && !isFogged()) {
			g.setColor(new Color(100, 60, 40));
			g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
			g.setColor(new Color(255, 255, 0));
			g.fillRect(x, y, TILE_WIDTH - 2, TILE_HEIGHT - 2);
		}
		if(isFogged() && !isUnknown()) {
			g.setColor(new Color(100, 60, 40));
			g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
			g.setColor(new Color(139, 0, 0));
			g.fillRect(x, y, TILE_WIDTH - 2, TILE_HEIGHT - 2);
		}
		if(isUnknown() && !isFogged()) {
			g.setColor(Color.BLACK);
			g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
		}
		
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
