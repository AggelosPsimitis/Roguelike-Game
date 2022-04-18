package Entities;

import Main.State.Handler;

public class GraySlime extends Enemy{

	public GraySlime(Handler handler, float x, float y) {
		
		super(handler,x, y, "Gray Slime", 80, 30, 8, 2);
	}
	
	public String getPunchLine() {
		return "You can hear a disgusting slippery sound...";
	}
}
