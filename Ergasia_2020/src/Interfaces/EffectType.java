package Interfaces;

public enum EffectType {

	NONE(false), MANA_BOOST(false), MANA_REPLENISH(true), 
	HP_BOOST(false), HP_REPLENISH(true), DAMAGE_BOOST(false);
	
	private boolean consumable;
	
	EffectType(boolean consumable) {
		this.consumable = consumable;
	}
	
	public boolean isConsumable() {
		return this.consumable;
	}
	
}
