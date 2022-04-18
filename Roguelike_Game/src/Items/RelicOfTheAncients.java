package Items;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import Interfaces.EffectType;
import Interfaces.Equippable;
import Interfaces.Item;
import Interfaces.SlotType;
import Main.State.Handler;
import Tiles.Tile;

public class RelicOfTheAncients implements Equippable, Item {

	private Handler handler;
	private SlotType slotType;
	private Set<ItemEffect> itemEffects;
	private String name;
	private int x;
	private int y;
	private boolean active;
	private int width, height;
	
	public RelicOfTheAncients(Handler handler, int x, int y){
		
		this.handler = handler;
		this.slotType = slotType.CHEST;
		this.itemEffects = new HashSet<>();
		this.itemEffects.add(new ItemEffect(EffectType.DAMAGE_BOOST,42));
		this.itemEffects.add(new ItemEffect(EffectType.HP_BOOST,80));
		this.name = "RelicOfTheAncients";
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.x = x;
		this.y = y;
		this.active = true;
	}
	public SlotType getSlotType(){
		return this.slotType;
	}
	public Set<ItemEffect> getItemEffects(){
		return this.itemEffects;
	}
	public String getName(){
		return this.name;
	}
	public String toString(){
		return new StringBuilder("RelicOfTheAncients :").append(" ").
		  append(this.itemEffects).toString();
	}
	@Override
	public boolean isActive() {
		return this.active;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(Graphics g) {

		if(handler.getWorld().tileVisible(x, y)) {
			g.setColor(new Color(229, 204, 255));
			g.fillRect(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, width, height);
		}
			
	}
		
	@Override
	public int getX() {
		return this.x;
	}
	@Override
	public int getY() {
		return this.y;
	}
	@Override
	public void setIsActive(boolean active) {
		this.active = active;	
	}
	
	
}
