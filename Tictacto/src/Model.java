import java.util.Random;

public class Model {
	
	//Koden skriven av Andreas Abramo(980917) och Gustav Af ekenstam(970505), 
	
	private Random random;
	private int flag; 
	private int scoreX = 0;
	private int scoreO = 0;
	private String x;

	//Klasser
	public View view;
	public Control control;
	
	//Match Logic 
	private String currentPlayer;
	private String[] gameState = {"0","0","0",
								  "0","0","0",
								  "0","0","0"};
	
	public Model() {
		flag = 0;
		determinePlayer();
	}

	public void setBoard(int idx) {
		nextMove(idx);
		view.updateB(gameState);
		setFlag();
		matchLogic();
		updateString();
	}
	
	public void determinePlayer() {
		random = new Random();
		String first;
		int slump; 
		slump = random.nextInt(100); 
		if(slump %2 == 0) {
			currentPlayer = "X";
			first = "Player X Start;";
			//view.getPlayerString(first);
		}
		else {
			currentPlayer = "O";
			first = "Player O Start:";

		}
		
	}
	public String nextMove(int pos) {
		gameState[pos] = currentPlayer; 
		
		if(currentPlayer == "O") {
		currentPlayer = "X";
		}
		else {
		currentPlayer = "O";
		}
		return currentPlayer;
	}
	

	public void updateString() {
		
		if(currentPlayer == "X") {
			x = "It's Player X turn:";
		}
		else {
			x = "It's Player O turn:";
		}
		view.updateText(x);
	}
	
	public String getString() {
		return x;
	}
	
	public void matchLogic() { //This metod is created by Yashika Jain and found on https://www.codespeedy.com/tic-tac-toe-game-using-java-swing-in-java/
		
		//X wins the game if true 
		if(gameState[0] == "X" && gameState[1] == "X" && gameState[2] == "X") {	
			xWins(1, 2, 3);
		}
		else if((gameState[3] == "X") && (gameState[4] == "X") && (gameState[5] == "X")) {
			xWins(3, 4, 5);
		}
		else if((gameState[6] == "X") && (gameState[7] == "X") && (gameState[8] == "X")) {
			xWins(6, 7, 8);
		}
		else if((gameState[0] == "X") && (gameState[3] == "X") && (gameState[6] == "X")) {
			xWins(0, 3, 6);
		}
		else if((gameState[1] == "X") && (gameState[4] == "X") && (gameState[7] == "X")) {
			xWins(1, 4, 7);
		}
		else if((gameState[2] == "X") && (gameState[5] == "X") && (gameState[8] == "X")) {
			xWins(2, 5, 8);
		}
		else if((gameState[0] == "X") && (gameState[4] == "X") && (gameState[8] == "X")) {
			xWins(0, 4, 8);
		}
		else if((gameState[2] == "X") && (gameState[4] == "X") && (gameState[6] == "X")) {
			xWins(2, 4, 6);
		}

		// O Wins the game if true

		else if( (gameState[0] == "O") && (gameState[1] == "O")	&& (gameState[2] == "O")) {
			oWins(0, 1, 2);
		}
		else if((gameState[3] == "O") && (gameState[4] == "O") && (gameState[5] == "O")) {
			oWins(3, 4, 5);
		}
		else if((gameState[6] == "O") && (gameState[7] == "O") && (gameState[8] == "O")) {
			oWins(6, 7, 8);
		}
		else if((gameState[0] == "O") && (gameState[3] == "O") && (gameState[6] == "O")) {
			oWins(0, 3, 6);
		}
		else if((gameState[1] == "O") && (gameState[4] == "O") && (gameState[7] == "O")) {	
			oWins(1, 4, 7);
		}
		else if((gameState[2] == "O") && (gameState[5] == "O") && (gameState[8] == "O")) {	
			oWins(2, 5, 8);
		}
		else if((gameState[0] == "O") && (gameState[4] == "O") && (gameState[8] == "O")) {		
			oWins(0, 4, 8);
		}
		else if((gameState[2] == "O") && (gameState[4] == "O") && (gameState[6] == "O")) {
			oWins(2, 4, 6);
		}

		// CHECK IF GAME IS TIE:
		else if (getFlag() == 9){
			gameTie();
		}
	}
	
	public void xWins(int a, int b, int c) {
		String win = "X Won the Game\nDo you want to play again?";
		view.endGame(a, b, c);
		scoreX++;
		view.GameOverGui(win);
		restartGame();
	}
		
	public void oWins(int a, int x, int c) {
		String win = "O Won the Game\nDo you want to play again?";
		view.endGame(a, x, c);
		scoreO++;
		view.GameOverGui(win);
		restartGame();
	}
	
	public void gameTie() {
		String win = "Game is a tie\nDo you want to play again?";
		view.GameOverGui(win);
		restartGame();
	}
	
	public void setFlag() {
		flag++;
	}
	
	public int getFlag() {
		return flag;
	}
	
	public int getXscore() {
		return scoreX;
	}
	
	public void setXscore() {
		scoreX++;
	}
	
	public int getOscore() {
		return scoreO;
	}
	
	public void setOscore() {
		scoreO++;
	}
	
	public void restartGame() {
		flag = 0;
		determinePlayer();
		for(int i = 0; i < 9; i++) {
			gameState[i] = "0";
		}
	}
}
