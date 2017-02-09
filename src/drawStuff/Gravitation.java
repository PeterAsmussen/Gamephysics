package drawStuff;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import m_v_s.S2;
import m_v_s.V2;

public class Gravitation extends JFrame {

  public Gravitation() {
    add(new DrawPanel());
  }

  public static void main(String[] args) {
    Gravitation frame = new Gravitation();
    frame.setTitle("Gravitation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 1000);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  class DrawPanel extends JPanel {
    // Animation
    int framerate=1000;
    int delay=1000/framerate;
    Timer myTimer=new Timer(delay, new TimerListener());
    
    // Simulate time
    double ts;
    double t;     // simulation time in sec.

    double G=6.6727e-11;
    double M=5.97e24;
    double R=6.378e6;
    double w=2.0*Math.PI/(24*60*60);
    S2 S=new S2(10/R,10/R, 600,400);
    V2 r=new V2(6.62*R, 0);
    V2 r2=new V2(9*R, 0);
    V2 rComet=new V2(100*R, 0);
    
    double speed = 6.62*R*w;
    double speed2 = 9*R*w;
    double speedComet = 15000/3.6;
    
    V2 v = new V2(0, speed);
    V2 a;
    
    V2 v2 = new V2(0, speed2);
    V2 a2;

    V2 vComet = new V2(-speedComet*Math.cos(Math.toRadians(5)), speedComet*Math.sin(Math.toRadians(5)));
    V2 aComet;
    
    DrawPanel(){
      myTimer.start();
      ts=System.currentTimeMillis();
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // Update simulation time
      double t_old=t;
      t=(System.currentTimeMillis()-ts)/1000.0;
      double dt=t-t_old;
      dt=(dt*60*60*24)/10.0;
      
      // Step integration
      a=r.unit().mul(-G*M/(r.dot(r)));
      v=v.add(a.mul(dt));
      r=r.add(v.mul(dt));
      S.drawPoint(g, r, 5);
      
      // Step integration for point2
      a2=r.unit().mul(-G*M/(r.dot(r)));
      v2=v2.add(a2.mul(dt));
      r2=r2.add(v2.mul(dt));
      S.drawPoint(g, r2, 5);

      // Step integration for comet
      aComet = rComet.unit().mul(-G*M/(rComet.dot(rComet)));
      vComet = vComet.add(aComet.mul(dt));
      rComet=rComet.add(vComet.mul(dt));
      S.drawPoint(g, rComet, 5);
      
      for (double phi=0; phi<2*Math.PI; phi+=0.1){
          S.drawPoint(g, new V2(Math.cos(phi), Math.sin(phi)).mul(R));
          S.drawPoint(g, new V2(Math.cos(phi), Math.sin(phi)).mul(6.62*R));
      }
      S.drawAxes(g);
      
      
      if (r.length()<=R) myTimer.stop();
    }
    
    class TimerListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    }
  }

}