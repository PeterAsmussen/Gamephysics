package drawStuff;

import javax.swing.*;

import m_v_s.S2;
import m_v_s.V2;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedTest extends JFrame {

  public SpeedTest() {
    add(new DrawPanel());
  }

  public static void main(String[] args) {
	SpeedTest frame = new SpeedTest();
    frame.setTitle("SpeedTest");
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

    S2 S=new S2(5,5, 200,700);
    double x0=0;  // m
    double v0=0;      // m/s
    double a=2.23;    // m/(s*s)

    DrawPanel(){
      myTimer.start();
      ts=System.currentTimeMillis();
    }
    
    double x(double t){
      return 0.5*a*t*t+v0*t+x0;
    }
    
    double v(double t){
      return a*t+v0;
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // Update simulation time
      t=(System.currentTimeMillis()-ts)/1000.0;
      
      double x=x(t);
      double v=v(t);
      
      g.drawString("t="+t, 15, 15);
      g.drawString("x="+x, 15, 15+30);
      g.drawString("v="+v, 15, 15+60);
      
      S.drawLine(g, new V2(x0,20), new V2(100,20));   // Vej
      S.drawPoint(g, new V2(x,20), 10);						// Bil    					
      
      if (x==100) {
    	  myTimer.stop();
      }
      
      S.drawAxes(g);
    }
    
    class TimerListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    }
  }

}