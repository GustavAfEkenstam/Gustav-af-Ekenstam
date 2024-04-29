package se.kau.isgc08.lab4.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;



import controller.Controller;

public class UIFrame implements ActionListener, MouseListener {
	
	
	private JFrame frame;
	private JPanel menuPanel, paintPanel, thicknessPanel;
	private JButton rectangleButton, circleButton, lineButton,colorButton, thickBtnPlus, thickBtnMinus, thickLabel;
	private int x1, x2, y1, y2;
	private String activeFigure = "rectangle", activeColor;
	private String [] colors = {"BLACK", "BLUE", "CYAN", "DARK_GRAY", "GRAY", "GREEN", "LIGHT_GRAY", "MAGENTA", "ORANGE", "PINK", "RED", "WHITE", "YELLOW"};
	private Color [] colorsArray = new Color[13];
	private JComboBox colorList;
	private int colorIndex;
	
	
	private Controller controller;
	
	public UIFrame() {
		
		runUI();
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void runUI() {
		
		colorsArray[0] = Color.BLACK;
		colorsArray[1] = Color.BLUE;
		colorsArray[2] = Color.CYAN;
		colorsArray[3] = Color.DARK_GRAY;
		colorsArray[4] = Color.GRAY;
		colorsArray[5] = Color.GREEN;
		colorsArray[6] = Color.LIGHT_GRAY;
		colorsArray[7] = Color.MAGENTA;
		colorsArray[8] = Color.ORANGE;
		colorsArray[9] = Color.PINK;
		colorsArray[10] = Color.RED;
		colorsArray[11] = Color.WHITE;
		colorsArray[12] = Color.YELLOW;
		
		frame = new JFrame();
		frame.setSize(800,600);
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		menuPanel = new JPanel();
		paintPanel = new JPanel();
		thicknessPanel = new JPanel(new GridLayout(1,3));
		
		paintPanel.setBackground(Color.WHITE);
		
		menuPanel.setPreferredSize(new Dimension(800,60));
		
		paintPanel.setLayout(new FlowLayout());
		
		paintPanel.setPreferredSize(new Dimension(800,540));
		
		paintPanel.setOpaque(true);
		
		menuPanel.setLayout(new GridLayout(1,5));
		
		rectangleButton = new JButton("Rektangel");
		circleButton = new JButton("Cirkel");
		lineButton = new JButton("Linje");
		thickBtnMinus = new JButton("-");
		thickBtnPlus = new JButton("+");
		colorButton = new JButton("Färg");
		thickLabel = new JButton("1");
		colorList = new JComboBox(colors);
		
		colorList.addActionListener(this);
		rectangleButton.addActionListener(this);
		circleButton.addActionListener(this);
		lineButton.addActionListener(this);
		thickBtnMinus.addActionListener(this);
		colorButton.addActionListener(this);
		thickBtnPlus.addActionListener(this);
		
		thicknessPanel.add(thickBtnMinus);
		thicknessPanel.add(thickLabel);
		thicknessPanel.add(thickBtnPlus);
		
		menuPanel.add(lineButton);
		menuPanel.add(rectangleButton);
		menuPanel.add(circleButton);
		menuPanel.add(thicknessPanel);
		menuPanel.add(colorList);
		
		frame.add(paintPanel, BorderLayout.CENTER);
		frame.add(menuPanel, BorderLayout.NORTH);
		
		paintPanel.addMouseListener(this);
		
		frame.pack();
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		
	}
	
	
	
	public void updateView(DrawingPanel panel) {
		
		paintPanel.add(panel);
		paintPanel.revalidate();
		paintPanel.repaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == rectangleButton) {
			activeFigure = "rectangle";
			System.out.println("Rekt");
		} else if(e.getSource() == circleButton) {
			activeFigure = "circle";
			System.out.println("Circkel");
		} else if(e.getSource() == lineButton) {
			activeFigure = "line";
			System.out.println("Linje");
		} else if (e.getSource() == thickBtnMinus) {
		
			int tmp = Integer.parseInt(thickLabel.getText());
			
			if(!(tmp == 1)) {
				tmp -= 1;
				thickLabel.setText(Integer.toString(tmp));
			}
			
	
		} else if (e.getSource() == thickBtnPlus) {
			int tmp = Integer.parseInt(thickLabel.getText());
			
			tmp += 1;
			thickLabel.setText(Integer.toString(tmp));
		} else if(e.getSource() == colorList) {
			colorIndex = colorList.getSelectedIndex();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {}


	@Override
	public void mousePressed(MouseEvent e) {
		
	    x1 = e.getX();
        y1 = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	    x2 = e.getX();
        y2 = e.getY();

        controller.addShape(activeFigure, x1, y1, x2, y2, Integer.parseInt(thickLabel.getText()), colorsArray[colorIndex]);
	}


	@Override
	public void mouseEntered(MouseEvent e) {}


	@Override
	public void mouseExited(MouseEvent e) {}
}
