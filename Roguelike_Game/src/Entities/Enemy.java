package Entities;

import java.awt.*;
import java.util.*;
import java.util.List;
import Items.*;
import Main.*;
import Main.State.*;
import Tiles.*;



public abstract class Enemy extends Creature {
	
	
	private String name;
	private int health;
	private int strength;
	private int xp;
	private int visibilityRadius;
	private String gameLogNotify;
	private List<MyObserver> enemyObservers;
	private Random random;
	private boolean punchLineFlag = true;

	public Enemy(Handler handler, float x, float y, String name, int xp, int health, int strength, int visibilityRadius) {
		
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);		
	
		bounds.x = 3;
		bounds.y = 3;
		bounds.width = 5;
		bounds.height = 5;
		
		this.name = name;
		this.xp = xp;
		this.health = health;
		this.strength = strength;
		this.visibilityRadius = visibilityRadius;
		this.enemyObservers = new LinkedList<>();
		this.gameLogNotify = "";
		this.random = new Random();
	}
	
	

	@Override
	public void update() {

		if(playerInVisibilityArea()){
			if(punchLineFlag) {
				gameLogNotify = this.getPunchLine();
				setPunchLineFlag(false);
			}
			chooseDirection();
			move();
			
		}
		
		checkAttacks();
		
		notifyObservers();
		gameLogNotify = "";
			
	}
	
	
	public double calculateHValue(int x, int y, int destX, int destY) {
		return ((double)Math.sqrt((x - destX)*(x - destX)) + 
				(y - destY)*(y - destY));
	}
	
	public void chooseDirection() {  
		
		xMove = 0;
		yMove = 0;
		List<Double> heuristicArray = new ArrayList<>();
		// calculate heuristic distance of north tile from player
		double h1 = calculateHValue((int)(x / Tile.TILE_WIDTH), (int)(y / Tile.TILE_HEIGHT) - 1, 
				(int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) ,
				(int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT));
		heuristicArray.add(h1);
		
		// calculate heuristic distance of south tile from player
		double h2 = calculateHValue((int)(x / Tile.TILE_WIDTH), (int)(y / Tile.TILE_HEIGHT) + 1, 
				(int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) ,
				(int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT));
		heuristicArray.add(h2);
		
		// calculate heuristic distance of west tile from player
		double h3 = calculateHValue((int)(x / Tile.TILE_WIDTH) - 1, (int)(y / Tile.TILE_HEIGHT), 
				(int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) ,
				(int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT));
		heuristicArray.add(h3);	
	
		// calculate heuristic distance of east tile from player
		double h4 = calculateHValue((int)(x / Tile.TILE_WIDTH) + 1, (int)(y / Tile.TILE_HEIGHT), 
				(int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) ,
				(int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT));
		heuristicArray.add(h4);
		
		// calculate heuristic distance of northWest tile from player
		double h5 = calculateHValue((int)(x / Tile.TILE_WIDTH) - 1, (int)(y / Tile.TILE_HEIGHT) -1, 
				(int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) ,
				(int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT));
				heuristicArray.add(h5);
				
		// calculate heuristic distance of northeEast tile from player
		double h6 = calculateHValue((int)(x / Tile.TILE_WIDTH) + 1, (int)(y / Tile.TILE_HEIGHT) - 1, 
				(int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) ,
						(int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT));
				heuristicArray.add(h6);
		
		// calculate heuristic distance of southWest tile from player
		double h7 = calculateHValue((int)(x / Tile.TILE_WIDTH) - 1, (int)(y / Tile.TILE_HEIGHT) + 1, 
				(int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) ,
						(int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT));
				heuristicArray.add(h7);
		
			
		// calculate heuristic distance of southEast tile from player
		double h8 = calculateHValue((int)(x / Tile.TILE_WIDTH) + 1, (int)(y / Tile.TILE_HEIGHT) + 1, 
				(int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) ,
						(int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT));
				heuristicArray.add(h8);		
				
		double min = h1;
		for(Double d : heuristicArray) {
			if(d < min)
				min = d;
		}
		if(min == h1) 
			yMove = -speed;
		if(min == h2)
			yMove = speed;
		if(min == h3)
			xMove = -speed;
		if(min == h4)
			xMove = speed;
		if(min == h5) {
			xMove = -speed;
			yMove = -speed;
		}
		if(min == h6) {
			xMove = speed;
			yMove = -speed;
		}
		if(min == h7) {
			xMove = -speed;
			yMove = speed;
		}
		if(min == h8) {
			xMove = speed;
			yMove = speed;
		}
	}
	
	private boolean playerInVisibilityArea() {
		return (Math.abs((int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) - (int)(this.x / Tile.TILE_WIDTH)) + 
				Math.abs((int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT) - (int)(this.y / Tile.TILE_HEIGHT))) < this.visibilityRadius;
	}
	
	
	private void checkAttacks() {
		
		if(handler.getGame().getPlayer().getCollisionBox().intersects(this.getCollisionBox())) {
			handler.getGame().getPlayer().hurt(this.strength);
			return;
		}
	}
	
	private boolean probToLeavePotion() {
		return this.random.nextInt(3) == 0;  // 25% probability (roughly)
	}
	
	@Override
	public void hurt(int amt) {
		this.health -= amt;
		gameLogNotify = this.getName() + ": - " + amt + " Health";
		if(this.health <= 0)
			die();
	}
	
	@Override
	public void die() {
		setActive(false);
		handler.getWorld().setEnemyCount(handler.getGame().getGameState().getWorld().getEnemyCount() - 1);
		if(probToLeavePotion()) {
			Tile[][] tileMap;
			switch(random.nextInt(1)) {
				
				case 0 :
					handler.getWorld().getItemManager().addItem(new HealthPotion(handler, (int)(x / Tile.TILE_WIDTH),
							(int)(y / Tile.TILE_HEIGHT)));
					tileMap = handler.getWorld().getTileMap();
					tileMap[(int)(x / Tile.TILE_WIDTH)][(int)(y / Tile.TILE_HEIGHT)] = new ItemTile((int)(x / Tile.TILE_WIDTH), (int)(y / Tile.TILE_HEIGHT));
					break;
				
				case 1 :
					handler.getWorld().getItemManager().addItem(new ManaPotion(handler, (int)(x / Tile.TILE_WIDTH),
							(int)(y / Tile.TILE_HEIGHT)));
					tileMap = handler.getWorld().getTileMap();
					tileMap[(int)(x / Tile.TILE_WIDTH)][(int)(y / Tile.TILE_HEIGHT)] = new ItemTile((int)(x / Tile.TILE_WIDTH), (int)(y / Tile.TILE_HEIGHT));
			}
		}
	}
	
	

	@Override
	public void render(Graphics g) {
		
		if(handler.getWorld().tileVisible((int)(x / Tile.TILE_WIDTH), (int)(y / Tile.TILE_HEIGHT))) {
			g.setColor(new Color(255, 51, 153));
			g.drawOval((int)(x + 2.0), (int)(y + 2.0), width, height); 
			g.fillOval((int)(x + 2.0), (int)(y + 2.0), width, height);
		}
	}
	
	public String getName() {
		return this.name;
	}

	public int getXp() {
		return this.xp;
	}

	public void setPunchLineFlag(boolean flag) {
		this.punchLineFlag = flag;
	}
	
	public abstract String getPunchLine();
	
	@Override
	public void addObserver(MyObserver o) {
		this.enemyObservers.add(o);
		
	}

	@Override
	public void removeObserver(MyObserver o) {
		enemyObservers.remove(o);
		
	}
	
	@Override
	public void notifyObservers() {
		
		for( MyObserver o : this.enemyObservers) 
			o.update(gameLogNotify);
	}
}
