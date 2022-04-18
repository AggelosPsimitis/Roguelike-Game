package Main.State;

import java.awt.Graphics;
import Worlds.World;


public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler, int maxNumOfEn, int maxNumOfHeaPot, int maxNumOfManPot, int maxNumOfEq) {
		
		super(handler);
		world = new World(handler, maxNumOfEn, maxNumOfHeaPot, maxNumOfManPot, maxNumOfEq);
		handler.setWorld(world);
	
	}
	@Override
	public void update() {

		world.update();
	}
	
	@Override
	public void render(Graphics g) {
		world.render(g);
	}
	@Override
	public World getWorld() {
		return world;
	}
	
	
	
}
