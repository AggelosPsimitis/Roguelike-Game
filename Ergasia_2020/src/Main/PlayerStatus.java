package Main;

import javax.swing.JTextArea;

public class PlayerStatus extends JTextArea implements MyObserver {

	/**
	 * H parakatw metavliti einai suggestion tou Eclipse
	 */
	private static final long serialVersionUID = 385088433144472557L;
	
	public PlayerStatus() {

		super(35, 50);
		this.append("Player Status :");
	}

	@Override
	public void update(Object o) {

		this.setText((String) o);

	}

}
