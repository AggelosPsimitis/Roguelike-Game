package Tiles;

import java.awt.*;

public abstract class Tile {

	
	public static final int TILE_WIDTH = 12 , TILE_HEIGHT = 12;
	
    private boolean unknown;
    private boolean fogged;
    private TileType tileType;
	protected int x, y;

	public Tile(TileType tltp) {
    
		this.tileType = tltp;
		this.unknown = true;
		this.fogged = false;
	}
	
	public void update() {
		
	}
	
	public abstract void render(Graphics g, int x, int y); 
		
	
	public abstract boolean isWalkable();
		
	
	public boolean isUnknown() {
		return this.unknown;
	}
	
	public boolean isFogged() {
		return this.fogged;
	}
	
	public void setUnknown(boolean u) {
		this.unknown = u;
	}
	
	public void setFogged(boolean f) {
		this.fogged = f;
	}
	
	public abstract boolean hasItem();
		
	public TileType getTileType() {
		return this.tileType;
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
