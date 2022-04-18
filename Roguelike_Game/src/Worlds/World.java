package Worlds;


import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import Entities.*;
import Items.HealthPotion;
import Items.ItemManager;
import Items.ManaPotion;
import Items.RelicOfTheAncients;
import Main.State.Handler;
import Tiles.*;




public class World {
	
	
	private Handler handler;
	private int width, height;  // counting in tiles not in pixels
	private Tile[][] tileMap;
	private Point startPoint;
	private Point exitPoint;
	private Point curPoint;
	private Random random;
	private double percentage;
	private int visibilityRadius;
	private int enemyCount;
	private int maxNumberOfEnemies;
	private int maxNumberOfHealthPotions;
	private int maxNumberOfManaPotions;
	private int maxNumberOfEquippables;
	
	//Entities
	private EntityManager entityManager;
	// Consumables
	private ItemManager itemManager;
		
	public World(Handler handler, int maxNumberOfEnemies, int maxNumberOfHealthPotions, int maxNumberOfManaPotions, int maxNumberOfEquippables){
		this.handler = handler;
		this.maxNumberOfEnemies = maxNumberOfEnemies;
		this.maxNumberOfHealthPotions = maxNumberOfHealthPotions;
		this.maxNumberOfManaPotions = maxNumberOfManaPotions;
		this.maxNumberOfEquippables = maxNumberOfEquippables;
		this.width = 80;
		this.height = 40;
		this.tileMap = new Tile[width][height];
		this.startPoint = new Point();
		this.exitPoint = new Point();
		this.curPoint = new Point();
		this.random = new Random();
		this.percentage = 0.45;
		this.visibilityRadius = 6;
		int startX = random.nextInt(width);
		int startY = random.nextInt(height);
		int curX = startX;
		int curY = startY;
		this.startPoint.setLocation(startX, startY);
		this.exitPoint.setLocation(startX, startY);
		this.curPoint.setLocation(curX, curY);
		
	
		this.entityManager = new EntityManager(handler, handler.getGame().getPlayer());
		this.itemManager = new ItemManager(handler);
		
		this.enemyCount = 0;
		
		loadWorld();
		
		
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public ItemManager getItemManager() {
		return this.itemManager;
	}
	
	private void enemyGenerate() {
		
		int x = 0;
		int y = 0;
		double rangeMin = 0.10;
		double rangeMax = 0.25;
		double pZero = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
		double probabDistr = pZero*Math.exp((double)handler.getGame().getPlayer().getHealth() /   
				(double)(handler.getGame().getPlayer().getLevelHealth() + handler.getGame().getPlayer().getLevelHealth()));
		if(random.nextFloat() <= probabDistr) {
			
			switch(handler.getGame().getPlayer().getLevel()) {
				case 1 :
		
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new GiantRat(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new Goblin(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					break;
					
				case 2 :
					
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new GiantRat(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new Goblin(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new GraySlime(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					break;
					
				case 3 :
					
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new GraySlime(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new OrcGrunt(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new OrcWarlord(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new Skeleton(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					break;
					
				case 4 :
					
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new Skeleton(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new OrcGrunt(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new OrcWarlord(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new Ettin(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					break;
					
				case 5 :
					
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new Skeleton(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new Wyrm(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
					x = random.nextInt(width);
					y = random.nextInt(height);
					if(getTile(x, y).isWalkable()) {
						entityManager.addEntity(new Vampire(handler, (float)x * Tile.TILE_WIDTH, (float)y * Tile.TILE_HEIGHT ));
						enemyCount++ ;
					}
			}
		}
		
	}
	
	public void update() {
		
		if(enemyCount < maxNumberOfEnemies)
			enemyGenerate();
		entityManager.update();
		itemManager.update();
	}
	
	public void render(Graphics g) {

		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				
				if(tileVisible(x, y)) {
					tileMap[x][y].setUnknown(false);
					tileMap[x][y].setFogged(false);
					tileMap[x][y].render(g, (int)(x*Tile.TILE_WIDTH), (int)(y*Tile.TILE_HEIGHT));
				}
				else if(!tileMap[x][y].isUnknown()) {
					tileMap[x][y].setFogged(true);
					tileMap[x][y].render(g, (int)(x*Tile.TILE_WIDTH), (int)(y*Tile.TILE_HEIGHT));
				}
				else {
					tileMap[x][y].render(g, (int)(x*Tile.TILE_WIDTH), (int)(y*Tile.TILE_HEIGHT));
				}	
			}
				
		}
		
		
		entityManager.render(g);
		itemManager.render(g);
		
		
	}
	
	public Tile getTile(int x, int y) {
		// make sure we don't get outside the map
		if(x < 0 || y < 0 || x >= width || y >= height ) {
			return new WallTile(x, y);
		}
		
		Tile t = tileMap[x][y];
		if(t == null) 
			return new WallTile(x, y);
		return t;		
	}
	
	public void loadWorld() {
		
		int filled = 0;
	
		for(int x = 0;x < width;x++) {
			for(int y = 0;y < height;y++) {
				tileMap[x][y] = new WallTile(x, y);
			}
		}
		
		while(filled /(float)(width*height) < percentage) {
			selectPointInRandomDirection(curPoint);
			if(tileMap[(int)curPoint.getX()][(int)curPoint.getY()].getTileType() == TileType.WallTile) {
				tileMap[(int)curPoint.getX()][(int)curPoint.getY()] = new PathTile((int)curPoint.getX(), (int)curPoint.getY());
				filled += 1;
			}
		}
		
		tileMap[(int)startPoint.getX()][(int)startPoint.getY()] = new EntranceTile((int)startPoint.getX(), (int)startPoint.getY());
		int dist = 0;
		int newDist = 0;
		int exitX = (int)exitPoint.getX();
		int exitY = (int)exitPoint.getY();
		for (int x = 0; x < width;x++) {
			for (int y = 0; y< height; y++) {
				newDist = Math.abs(x - exitX) + Math.abs(y - exitY);
				if(newDist > dist && tileMap[x][y].getTileType() == TileType.PathTile) {
					exitX = x;
					exitY = y;
					dist = newDist;
				}
			}
		}
		exitPoint.setLocation(exitX, exitY);
		tileMap[exitX][exitY] = new ExitTile(exitX, exitY);
		
		for(int x = 0; x < maxNumberOfHealthPotions; x++) {
			int nowX = random.nextInt(width);
			int nowY = random.nextInt(height);
			if(tileMap[nowX][nowY].getTileType() == TileType.PathTile) {
				itemManager.addItem(new HealthPotion(handler, nowX, nowY));	
				tileMap[nowX][nowY] = new ItemTile(nowX, nowY);
			}
		}
		for(int x = 0; x < maxNumberOfManaPotions; x++) {
			int nowX = random.nextInt(width);
			int nowY = random.nextInt(height);
			if(tileMap[nowX][nowY].getTileType() == TileType.PathTile) {
				itemManager.addItem(new ManaPotion(handler, nowX, nowY));
				tileMap[nowX][nowY] = new ItemTile(nowX, nowY);
			}
		}
		
		for(int x = 0; x < maxNumberOfEquippables; x++) {
			int nowX = random.nextInt(width);
			int nowY = random.nextInt(height);
			if(tileMap[nowX][nowY].getTileType() == TileType.PathTile) {
				itemManager.addItem(new RelicOfTheAncients(handler, nowX, nowY));
				tileMap[nowX][nowY] = new ItemTile(nowX, nowY);
			}
		}		
	}
	
	public void selectPointInRandomDirection(Point point) throws ArrayIndexOutOfBoundsException{
		int x = (int)point.getX();
		int y = (int)point.getY();
		int randir = random.nextInt(4);
		switch(randir) {
			case 0:
				x++;
				break;
			case 1:
				x--;
				break;
			case 2:
				y++;
				break;
			case 3:
				y--;
				break;
			default:
				break;		
		}
		try {
			if(x<0 || x>width-1 || y<0 || y>height-1)
				throw new ArrayIndexOutOfBoundsException("OUT OF BOUNDS");
		}
		catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println(ae);
			return;
		}
		point.setLocation(x, y);
	}
	
	public boolean tileVisible(int x, int y) {
		
		return (Math.abs((int)(handler.getGame().getPlayer().getX() / Tile.TILE_WIDTH) - x) + Math.abs((int)(handler.getGame().getPlayer().getY() / Tile.TILE_HEIGHT) - y)) < visibilityRadius;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public Point getExitPoint() {
		return exitPoint;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public Tile[][] getTileMap() {
		return tileMap;
	}
		
	public int getEnemyCount() {
		return this.enemyCount;
	}
	
	public void setEnemyCount(int n) {
		this.enemyCount = n;
	}
	
	
}
