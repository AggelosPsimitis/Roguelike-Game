package Entities;

import Main.State.Handler;

public class OrcGrunt extends Enemy {

	public OrcGrunt(Handler handler, float x, float y) {
		
		super(handler, x, y, "Orc Grunt", 100, 40, 10, 6);
	}
	
	public String getPunchLine() {
		return "You can hear the grunts of a warthirsty veteran...";
	}
}
