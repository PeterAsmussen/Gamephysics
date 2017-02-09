package drawStuff;

import javax.swing.*;

import m_v_s.M3;
import m_v_s.V3;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotateCubeWithCamera extends JFrame {

  public RotateCubeWithCamera() {
    add(new DrawPanel());
  }

  public static void main(String[] args) {
    RotateCubeWithCamera frame = new RotateCubeWithCamera();
    frame.setTitle("RotateCubeWithCamera");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 700);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  class DrawPanel extends JPanel {
    // Animation
    int framerate=25;
    int delay=1000/framerate;
    Timer myTimer=new Timer(delay, new TimerListener());
    
    // Simulate time
    double ts;
    double t;     // simulation time in sec.

    // App
    Camera S=new Camera(100,100, 400,400);
    
    M3 I=new M3(	1, 0, 0,
    					0, 1, 0,
    					0, 0, 1);
    M3 Sz=new M3(	0, -1, 0,
                 			1,  0, 0,
                 			0,  0, 0);
    
    double phi=Math.PI/100;
    M3 Rz=I.add(Sz.mul(Math.sin(phi))).add(Sz.mul(Sz).mul(1-Math.cos(phi)));
    V3[] cube=new V3[8];
    V3 c=new V3(0,0,0);

    DrawPanel(){
      cube[0]=new V3(1,4,1);
      cube[1]=new V3(1,4,3);
      cube[2]=new V3(1,6,1);
      cube[3]=new V3(1,6,3);
      cube[4]=new V3(3,4,1);
      cube[5]=new V3(3,4,3);
      cube[6]=new V3(3,6,1);
      cube[7]=new V3(3,6,3);
      for (int i=0; i<cube.length; i++) c=c.add(cube[i]);
      c=c.mul(1.0/cube.length);
      
      S.moveTo(new V3(10,5,2));
      S.focus(c);
      S.z=5;
      
      myTimer.start();
      ts=System.currentTimeMillis();
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // Update simulation time
      t=(System.currentTimeMillis()-ts)/1000.0;
      
      for (int i=0; i<cube.length; i++){
        cube[i]=Rz.mul(cube[i].sub(c)).add(c);
      }
      
      S.drawAxes(g);
      S.drawLine(g, cube[0], cube[1]);
      S.drawLine(g, cube[1], cube[3]);
      S.drawLine(g, cube[3], cube[2]);
      S.drawLine(g, cube[2], cube[0]);
      S.drawLine(g, cube[4], cube[5]);
      S.drawLine(g, cube[5], cube[7]);
      S.drawLine(g, cube[7], cube[6]);
      S.drawLine(g, cube[6], cube[4]);
      S.drawLine(g, cube[0], cube[4]);
      S.drawLine(g, cube[1], cube[5]);
      S.drawLine(g, cube[3], cube[7]);
      S.drawLine(g, cube[2], cube[6]);
      
    }
    
    class TimerListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    }
  }
}