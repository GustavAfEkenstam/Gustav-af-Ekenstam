import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks extends Sprite {

	//Koden skriven av Andreas Abramo(980917-0310) & Gustav af Ekenstam(970505-5995)
	
	private Color color;
	
	public Bricks(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;	
	}
	@Override
	public void update(Keyboard keyboard) {
		
	}
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
