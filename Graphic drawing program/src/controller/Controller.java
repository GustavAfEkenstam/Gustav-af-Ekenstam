package controller;


import java.awt.Color;

import se.kau.isgc08.lab4.model.*;
import se.kau.isgc08.lab4.view.*;


public class Controller {
	
	private ShapeFacade model;
	private UIFrame view;
	
	public Controller(ShapeFacade model, UIFrame view) {
		
		this.model = model;
		this.view = view;
		
	}
	
	public void addShape(String type, int x1, int y1, int x2, int y2, int thickness, Color color) {
		
		if(type == "line") {
			
			model.addLine(x1, y1, x2, y2, thickness, color);
	        System.out.println("lin ");

			
		} else if(type == "rectangle") {
			
			int x = Math.min(x1, x2); 
			int y = Math.min(y1, y2);
			int width = Math.abs(x2 - x1);
			int height = Math.abs(y2 - y1);
			
			model.addRect(x, y, width, height, thickness, color, null);
			System.out.println("rec ");
			
		} else if(type == "circle") {
			
			int x = Math.min(x1, x2); 
			int y = Math.min(y1, y2);
			int width = Math.abs(x2 - x1);
			int height = Math.abs(y2 - y1);
			
			model.addCircle(x, y, width, height, thickness, color, null);
			System.out.println("circ ");
		}
		
		model.addToPanel();	
		view.updateView(model.getPanel());
		save();
	}
	
	public void save() {
		model.saveComposite();
	}
	
	public void load() {
		model.loadComposite();
		
	}
	
	public DrawingPanel getPanel() {
		
		return model.getPanel();
	}
}