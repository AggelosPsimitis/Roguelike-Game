package Main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	private JPanel panel;
	private PlayerStatus playerStatus;
	private GameLog gameLog;

	private String title;
	private int width, height;

	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height)); // 960 x 480
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);

		panel = new JPanel(new BorderLayout());

		playerStatus = new PlayerStatus();
		gameLog = new GameLog();

		panel.add(gameLog, BorderLayout.SOUTH);
		panel.add(playerStatus, BorderLayout.EAST);

		panel.add(canvas);

		frame.add(panel);

		frame.pack();
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public PlayerStatus getPlayerStatus() {
		return this.playerStatus;
	}

	public GameLog getGameLog() {
		return this.gameLog;
	}
}