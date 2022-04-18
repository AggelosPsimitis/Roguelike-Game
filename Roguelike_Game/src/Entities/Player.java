package Entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import Interfaces.*;
import Items.*;
import Main.*;
import Main.PlayerStatus;
import Main.State.*;
import Tiles.*;


public abstract class Player extends Creature {

	private String name;
	private int health, mana;
	private int xp;
	private int strength;
	private int intelligence;
	private int level;
	private Map<SlotType, Slot> playerSlots;
	private List<Slot> playerSlotsList;
	private List<Item> inventory;
	private int inventoryCap;
	private NavigableMap<Integer, Integer> xpToLevel;
	private List<MyObserver> playerObservers;
	private String gameLogNotify;
	
	
	public Player(Handler handler, float x, float y, String name) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);		
	
		bounds.x = 3;
		bounds.y = 3;
		bounds.width = 5;
		bounds.height = 5;
		
		this.name = name;
		this.createXpToLevel();
		this.setXP(0);
		this.health = this.getLevelHealth();
		this.mana = this.getLevelMana();
		this.strength = this.getLevelStrength();
		this.intelligence = this.getLevelIntell();
		this.setLevel();
		this.playerSlots = new HashMap<>();
		this.playerSlotsList = new LinkedList<>();
		
		this.inventory = new ArrayList<>();
		this.inventoryCap = 10;
		
		this.playerObservers = new LinkedList<>();
		
		this.gameLogNotify = "";
	}

	public boolean inventoryFull() {
		return this.inventoryCap == this.inventory.size();
	}
	
	public void pickUp(Item item) throws NullPointerException{
		try {
			if((Consumable) item != null && !this.inventoryFull()) {
				this.inventory.add(item);
				item.setIsActive(false);
				gameLogNotify = " Added to Inventory : " + item;
			}
		}catch (NullPointerException ne) {
			System.out.println(ne);
		}
	}
	
	@Override
	public void update() {
		
		// Movement
		getInput();
		move();
		
		// automatically pick up item from tile (if it is ItemTile)
		checkItemPickUp();
		
		int beforeFightLevel = this.getLevel();
		
		// Attack if player's collision box is inside an enemy's collision box
		checkAttacks();

		int afterFightLevel = this.getLevel();
		
		// check if player's level has increased after a fight and update stats
		if(levelIncreased(beforeFightLevel, afterFightLevel)) {
			setHealth(getLevelHealth());
			setMana(getLevelMana());
			setStrength(getLevelStrength());
			setIntelligence(getLevelIntell());
		}
		
		// notify PlayerStatus and GameLog 
		notifyObservers();
		gameLogNotify = "";
	}
	
	private boolean levelIncreased(int before, int after) {
		return before - after < 0;
	}
	
	private void checkAttacks() {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBox().intersects(this.getCollisionBox())) {
				Enemy enemy = (Enemy)e;
				enemy.hurt(getDamage());
				this.increaseXP((int)enemy.getXp() / 4);
				gameLogNotify = "+ " + (int)enemy.getXp() / 4 + " XP ";
				if(!e.isActive()) {
					gameLogNotify = "You killed a " + enemy.getName();
					this.increaseXP((int)enemy.getXp() / 2);
					gameLogNotify = "+ " + (int)enemy.getXp() / 2 + " XP ";
				}
				return;
			}
		}
	}
	
	public void checkItemPickUp() {
		
		if(handler.getWorld().getTile((int)(x / Tile.TILE_WIDTH), (int)(y / Tile.TILE_HEIGHT)).hasItem()) {
			if(handler.getWorld().getItemManager().getItem((int)(x / Tile.TILE_WIDTH), (int)(y / Tile.TILE_HEIGHT)) instanceof Consumable)
				pickUp(handler.getWorld().getItemManager().getItem((int)x / Tile.TILE_WIDTH, (int)y / Tile.TILE_HEIGHT));
		}	
	}
			
	@Override
	public void hurt(int amt) {
		
		this.health -= amt;
		gameLogNotify = "Damage Taken" + " - " + amt + " Health";
		if(this.getHealth() <= 0)
			die();
	}
	
	@Override
	public void die() {
		gameLogNotify = "YOU DIED";
		setActive(false);
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().useHealthPotion) {
			
			for(Iterator<Item> iterator = inventory.iterator(); iterator.hasNext();) {
				Item i = iterator.next();
				Consumable c = (Consumable) i;
				if(i.getName() == "Health Potion"){
					c.use();
					gameLogNotify = "Health Potion Used";
					iterator.remove();
					return;
				}
			}	
		}
		if(handler.getKeyManager().useManaPotion) {
			for(Iterator<Item> iterator = inventory.iterator(); iterator.hasNext();) {
				Item i = iterator.next();
				Consumable c = (Consumable)i;
				if(i.getName() == "Mana Potion") {
					c.use();
					gameLogNotify = "Mana Potion Used";
					iterator.remove();
					return;
				}
			}
		}
		
		if(handler.getKeyManager().equip) {
			for(Item i : handler.getWorld().getItemManager().getItems()) {
				if(i.getX() == (int)(x / Tile.TILE_WIDTH) && i.getY() == (int)(y / Tile.TILE_HEIGHT)) {
					Equippable e = (Equippable) i;
					SlotType s = e.getSlotType();
					if(this.Equip(s, e)) {
						gameLogNotify = "You Equipped a " + e;
						i.setIsActive(false);
					}
					else {
						gameLogNotify = "Cannot Equip item";
					}
				}
			}
		}
	}
	

	@Override
	public void render(Graphics g) {
			
		g.setColor(new Color(30, 200, 200)); 
		g.fillRect((int)(x + 2.0), (int)(y + 2.0), width, height);
	}
	
	
	public int getHealth() {
		return this.health + this.getExtraHealth();
	}

	public void setHealth(int health) {

		if(health >= getLevelHealth() + getExtraHealth() ) {
			this.health = getLevelHealth();
		}else this.health = health;
		
	}

	public int getMana() {
		return this.mana + this.getExtraMana();
	}

	public void setMana(int mana) {
		
		if(mana > getLevelMana() + this.getExtraMana() ) {
			this.mana = getLevelMana();
		}else this.mana = mana;

	}

	public int getDamage() {
		return this.strength + getExtraStrength();
	}

	public void setStrength(int strength) {

		if(strength > getLevelStrength() + this.getExtraStrength())
			this.strength = getLevelStrength();
		else this.strength = strength;
		
	}

	public int getIntelligence() {
		return this.intelligence;
	}

	public void setIntelligence(int intelligence) {

		if(intelligence > getLevelIntell())
			this.intelligence = getLevelIntell();
		else this.intelligence = intelligence;
		
	}

	public abstract int getLevelHealth();
	public abstract int getLevelMana();
	public abstract int getLevelStrength();
	public abstract int getLevelIntell();
	
	public int getExtraHealth() {
		
		var health = 0;
		
		for(Slot s : this.playerSlotsList) {
			
			for (Item i : s.getSlotItems()) {
				
				for (ItemEffect ie : i.getItemEffects()) {
					
					if(ie.getEffectType() == EffectType.HP_BOOST) {
						
						health += ie.getEffectAmount();
						
					}
				}
			}
		}
		return health;	
	}
	
	public int getExtraMana() {
		
		var mana = 0;
		
		for(Slot s : this.playerSlotsList) {
			
			for (Item i : s.getSlotItems()) {
				
				for (ItemEffect ie : i.getItemEffects()) {
					
					if(ie.getEffectType() == EffectType.MANA_BOOST) {
						
						mana += ie.getEffectAmount();
						
					}
				}
			}
		}
		return mana;	
	}
	
	public int getExtraStrength() {
		
		var strength = 0;
		
		for(Slot s : this.playerSlotsList) {
			
			for (Item i : s.getSlotItems()) {
				
				for (ItemEffect ie : i.getItemEffects()) {
					
					if(ie.getEffectType() == EffectType.DAMAGE_BOOST) {
						
						strength += ie.getEffectAmount();
						
					}
				}
			}
		}
		return strength;	
	}
	
	public int getXP() {
		return this.xp;
	}
	
	public void setXP(int xp) {
		this.xp = xp;
		if(this.xp < 0)
			this.xp = 0;
		//this.setLevel();
	}

	private void increaseXP(int ixp) {
		var xp = this.getXP();
		xp += ixp;
		this.setXP(xp);
		this.setLevel();
	}
	
	private void decreaseXP(int dxp) {
		var xp = getXP();
		xp -= dxp;
		this.setXP(xp);
		if(xp < 0){
			this.setXP(0);
		}
		this.setLevel();
	}
	
	public void setLevel() {
		this.level = this.xpToLevel.floorEntry(this.getXP()).getValue();
	}
	
	public int getLevel() {
		return this.level;
	}
	
	protected void createPlayerSlots(int numberOfFingers, int numberOfHands, int numberOfChests, int numberOfLegs) {
		this.playerSlots.put(SlotType.FINGERS,  new Slot(SlotType.FINGERS, numberOfFingers));
		this.playerSlotsList.add(this.playerSlots.get(SlotType.FINGERS));
		this.playerSlots.put(SlotType.HANDS, new Slot(SlotType.HANDS, numberOfHands));
		this.playerSlotsList.add(this.playerSlots.get(SlotType.HANDS));
		this.playerSlots.put(SlotType.CHEST, new Slot(SlotType.CHEST, numberOfChests));
		this.playerSlotsList.add(this.playerSlots.get(SlotType.CHEST));
		this.playerSlots.put(SlotType.LEGS, new Slot(SlotType.LEGS, numberOfLegs));
		this.playerSlotsList.add(this.playerSlots.get(SlotType.LEGS));
	}
	
	private Slot getSlot(SlotType slotType) {
		return this.playerSlots.get(slotType);
	}
	
	public boolean Equip(SlotType slotType, Object object) {
		return this.playerSlots.get(slotType).Equip(object);
	}
	
	
	private int getNumberOfHealthPotions() {
		int count = 0;
		for (Item i : this.inventory) {
			if(i.getName() == "Health Potion")
				count += 1;
		}
		return count;
	}
	
	private int getNumberOfManaPotions() {
		int count = 0;
		for (Item i : this.inventory) {
			if(i.getName() == "Mana Potion")
				count += 1;
		}
		return count;
	}
	
	public List<Item> getInventory(){
		return this.inventory;
	}
	
	private void createXpToLevel(){
    	this.xpToLevel = new TreeMap<>();
    	this.xpToLevel.put(0,1);
       	this.xpToLevel.put(300,2);
    	this.xpToLevel.put(900,3);
    	this.xpToLevel.put(2700,4);
    	this.xpToLevel.put(6500,5);
    }


	public String getName() {
		return this.name;
	}
	
	@Override
	public void addObserver(MyObserver o) {
		this.playerObservers.add(o);
		
	}

	@Override
	public void removeObserver(MyObserver o) {
		playerObservers.remove(o);
		
	}
	
	@Override
	public void notifyObservers() {
		
		for( MyObserver o : this.playerObservers) {
			if(o instanceof PlayerStatus) {
				o.update(this.name + "\n WARRIOR" + "\n\n" + "Health : " + this.getHealth() + " ( + " + this.getExtraHealth() + ")" + "\n\n"
						+ "Mana : " + this.getMana() + "\n\n" + "Current Level :" + this.getLevel() + "\n\n"
						+ "XP points : " + this.getXP() + "\n\n"
						+ "Strength :" + this.getDamage() +  " ( " + this.getLevelStrength() + " + " + this.getExtraStrength() + ")" +"\n\n" 
						+ "Potions :" + this.getNumberOfHealthPotions() + " (H)" + " / " + this.getNumberOfManaPotions() + " (M)" + "\n\n"
						+ this.getSlot(SlotType.HANDS) + "\n\n" + this.getSlot(SlotType.FINGERS) + "\n\n"
						+ this.getSlot(SlotType.CHEST) + "\n\n" +  this.getSlot(SlotType.LEGS) + "\n\n");
			}
			else o.update(gameLogNotify);
		}
		
	}

	
	
}
