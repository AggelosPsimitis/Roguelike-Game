package Interfaces;

import java.util.HashSet;
import java.util.Set;

public class Slot {
	
	private SlotType slotType;
	private int cap;
	private Set<Item> slotItems;
	
	public Slot(SlotType slotType, int cap) {
		this.slotType = slotType;
		this.cap = cap;
		this.slotItems = new HashSet<>();
	}
	
	public SlotType getSlotType() {
		return this.slotType;
	}
	
	public int getCap() {
		return this.cap;
	}
	
	public boolean isFull() {
		return this.cap == this.slotItems.size();
	}
	
	public boolean Equip(Object object) throws IllegalArgumentException{
		if(!this.isFull()) {
			
			try {
				if(!(object instanceof Equippable))
					throw new IllegalArgumentException("ITEM NOT EQUIPPABLE");
				var item = (Equippable)object;
				SlotType st = item.getSlotType();
				if(!(st == this.slotType))
					throw new IllegalArgumentException("WRONG SLOT TYPE");
				if(this.slotItems.contains(object))
					throw new IllegalArgumentException("ALREADY IN SET");
			} catch (IllegalArgumentException ie) {
				System.out.println(ie);
				return false;
			  }
			this.slotItems.add((Item)object);
			return true;
		} else return false;
	}
	
	public boolean Remove(Object object) throws NullPointerException{
		if(this.slotItems.contains(object)) {
			this.slotItems.remove(object);
			return true;
		}
		else
			throw new NullPointerException("CANNOT REMOVE ITEM THAT IS NOT IN THE SET");
	}

	public boolean isInSlotItems(Object object) {
		return this.slotItems.contains(object);
	}
	
	public Set<Item> getSlotItems(){
		return this.slotItems;
	}
	
	public String toString() {
		
		return new StringBuilder("").append(this.getSlotType()).
				append(" : ").
				append(this.getSlotItems().size() + " / " + this.cap).toString();
	}
	
}
