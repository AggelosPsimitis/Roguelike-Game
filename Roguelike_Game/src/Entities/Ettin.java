package Entities;

import Main.State.Handler;

public class Ettin extends Enemy {

	public Ettin (Handler handler, float x, float y) {
		
		super(handler, x, y, "Ettin", 150, 60, 20, 9);
	}
	
	public String getPunchLine() {
		return "You can hear heavy steps...";
	}
	
}
