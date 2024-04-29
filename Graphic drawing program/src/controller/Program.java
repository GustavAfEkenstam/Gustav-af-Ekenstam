package controller;

import se.kau.isgc08.lab4.model.ShapeFacade;
import se.kau.isgc08.lab4.view.UIFrame;

public class Program {

	private ShapeFacade model;
	private Controller controller;
	private UIFrame view;
	
	public Program() {
		
		model = new ShapeFacade();
		view = new UIFrame();
		controller = new Controller(model, view);

		view.setController(controller);
		controller.load();
		view.updateView(controller.getPanel());
	}
	
	public static void main(String[] args) {
	
		Program p = new Program();
	}
}
