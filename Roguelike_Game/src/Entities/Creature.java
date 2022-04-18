package Entities;


import Main.State.Handler;
import Tiles.Tile;

public abstract class Creature extends Entity {
	
	
	public static final float DEFAULT_SPEED = 0.6f;
	public static final int DEFAULT_CREATURE_WIDTH = 6;
	public static final int DEFAULT_CREATURE_HEIGHT = 6;

	
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		
		moveX();
		moveY();	
	}
	
	public void moveX() {
		if(xMove > 0) {//moving right
			
			
			int tx = (int)(x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			
			
			if(noCollisionWithTile(tx, (int)(y + bounds.y) / Tile.TILE_HEIGHT) &&
					noCollisionWithTile(tx, (int)( y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			}
		}else if(xMove < 0) {// moving left
			int tx = (int)(x + xMove + bounds.x) / Tile.TILE_WIDTH;
			
			if(noCollisionWithTile(tx, (int)(y + bounds.y) / Tile.TILE_HEIGHT) &&
					noCollisionWithTile(tx, (int)( y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) {// moving up
			
			
			int ty = (int)(y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			
			if(noCollisionWithTile((int)(x + bounds.x) / Tile.TILE_WIDTH, ty) &&
					noCollisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			}
		}else if(yMove > 0) {// moving down
			
			int ty = (int)(y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			
			if(noCollisionWithTile((int)(x + bounds.x) / Tile.TILE_WIDTH, ty) &&
					noCollisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			}
			
		}
	}
	
	protected boolean noCollisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isWalkable();
	}
	
	protected boolean tileHasItem(int x, int y) {
		return handler.getWorld().getTile(x, y).hasItem();
	}
	
	//Getters & Setters

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
