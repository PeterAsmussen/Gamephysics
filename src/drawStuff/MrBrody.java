package drawStuff;

import javax.swing.*;

import m_v_s.S2;
import m_v_s.V2;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MrBrody extends JFrame {

  public MrBrody() {
    add(new DrawPanel());
  }

  public static void main(String[] args) {
    SpeedTest frame = new SpeedTest();
    frame.setTitle("MrBrody_1");
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

    S2 S=new S2(15,15, 200,700);
    double x0=41.15;  // m
    double v0=0;      // m/s
    double g=9.82;    // m/(s*s)

    DrawPanel(){
      myTimer.start();
      ts=System.currentTimeMillis();
    }
    
    double x(double t){
      return -0.5*g*t*t+v0*t+x0;
    }
    
    double v(double t){
      return -g*t+v0;
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
      g.drawString("R.I.P. Mr. Brody", 200, 600);
      S.drawLine(g, new V2(-20,x0), new V2(20,x0));   // Bro
      S.drawPoint(g, new V2(0,x), 10);    // Mr Brody
      if (x>=0) myTimer.stop();
      
      S.drawAxes(g);
    }
    
    class TimerListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    }
  }

}