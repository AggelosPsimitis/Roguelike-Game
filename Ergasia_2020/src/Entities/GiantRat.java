package Entities;

import Main.State.Handler;

public class GiantRat extends Enemy{

	public GiantRat(Handler handler, float x, float y) {
		super(handler, x, y, "Giant Rat", 30, 5, 2, 4);
		
	}
	
	public String getPunchLine() {
		return "You can hear some heavy squeaks...";
	}
	
}
