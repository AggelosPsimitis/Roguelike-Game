package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.State.Handler;

public abstract class Entity implements PlayerSubject {
	
	
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected boolean active = true; // if false entity manager removes this entity from the game
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void update();
	public abstract void render(Graphics g);
	
	public abstract void die(); 
	public abstract void hurt(int amt);

	
	// method to check if collision box of this entity is inside the collision box of another entity
	public boolean checkEntityCollisions() {  
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBox().intersects(this.getCollisionBox())) {
				return true;
			}
			
		}
		return false;
	}
	
	public Rectangle getCollisionBox() {
		return new Rectangle((int)(this.x + bounds.x), (int)(this.y + bounds.y), bounds.width, bounds.height);
	}

}
