package Entities;

import Main.State.Handler;

public class Skeleton extends Enemy{

	public Skeleton (Handler handler, float x, float y) {
		
		super(handler, x, y, "Skeleton", 100, 20, 30, 4);
	}
	
	public String getPunchLine() {
		return "You can hear bones that creak...";
	}
}
