package Main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

public class Launcher {

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		EventQueue.invokeLater( () -> {
			Game game = new Game("Quest for the Ring of Werdna the Sorcerer Wizzard", screenSize.width, screenSize.height);
			game.start();
		});
	}
}
