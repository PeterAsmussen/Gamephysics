package drawStuff;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class DrawLines extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -914575999403614200L;

	public DrawLines(){
		initUI();
	}
	
	private void initUI() {
		final Lines panel = new Lines();
		add(panel);


		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Timer timer = panel.getTimer();
				timer.stop();
			}
		});
	}
}
