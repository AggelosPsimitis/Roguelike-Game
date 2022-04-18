package Tiles;

import java.awt.Color;
import java.awt.Graphics;

public class WallTile extends Tile {

	public WallTile(int x, int y) {
		super(TileType.WallTile);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean isWalkable() {
		return false;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		
		if(isUnknown() && !isFogged()) {
			g.setColor(Color.BLACK);
			g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
		}
		if(isFogged() && !isUnknown()) {
			g.setColor(new Color(105, 105, 105));
			g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
		}
		if(!isFogged() && !isUnknown()){
			g.setColor(new Color(105, 105, 105));
			g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
		}
	}

	@Override
	public boolean hasItem() {
		return false;
	}
	
}
