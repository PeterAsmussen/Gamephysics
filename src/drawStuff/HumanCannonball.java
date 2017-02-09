package drawStuff;

import javax.swing.*;

import drawStuff.AirDrop.DrawPanel.TimerListener;
import m_v_s.S2;
import m_v_s.V2;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HumanCannonball extends JFrame {

	public HumanCannonball(){
		add(new DrawPanel());
	}
	public static void main(String[] args) {
		AirDrop frame = new AirDrop();
		frame.setTitle("Human Cannonball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 1000);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}

	class DrawPanel extends JPanel {
		// Animation
		int framerate=500;
		int delay=1000/framerate;
		Timer myTimer=new Timer(delay, new TimerListener());

		// Simulate time
		double ts;
		double t;     // simulation time in sec.

		S2 S=new S2(1,1, 10,800);
		double g=9.82;    // m/(s*s)
		V2 r0=new V2(0, 300);
		V2 v0=new V2(500.0*1000/(60.0*60.0), 0);
		V2 a=new V2(0,-g);
		ArrayList<V2> traj=new ArrayList<V2>();

		DrawPanel(){
			myTimer.start();
			ts=System.currentTimeMillis();
		}

		V2 r(double t){
			return a.mul(0.5*t*t).add(v0.mul(t)).add(r0);
			//		      return -0.5*g*t*t+v0*t+x0;
		}

		V2 v(double t){
			return a.mul(t).add(v0);
			//		      return -g*t+v0;
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Update simulation time
			t=(System.currentTimeMillis()-ts)/1000.0;

			V2 r=r(t);
			V2 v=v(t);
			traj.add(r);

			g.drawString("t="+t, 10, 10);
			g.drawString("r="+r, 10, 10+30);
			g.drawString("v="+v, 10, 10+60);
			S.drawLine(g, new V2(0,0), new V2(1100,0));
			S.drawLine(g, new V2(0,0), new V2(0,300));
			for (V2 p: traj) S.drawPoint(g, p, 5);
			if (r.y<=0) myTimer.stop();
			S.drawAxes(g);
		}

		class TimerListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		}
	}	  
}
