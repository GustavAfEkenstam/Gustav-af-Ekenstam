import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class LightBrick extends Bricks {
	
	//Koden skriven av Andreas Abramo(980917-0310) & Gustav af Ekenstam(970505-5995)
	
	private int life=1;
	public LightBrick(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		
	}
	public void update(Keyboard keyboard) {	
		
	}
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.yellow);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	public int setLife() {
		life-=1;
		return life;
	}
	public int getLife() {
		return life;
		
	}
}
