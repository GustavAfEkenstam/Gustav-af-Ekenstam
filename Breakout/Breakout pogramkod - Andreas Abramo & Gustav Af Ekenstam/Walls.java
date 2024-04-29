import java.awt.Graphics2D;
import java.awt.Color;

public class Walls extends Sprite {
	
	//Koden skriven av Andreas Abramo(980917-0310) & Gustav af Ekenstam(970505-5995)
	
	public Walls(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
	}

	@Override
	public void update(Keyboard keyboard) {
			
	}

	@Override
	public void draw(Graphics2D graphics) {
		// TODO Auto-generated method stub
		graphics.setColor(Color.gray);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
	

