package se.kau.isgc08.lab4.model;

import java.awt.Color;
import se.kau.isgc08.lab4.view.*;

public class ShapeFacade {
	
	
	private DrawingContainer drawingContainer;
	private DrawingUtil drawingUtil;
	private DrawingPanel drawingPanel;
	
	public ShapeFacade() {
		drawingContainer = new DrawingContainer();
		drawingUtil = new  DrawingUtil();
		drawingPanel = new DrawingPanel(null);

	}
	
	public void addCircle(int x1, int y1, int w, int h, int width, Color lineColor, Color area) {
		drawingContainer.add(new Circle(drawingUtil, x1, y1, w, h, width, lineColor, area));
	}
	
	public void addLine(int x1, int y1, int x2, int y2, int width, Color color) {
		drawingContainer.add(new Line(drawingUtil, x1, y1, x2, y2, width, color));
		System.out.print(drawingContainer.size());
		
		
	}
	
	public void addRect(int x1, int y1, int w, int h, int width, Color lineColor, Color area) {
		drawingContainer.add(new Rect(drawingUtil, x1, y1, w, h, width, lineColor, area));
	}
	
	public DrawingContainer getComposite() {
		return drawingContainer;
		
	}
	
	public void saveComposite() {
		drawingContainer.saveContainer();
	}
	
	public void loadComposite() {
		drawingContainer.loadContainer();
		setDc();
	}
	
	public void addToPanel() {
		drawingPanel.setDc(drawingContainer);
	}
	
	public DrawingPanel getPanel() {
		
		return drawingPanel;
	}
	
	public void setDc() {
		
		drawingPanel.setDc(drawingContainer);
	}
}
