import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Sprite {
	
	//Koden skriven av Andreas Abramo(980917-0310) & Gustav af Ekenstam(970505-5995)
		
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void update(Keyboard keyboard) {
		if(keyboard.isKeyDown(Key.Left) && (getX() != 10)) 
			setX(getX() - 5);
		
		if(keyboard.isKeyDown(Key.Right) && (getX() != 330))
			setX(getX() + 5);	
	}
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.white);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());	
	}
}
