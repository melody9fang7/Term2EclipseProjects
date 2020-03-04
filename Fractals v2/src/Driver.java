import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener {

	/* Attributes a.k.a. Instance Variables */
	int w = 800, h = 800;

	public void paint(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 800);

		rings(g, 200, 290, 270);

		

	}// end of paint method - put code above for anything dealing with drawing -

	

	public void clover(Graphics g, int radius, int x, int y) {

		// each method is in charged of drawing 4 circles
		g.setColor(Color.black);
		g.drawOval(x, y, radius, radius);
		g.drawOval(x + radius, y, radius, radius);
		g.drawOval(x, y + radius, radius, radius);
		g.drawOval(x + radius, y + radius, radius, radius);

		// it will then invoke a new method to keep drawing

		if (radius > 2) {
			clover(g, radius - 5, x + 5, y + 5);
		}

	}
	
	public void lines(Graphics g, int r, int x, int y, int angle) {

		int nx = (int) (Math.cos(angle * Math.PI / 180) * r) / 2 + x;
		int ny = (int) (Math.sin(angle * Math.PI / 180) * r) / 2 + y;

		g.setColor(Color.magenta);
		g.drawLine(x, y, nx, ny);

		if (angle <= 360) {
			lines(g, r, x, y, angle + 10);

		}

	}

	public void sphere(Graphics g, int radius, int x, int y, int angle) {
			// each method call draws one part of the fractal
		g.setColor(Color.red);
		rings(g, 700, 50, 30);
		
		lines(g, 700, 400, 380, 0);
		
		g.setColor(Color.blue);

		x = (int) (Math.cos(angle * Math.PI / 180) * radius / 4);
		y = (int) (Math.sin(angle * Math.PI / 180) * radius / 4);
		
		g.drawOval(x + 225, y + 205, radius / 2, radius / 2);

		g.setColor(Color.cyan);
		g.drawOval(50, 30, 700, 700);


		if (angle < 360) {
			sphere(g, radius, x, y, angle + 10);

		}

	}

	public void rings(Graphics g, int radius, int x, int y) {
		// each method call draws one part of the fractal
		g.setColor(Color.red);
		g.drawOval(x, y, radius, radius);

		if (radius > 10) {
			rings(g, radius - 20, x + 10, y + 10);
		}

	}

	public void square(Graphics g, int length, int x, int y) {

		int r = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		int gr = (int) (Math.random() * 255);

		Color c = new Color(r, gr, b);

		g.setColor(c);

		// each method is in charged of drawing 4 circles
		g.fillRect(x, y, length, length);

		// it will then invoke a new method to keep drawing

		if (length > 1) {
			// top row
			square(g, length / 4, x - 2 * (length / 4), y - 2 * (length / 4));
			square(g, length / 4, x + 2 * (length / 5), y - 2 * (length / 4));
			square(g, length / 4, x + (length + length / 4), y - 2
					* (length / 4));

			square(g, length / 4, x - 2 * (length / 4), y + 2 * (length / 5));
			square(g, length / 4, x + (length + length / 4), y + 2
					* (length / 5));

			// top row
			square(g, length / 4, x - 2 * (length / 4), y
					+ (length + length / 4));
			square(g, length / 4, x + 2 * (length / 5), y
					+ (length + length / 4));
			square(g, length / 4, x + (length + length / 4), y
					+ (length + length / 4));

		}

	}

	/**
	 * Update the positions of the ball and paddle. Update the scores, counter
	 * and time
	 */
	public void update() {

	}// end of update method - put code above for any updates on variable

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	/* Instantiate any attributes here (instance variables */
	public Driver() {

		JFrame f = new JFrame();
		f.setTitle("Pong");
		f.setSize(w, h);
		f.setBackground(Color.BLACK);
		f.setResizable(false);

		f.add(this);
		t = new Timer(17, this);
		// t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	Timer t;

}
