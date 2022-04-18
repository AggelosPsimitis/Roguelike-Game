package Main.State;

import java.awt.Graphics;


import Worlds.World;

public abstract class State {
	
	
	// game state manager
	protected static State currentState = null; // current state we need to update() & render() in our game
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	/////////////////////////////////
	
	protected Handler handler;
	protected World world;
	protected String name;
	
	public State(Handler handler) {
		this.handler = handler;	
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);

	public abstract World getWorld();
}
