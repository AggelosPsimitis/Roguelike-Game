package Entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import Main.State.Handler;

public class EntityManager {
	
	private Handler handler;
	private ArrayList<Entity> entities;
	
	public EntityManager(Handler handler, Player player) {
		
		this.handler = handler;
		entities = new ArrayList<>();
		addEntity(player);
		
	}
	
	public void update() {
		
		for(Iterator<Entity> iterator = this.entities.iterator(); iterator.hasNext();) {
			Entity e = iterator.next();
			e.update();
			if(!e.isActive())
				iterator.remove();
			
		}
	}
	
	public void render(Graphics g) {
		
		for(Entity e : entities) {
			
			e.render(g);
		}	
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
		if(!(e instanceof Player))
			e.addObserver(handler.getGame().getDisplay().getGameLog());
	}
	
	
	// GETTERS SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public Entity getEntity(float x, float y) {
		
		for(Entity e : this.entities) {
			if(e.getX() == x && e.getY() == y)
				return e;
		}
		return null;
	}
	

}
