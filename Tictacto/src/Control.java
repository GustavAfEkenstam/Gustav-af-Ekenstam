import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements ActionListener {

	//Koden skriven av Andreas Abramo(980917) och Gustav Af ekenstam(970505), 
	
	private View view; 
	private Model model;
	
	public Control(Model model) {
		this.model = model;
	}
	
	public void setView(View view) {
		this.view = view;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
			
		model.setBoard(Integer.parseInt(e.getActionCommand()));
	
	}
}


