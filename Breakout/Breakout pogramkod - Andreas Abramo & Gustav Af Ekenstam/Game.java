import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
public class Game {
	
	//Koden skriven av Andreas Abramo(980917-0310) & Gustav af Ekenstam(970505-5995)
	
	private static final int WINDOW_WIDTH = 400;
	
	private Player player;
	private Ball ball;
	private ArrayList<LightBrick> lightBricks;
	private ArrayList<MediumBricks> mediumBricks;
	private ArrayList<StrongBricks> strongBricks;
	private ArrayList<Walls> walls;
	private int scoreGame=0;
	private boolean isRunning = true;
	private Highscore highscore = new Highscore();
	
	private String name;
	private Score score = new Score(name, scoreGame);
	
	public Game(GameBoard board) {		
		lightBricks = new ArrayList<LightBrick>();
		for(int i=0; i<9; i++) {
			lightBricks.add(new LightBrick(i*40+22, 80, 35, 10, Color.yellow));
		}
		mediumBricks = new ArrayList<MediumBricks>();
		for(int i=0; i<9; i++) {
			mediumBricks.add(new MediumBricks(i*40+22, 65, 35, 10, Color.green));
		}
		strongBricks = new ArrayList<StrongBricks>();
		for(int i=0; i<9; i++) {
			strongBricks.add(new StrongBricks(i*40+22, 50, 35, 10, Color.magenta));
		}
		player = new Player(WINDOW_WIDTH / 2 - 30, 550, 60, 10);
		ball = new Ball(100, 300, 16, 16);
		
		walls = new ArrayList<Walls>();
		walls.add(new Walls(0, 0, 10, 600, Color.gray));
		walls.add(new Walls(0, 0, 400, 10, Color.gray));
		walls.add(new Walls(390, 0, 10, 600, Color.gray));
		
	}
	
	public void update(Keyboard keyboard) {
		if(isRunning) {
			player.update(keyboard);
			ball.update(keyboard);
			checkObject(player);
			ballOut();
			maxScore();
			
			for(Iterator<LightBrick> itr = lightBricks.iterator(); itr.hasNext();) {
				LightBrick lightBrick = itr.next();
				if(checkObject(lightBrick)) {
					score.setScore(50);

					lightBrick.setLife();

					if(lightBrick.getLife() == 0) {
						itr.remove();
					}
				}
			}
			for(Iterator<MediumBricks> itr = mediumBricks.iterator(); itr.hasNext();) {
				MediumBricks mediumBrick = itr.next();
				if(checkObject(mediumBrick)) {
					mediumBrick.setLife();
					score.setScore(100);
					if(mediumBrick.getLife() == 0) {
						itr.remove();
					}
				}		
			}
			for(Iterator<StrongBricks> itr = strongBricks.iterator(); itr.hasNext();) {
				StrongBricks strongBrick = itr.next();
				if(checkObject(strongBrick)) {
					score.setScore(150);
					strongBrick.setLife();
					if(strongBrick.getLife() == 0) {
						itr.remove();
					}
				}
			}
			if(ball.getY() >= 600 && ball.getY() <= 610) {
				ball.setX(80);
				ball.setY(200);
				ball.removeLife();
			}
		}
		else {
			if(keyboard.isKeyDown(Key.Space))
				reset();
		}
	}
		
