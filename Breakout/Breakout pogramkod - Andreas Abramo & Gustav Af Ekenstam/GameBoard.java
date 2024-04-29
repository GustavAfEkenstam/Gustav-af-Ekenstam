import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class GameBoard extends JComponent {
	private final int FPS = 40; 
	private Game game;
	private Keyboard keyboard;
	private static boolean gameRunning = true;
	
	private Highscore highscore = new Highscore();
	public GameBoard() {
		keyboard = new Keyboard();
		game = new Game(this);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D graphics = (Graphics2D)arg0;
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		game.draw(graphics);
		
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		if (e.getID() == KeyEvent.KEY_PRESSED)
			keyboard.processKeyEvent(e.getKeyCode(), true);
		else if (e.getID() == KeyEvent.KEY_RELEASED)
			keyboard.processKeyEvent(e.getKeyCode(), false);
	}
	
	public void start() {	
		while(true) {
			game.update(keyboard);
			try {
				Thread.sleep(1000 / FPS); //Throttle thread
				//System.out.println("Spelet körs i thread " + t++);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Spelet körs i catch");
			}
			this.repaint();
		}	
	}
	
}

