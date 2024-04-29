package borda;
//Koden är skriven av Andreas Abramo (980917-0310) & Gustav af Ekenstam (970505-5995)
public class Backend {
	public final int WIDTH = 75;
	public final int HEIGHT = 75;
	private int x;
	private int y;
	private boolean activated;
	private boolean booked = false;
	public Backend(int x, int y) {
		this.x = x;
		this.y = y;
	}	

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isActivated() {
		return activated; 
	}
	
	public void toggleActivated() {
		activated = ! activated; 
	}
	
	public void book() {
		booked = true;
	}
	
	public boolean isBooked() {
		return booked;
	}
	
	public void cancelBooking() {
		booked = false;
	}

}
