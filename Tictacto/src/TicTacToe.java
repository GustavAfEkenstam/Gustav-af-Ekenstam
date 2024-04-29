
public class TicTacToe {

	//Koden skriven av Andreas Abramo(980917) och Gustav Af ekenstam(970505), 
	
	public static void main(String[] args) {
		Model model = new Model();
		Control controll = new Control(model);
		View view = new View(controll);
		model.view = view;
		view.model = model;
	}
}
