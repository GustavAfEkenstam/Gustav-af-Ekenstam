package se.kau.isgc08.lab4.view;

import java.awt.Color;

import javax.swing.JFrame;
import se.kau.isgc08.lab4.model.*;

public class DrawingView {
	
	private DrawingPanel dp;
	private JFrame f;
	
	public DrawingView() {
		f = new JFrame("CompositeFacade");
		
		f.setSize(500, 500); 
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public DrawingUtil getUtil() {
		
		return new DrawingUtil();
	}
	
	public void setPanel(DrawingComposite dc) {
		dp = new DrawingPanel(dc);
		dp.setBackground(Color.GRAY);
		f.add(dp);
		
	}
	public DrawingPanel getPanel() {
		return dp;
	}
	
	public void repaint() {
		//f.repaint();
		f.revalidate();
	}
}
