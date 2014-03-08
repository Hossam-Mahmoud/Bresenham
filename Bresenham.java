import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Bresenham extends JFrame {

	int startX = 20;
	int startY = 20;
	int rows = 14;
	int rowLength = 700;
	int cols = 15;
	int colLength = -1;
	int displacement = 50;
	int finalY = startY + rows * displacement;
	ArrayList<Point> filledCells;
	final JPanel grid; 
	
	public Bresenham() {
		filledCells = new ArrayList<Point>();
		
		grid = new JPanel() {
			public void paintComponent(Graphics g) {
				drawGrid(g);
				drawFilled(g);
			}
		};
		
		addLabels();
		
		setSize(800, 800);
		setVisible(true);
	}
	
	
	// Initialize the grid
	private void drawGrid(Graphics g){
		int currX = startX;
		int currY = startY;
		int endX = currX + rowLength;
		int endY = currY;
		for (int i = 0; i < rows; i++) {
			g.drawLine(currX, currY, endX, endY);
			currY += displacement;
			endY += displacement;
		}
		
		colLength = endY;
		currX = startX;
		currY = startY;
		endX = currX;
		endY = colLength - displacement;
		for (int i = 0; i < cols; i++) {
			g.drawLine(currX, currY, endX, endY);
			currX += displacement;
			endX += displacement;
		}
	}
	
	
	// Called by repaint() function to draw colored pixels
	private void drawFilled(Graphics g) {
		g.setColor(Color.RED);
		for(Point p: filledCells) {
			g.fillRect(p.x, p.y, displacement, displacement);
		}
	}
	
	
	// Adding UI components
	private JPanel addLabels() {
		JLabel x1Label = new JLabel("x1");
		JLabel y1Label = new JLabel("y1");
		JLabel x2Label = new JLabel("x2");
		JLabel y2Label = new JLabel("y2");
		
		final JTextField x1Text = new JTextField();
		final JTextField y1Text = new JTextField();
		final JTextField x2Text = new JTextField();
		final JTextField y2Text = new JTextField();
		
		JButton button = new JButton("Draw");
		button.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x1 = new Integer(x1Text.getText());
				int y1 = new Integer(y1Text.getText());
				int x2 = new Integer(x2Text.getText());
				int y2 = new Integer(y2Text.getText());
				filledCells.clear();
				bresenham(x1, y1, x2, y2);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
		
		JPanel panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(panel);
		panel.setLayout(groupLayout);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
		add(panel);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
						.addComponent(grid)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(x1Label)
								).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(x1Text)
								).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(y1Label)
								).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(y1Text)
								).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(x2Label)
								).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(x2Text)
								).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(y2Label)
								).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(y2Text)
								).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(button))
				        )
		);
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
			    		  .addComponent(grid)
			    		  .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    				  .addComponent(x1Label)
			    				  .addComponent(x1Text)
			    				  .addComponent(y1Label)
			    				  .addComponent(y1Text)
			    				  .addComponent(x2Label)
			    				  .addComponent(x2Text)
			    				  .addComponent(y2Label)
			    				  .addComponent(y2Text)
			    				  .addComponent(button)
			    		  )
		);
		
		return panel;
	}
	
	private void bresenham(int x1, int y1, int x2, int y2) {
		// TODO: Implement Bresenham algorithm which draws line from (x1, y1) to (x2, y2)
		
	}
	
	// colors the pixel whose pivot is (x,y)
	// (0,0) is in the left lower corner of the grid
	private void plot(int x, int y) {
		int xCorner = startX + x * displacement;
		int yCorner = finalY - (y + 2) * displacement;
		filledCells.add(new Point(xCorner, yCorner));
		repaint();
		grid.repaint();
	}

	public static void main(String[] args) {
		 new Bresenham();
	}
}
