package drawStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Lines extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5057327000506172556L;
	private final int Delay = 150;
	private Timer timer;
	
	public Lines(){
		initTimer();
	}
	
	private void initTimer() {
		timer = new Timer(Delay, this);
		timer.start();
	}

	public Timer getTimer(){
		return timer;
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setPaint(Color.red);
		g2D.draw(new Line2D.Double(10, 800, 600, 50));
		g2D.draw(new Ellipse2D.Double(300, 150, 200, 107));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		doDrawing(g);
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}

