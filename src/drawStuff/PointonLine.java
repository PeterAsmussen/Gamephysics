package drawStuff;
import javax.swing.*;

import m_v_s.S2;
import m_v_s.V2;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointonLine extends JFrame {

  /**
	 * 
	 */
	private static final long serialVersionUID = -6451786562233157317L;

public PointonLine() {
    add(new DrawPanel());
  }

  public static void main(String[] args) {
    PointonLine frame = new PointonLine();
    frame.setTitle("PointOnLine");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 700);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  public class DrawPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2068727882934126019L;
	// Animation
    int framerate=25;
    int delay=1000/framerate;
    Timer myTimer=new Timer(delay, new TimerListener());
    
    // Simulate time
    double ts;
    double t;     // simulation time in sec.

    S2 S=new S2(50,50, 200,400);
    V2 A=new V2(4,2);
    V2 B=new V2(16,5);
    V2 r=B.sub(A).unit();	
    double s=2;             // virtuelle længdeenheder pr. sec.

    DrawPanel(){
      myTimer.start();
      ts=System.currentTimeMillis();
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // Update simulation time
      t=(System.currentTimeMillis()-ts)/1000.0;
      
      V2 P=A.add(r.mul(s*t));
      S.drawAxes(g);
      S.drawPoint(g, A, 5);;
      S.drawPoint(g, B, 5);
      S.drawPoint(g, P, 10);
      S.drawLine(g, A, B);
      g.drawString("t="+t, 30, 30);
      if (P.x>=B.x) myTimer.stop();
    }
    
    public class TimerListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    }
  }

}