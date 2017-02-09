package drawStuff;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import m_v_s.S2;
import m_v_s.V2;



public class LineCrossEllipse extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3559374147450350554L;

	public LineCrossEllipse() {
		add(new DrawPanel());
	}

	public static void main(String[] args) {
		LineCrossEllipse frame = new LineCrossEllipse();
		frame.setTitle("Line x Ellipse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}

	public class DrawPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3945235658596193971L;
		S2 S = new S2(50,50,200,400);
		V2 l1 = new V2(10, 15.0/3);
		V2 l2 = new V2(0 , -5.0/3);
		V2 c = new V2(4,3);
		double a=3; double b=2;

		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			S.drawLine(g, l1, l2);
			for (double rho=0; rho<Math.PI*2;rho+=0.01){
				V2 p = c.add(new V2(a*Math.cos(rho), b*Math.sin(rho)));
				S.drawPoint(g, p, 5);
			}
			S.drawAxes(g);
		}

	}

}

