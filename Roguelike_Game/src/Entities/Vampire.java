package Entities;

import Main.State.Handler;

public class Vampire extends Enemy{
	
	public Vampire (Handler handler, float x, float y) {
		
		super(handler, x, y, "Vampire", 400, 50, 30, 10);
	}
	
	public String getPunchLine() {
		
		return "You can hear bats rustling nearby...";
	}

}
