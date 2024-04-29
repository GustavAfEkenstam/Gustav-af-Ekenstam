import java.awt.Color;
import java.awt.Graphics2D;

public class Ball extends Sprite {
	
	//Koden skriven av Andreas Abramo(980917-0310) & Gustav af Ekenstam(970505-5995)
	
	private  int life = 3; 
	private  int v1 = 5; 
	private  int v2 = 5; 
	
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	@Override
	public void update(Keyboard keyboard) {
		setY(getY() + v1);
		setX(getX() + v2);
		
		if(getY() >= 620  || getY() <= 10+6) {
			changeDirY();
		}
		if(getX() >= 400 - 26 || getX() <= 10+6) {
			changeDirX();
		}
	}
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.white);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	public void changeDirY() {
		v1 = v1 * (-1);	
	}
	
	public void changeDirX() {
		v2 = v2 * (-1);	
	}
	
	public void removeLife() {
		life-=1;
	}
	
	public int getLife() {
		return life;
	}
	
	public void pauseBall() {
		v1=0;
		v2=0;
	}
	
	public void resetBallLife() {
		life = 3;
	}
}
