package drawStuff;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import m_v_s.S2;
import m_v_s.V2;

public class Triangle extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5582325514322731025L;

	public Triangle() {
		add(new DrawPanel());
	}

	public static void main(String[] args) {
		Triangle frame = new Triangle();
		frame.setTitle("Triangle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 700);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}

	class DrawPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1648621327590859686L;
		S2 S=new S2(50,50, 200,400);
		V2 A=new V2(2,2);
		V2 B=new V2(3,4);
		V2 C=new V2(4,2);
		V2 V = new V2(-1, 1);
		
		V2 Am = V.add(A);
		V2 Bm = V.add(B);
		V2 Cm = V.add(C);
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			S.drawAxes(g);
			
			//Draw original triangle
			S.drawLine(g, A, B);
			S.drawLine(g, B, C);
			S.drawLine(g, C, A);
			
			//Draw new/moved/translated triangle
			S.drawLine(g, Am, Bm);
			S.drawLine(g, Bm, Cm);
			S.drawLine(g, Cm, Am);

		}
	}
}
