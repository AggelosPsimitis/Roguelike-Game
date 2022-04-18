package Entities;

import Main.State.Handler;

public class HumanWarrior extends Player {

	private static int DEFAULT_HEALTH = 30;
	private static int DEFAULT_MANA = 0;
	private static int DEFAULT_STRENGTH = 10;
	private static int DEFAULT_INTELLIGENCE = 0;
	
	public HumanWarrior(Handler handler, float x, float y, String name) {
		
		super(handler, x, y, name);
		super.createPlayerSlots(10, 2, 1, 1); // 10 fingers, 2 hands, 1 chest, 1 pair of legs

	}

	@Override
	public int getLevelHealth() {

		int health = DEFAULT_HEALTH;
		
		switch(super.getLevel()) {
		
			case 1 :
				break;
			
			case 2 : 
				health += 30;
				break;
				
			case 3 : 
				health += 50;
				break;
				
			case 4 : 
				health += 60;
				break;
				
			case 5 : 
				health += 70;
				break;
				
			default :
				break;
		}
		return health;
	}

	@Override
	public int getLevelMana() {
		return DEFAULT_MANA;
	}

	@Override
	public int getLevelStrength() {
		
		int strength = DEFAULT_STRENGTH;
		
		switch(super.getLevel()) {
		
			case 1 :
				break;
			
			case 2 : 
				strength += 10;
				break;
				
			case 3 : 
				strength += 15;
				break;
				
			case 4 : 
				strength += 20;
				break;
				
			case 5 : 
				strength += 25;
				break;
				
			default :
				break;
		}
		return strength;
	}

	@Override
	public int getLevelIntell() {
		return DEFAULT_INTELLIGENCE;
	}
	
}