	public boolean checkObject(Sprite obj) {
		boolean has_collided = false; 
	
		if(checkCollision(ball.getX(), ball.getY(),
				obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
			has_collided = true;
		}
		else if(checkCollision(ball.getX() + ball.getWidth()/2, ball.getY()+ball.getHeight()/2,
				obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
			has_collided = true;
		}
		else if(checkCollision(ball.getX() + ball.getWidth(), ball.getY(),
				obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
			has_collided = true;
		}
		else if(checkCollision(ball.getX(), ball.getY() + ball.getHeight(),
				obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
			has_collided = true;
		}
		else if(checkCollision(ball.getX()+ ball.getWidth(), ball.getY() + ball.getHeight(),
				obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
			has_collided = true;
		}
		if (has_collided) {
			int ballMiddleX = (ball.getX() + ball.getWidth()/2);
			int ballMiddleY = (ball.getY() + ball.getHeight()/2);
			int objMiddleX = (obj.getX() + obj.getWidth()/2);
			int objMiddleY = (obj.getY() + obj.getHeight()/2);
			
			if(ballMiddleY <= objMiddleY) {
				ball.setY(ball.getY() - ball.getHeight()/2);
				ball.changeDirY();
			}
			else if(ballMiddleY >= objMiddleY) {
				ball.setY(ball.getY() + ball.getHeight()/2);
				ball.changeDirY();
			}
			else if(ballMiddleX <= objMiddleX) {
				ball.setX(ball.getX() - ball.getWidth()/2);
				ball.changeDirX();
			}
			else if(ballMiddleX >= objMiddleX) {
				ball.setY(ball.getX() + ball.getWidth()/2);
				ball.changeDirX();		
			}
			return true;
		}
		return false;
	}
	
	public boolean checkCollision(int obj1x, int obj1y, int obj2x, int obj2y, int obj2w, int obj2h) {
		if(obj1y >= obj2y && obj1y <= obj2y + obj2h) {
			if(obj1x >= obj2x && obj1x <= obj2x + obj2w){
				return true;
			}
		}	
		return false; 
	}

	public void draw(Graphics2D graphics) {
		player.draw(graphics);
		ball.draw(graphics);
		for(Walls wall : walls) {
			wall.draw(graphics);
		}
		for(LightBrick brick : lightBricks) {
			brick.draw(graphics);
		}
		for(MediumBricks brick : mediumBricks) {
			brick.draw(graphics);
		}
		for(StrongBricks brick : strongBricks) {
			brick.draw(graphics);
		}
		graphics.setColor(Color.green);
		graphics.setFont(new Font("serif", Font.BOLD, 20));
		if(ball.getLife()==0 || score.getScore() == 6300) {
			graphics.drawString("Tryck space för att spela igen",  100, 300);
		}
		graphics.drawString("Score :" + score.getScore() , 700, 20);
		graphics.drawString("Balls left: " + ball.getLife(), 700, 40);			
	}
	
	private void ballOut() {
		if(ball.getLife() == 0) {	
			isRunning = false;
			highscore.addHighscore(score);
			highscore.highscoreGUI();
			ball.pauseBall();
		}
	}
	private void maxScore() {
		if (score.getScore() == 6300) {	
			isRunning = false;
			highscore.addHighscore(score);			
			highscore.highscoreGUI();
			ball.pauseBall();
		}
	}
	
	private void reset() {
		score.resetScore();
		ball.resetBallLife();
		isRunning = true;
		
		lightBricks = new ArrayList<LightBrick>();
		for(int i=0; i<9; i++) {
			lightBricks.add(new LightBrick(i*40+22, 80, 35, 10, Color.yellow));
		}
		mediumBricks = new ArrayList<MediumBricks>();
		for(int i=0; i<9; i++) {
			mediumBricks.add(new MediumBricks(i*40+22, 65, 35, 10, Color.green));
		}
		strongBricks = new ArrayList<StrongBricks>();
		for(int i=0; i<9; i++) {
			strongBricks.add(new StrongBricks(i*40+22, 50, 35, 10, Color.magenta));
		}
		
		player = new Player(WINDOW_WIDTH / 2 - 30, 550, 60, 10);
		ball = new Ball(100, 300, 16, 16);
		
		walls = new ArrayList<Walls>();
		walls.add(new Walls(0, 0, 10, 600, Color.gray));
		walls.add(new Walls(0, 0, 400, 10, Color.gray));
		walls.add(new Walls(390, 0, 10, 600, Color.gray));
	}
}
