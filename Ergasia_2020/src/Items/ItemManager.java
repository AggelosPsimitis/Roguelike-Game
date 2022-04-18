package Items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Interfaces.Item;
import Main.State.Handler;

public class ItemManager {

	private Handler handler;
	private List<Item> items;
	
	
	public ItemManager (Handler handler) {
		
		this.handler = handler;
		this.items = new ArrayList<>();
		
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	
	
	public List<Item> getItems() {
		return this.items;
	}


	public Item getItem(int x, int y){

		for (Item i : this.items) {
			
			if(x == i.getX() && y == i.getY()) {
				return i;
			}
		}
		return null;
	}
	
	public void update() {
		
		for(Iterator<Item> iterator = this.items.iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			if(!item.isActive()) {
				iterator.remove();
			}
		}
	}
	
	
	public void render(Graphics g) {
		
		for(Item i : this.items) {
			
			i.render(g);
		}	
		
	}
}

