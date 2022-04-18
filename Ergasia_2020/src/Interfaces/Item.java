package Interfaces;

import java.awt.Graphics;
import java.util.Set;
import Items.ItemEffect;

public interface Item {
	
	public static final int DEFAULT_WIDTH = 4; 
	public static final int DEFAULT_HEIGHT = 4;
	Set<ItemEffect> getItemEffects();
	boolean isActive();
	void update();
	void render(Graphics g);
	int getX();
	int getY();
	void setIsActive(boolean active);
	String getName();
	
}
