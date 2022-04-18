package Items;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import Interfaces.Consumable;
import Interfaces.EffectType;
import Interfaces.Item;
import Main.State.Handler;
import Tiles.Tile;

public class HealthPotion implements Item, Consumable {

	
	private Handler handler;
	private int usesLeft;
	private Set<ItemEffect> itEfList;
	private int healthReplAmt;
	private boolean active;
	private int x;
	private int y;
	private int width;
	private int height;
	private String name;
	
	public HealthPotion(Handler handler, int x, int y) {
		
		this.x = x;
		this.y = y;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.handler = handler;
		this.healthReplAmt = 20;
		itEfList = new HashSet<>();
		this.itEfList.add(new ItemEffect(EffectType.HP_REPLENISH, healthReplAmt));
		this.usesLeft = 1;
		this.name = "Health Potion";
		this.active = true;
	}
	
	@Override
	public int UsesLeft() {
		return this.usesLeft;
	}

	@Override
	public void use() {

		if (usesLeft > 0) {
			int health = handler.getGame().getPlayer().getHealth() + healthReplAmt;
			handler.getGame().getPlayer().setHealth(health);
			usesLeft --;
			//handler.getWorld().getEntityManager().getPlayer().getInventory().remove(this);
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return this.name;
	}
	
	@Override
	public Set<ItemEffect> getItemEffects() {
		return this.itEfList;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public void setIsActive(boolean active) {
		this.active = active;
	}
	
	public String toString() {
		
		return new StringBuilder(this.name).
				append(" ").
				append(this.itEfList).toString();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if(handler.getWorld().tileVisible(x, y)) {
			g.setColor(new Color(30, 250, 30));
			g.fillRect(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, width, height);
		}
		
	}

}
