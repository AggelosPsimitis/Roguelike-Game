package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean attack;
	public boolean useHealthPotion;
	public boolean useManaPotion;
	public boolean equip;
	public boolean drop;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		attack = keys[KeyEvent.VK_SPACE];
		useHealthPotion = keys[KeyEvent.VK_H];
		useManaPotion = keys[KeyEvent.VK_M];
		equip = keys[KeyEvent.VK_E];
		drop = keys[KeyEvent.VK_R];
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// get id of key pressed
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;
		
	}

	
	
}
