package Entities;

import Main.State.Handler;

public class Wyrm extends Enemy {

	public Wyrm(Handler handler, float x, float y) {
		
		super(handler, x, y, "Wyrm", 200, 80, 20, 5);
	}
	
	public String getPunchLine() {
		return "Nor a dragon nor a snake but both...";
	}
}
