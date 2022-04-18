package Entities;

import Main.State.Handler;

public class OrcWarlord extends Enemy{

	public OrcWarlord(Handler handler, float x, float y) {
		super(handler, x, y, "OrcWarlord", 120, 50, 12, 7);
		
	}
	
	public String getPunchLine() {
		return "You can hear some old chieftain's heavy fluffs...";
	}
}
