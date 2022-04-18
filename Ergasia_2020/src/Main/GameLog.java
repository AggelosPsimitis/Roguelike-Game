package Main;

import java.awt.Font;
import javax.swing.JTextArea;

public class GameLog extends JTextArea implements MyObserver {

	/**
	 * H parakatw metavliti einai suggestion tou Eclipse
	 */
	private static final long serialVersionUID = 2666233036851704988L;

	private int logCount;
	
	public GameLog() {

		super(20, 20);
		this.setFont(new Font("Serif", Font.ITALIC, 16));
		this.logCount = 0;
		this.setEditable(false);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);

	}

	@Override
	public void update(Object o) {

		if((String)o != "") {
			this.append((String)o);
			this.append("\n");
			logCount++;
			if(logCount > 15) {
				selectAll();
				replaceSelection("");
				logCount = 0;
			}
		}
 
	}

}
