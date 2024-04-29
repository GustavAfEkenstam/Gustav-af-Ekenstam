 
public class Score  {

	//Koden skriven av Andreas Abramo(980917-0310) & Gustav af Ekenstam(970505-5995)
	
	private String name;
	private int score=0; 
	
	public Score(String name, int score) {
		this.setName(name);
		this.setScore(score);
	}
	
	public int getScore() {
		return score;	
	}
	
	public String getName() {
		return name;
	}
	
	public void setScore(int Score) {
		this.score += Score;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + "\t" + score;
	}
	
	public void resetScore() {
		score = 0;
	}
}
