package borda;

import java.awt.Color;

import javax.swing.JFrame;
//Koden är skriven av Andreas Abramo (980917-0310) & Gustav af Ekenstam (970505-5995)

public class FrontendWindow  extends JFrame{
	public FrontendWindow() {

		add(new Frontend());
		setSize(800, 600);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Bokningssystem");
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);	
	}
}
