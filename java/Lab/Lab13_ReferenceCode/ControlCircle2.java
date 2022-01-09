

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ControlCircle2 extends JFrame {
	private JButton jbtEnlarge = new JButton("Enlarge");
	private JButton jbtShrink = new JButton("Shrink");
	private CirclePanel canvas = new CirclePanel();

	public ControlCircle2() {
		JPanel panel = new JPanel(); // Use the panel to group buttons
		panel.add(jbtEnlarge);
		panel.add(jbtShrink);
		
		this.add(canvas, BorderLayout.CENTER); // Add canvas to center
		this.add(panel, BorderLayout.SOUTH); // Add buttons to the frame

		Listener l = new Listener();
		jbtEnlarge.addActionListener(l);
		jbtShrink.addActionListener(l);
		
	}

	/** Main method */
	public static void main(String[] args) {
		JFrame frame = new ControlCircle2();
		frame.setTitle("ControlCircle2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

		frame.setVisible(true);
	}

	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbtEnlarge){
//				System.out.println("HEllo");
				canvas.enlarge();
			}else if(e.getSource() == jbtShrink){
				canvas.shrink();
			}
//			System.out.println("HE");
		}
	}
}

class CirclePanel extends JPanel {
	private int radius = 50; // Default circle radius

	/** Enlarge the circle */
	public void enlarge() {
		radius = (int)(radius * 1.1);
		this.repaint();
		
	}

	/** Enlarge the circle */
	public void shrink() {
		radius = (int)(radius * 0.9);
		this.repaint();
	}

	/** Repaint the circle */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawOval(this.getWidth()/2-radius, this.getHeight()/2-radius, 2 * radius, 2 * radius);
	}
}