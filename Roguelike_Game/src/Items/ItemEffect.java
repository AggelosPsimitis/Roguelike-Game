package Items;

import Interfaces.EffectType;

public class ItemEffect {

	private EffectType effectType;
	private int amt;
	
	public ItemEffect(EffectType effectType, int amt) {
		
		this.effectType = effectType;
		this.amt = amt;
		
	}
	
	public boolean isConsumable() {
		return this.effectType.isConsumable();
	}
	
	public EffectType getEffectType() {
		return this.effectType;
	}
	
	public int getEffectAmount() {
		return this.amt;
	}
	
	public String toString() {
		
		return new StringBuilder("").append(this.effectType).
				append(" ").
				append(this.amt).append(" ").toString();
	}
	
	
}
