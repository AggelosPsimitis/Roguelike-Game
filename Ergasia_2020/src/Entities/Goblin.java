package Entities;

import Main.State.Handler;

public class Goblin extends Enemy{

	public Goblin(Handler handler, float x, float y) {
		super(handler, x, y, "Goblin", 50, 15, 5, 7);
		
	}
	public String getPunchLine() {
		return "You can hear a goblin's whisper...";
	}
	
}
