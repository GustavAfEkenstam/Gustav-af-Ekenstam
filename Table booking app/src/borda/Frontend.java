 package borda;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
// Koden är skriven av Andreas Abramo (980917-0310) & Gustav af Ekenstam (970505-5995)
public class Frontend extends JComponent implements MouseListener, ActionListener {
	private ArrayList<Backend> smallSquares;
	private ArrayList<Backend> bigSquares;
	private ArrayList<Backend> circels;
	private Backend bigTabel; 
	private JButton bookButton;
	private JButton cancelButton;
	private JButton addGuestButton;
	private JButton removeGuestButton;
	private JPanel menuPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel buttonSouthPanel;
	private JLabel titleQueList;
	private DefaultListModel<String> nameModel;
	private JList<String> nameList;	
	private JScrollPane scrollPane;
	private int index;
	
	public Frontend() {

		// Skapr allt nödvändiga delar till meny-panelen
		bookButton = new JButton("Boka");
		cancelButton = new JButton("Avboka");
		addGuestButton = new JButton("+");
		removeGuestButton = new JButton("-");
		menuPanel = new JPanel(new GridLayout(2,1));
		northPanel = new JPanel(new GridLayout(2,1));
		southPanel = new JPanel(new BorderLayout());
		buttonSouthPanel = new JPanel(new FlowLayout());
		nameModel = new DefaultListModel<String>();
		titleQueList = new JLabel("Kö-lista för drop-in gäster", SwingConstants.CENTER);
		nameList = new JList<>(nameModel);
		scrollPane = new JScrollPane(nameList);
		// Lägger till mouselistener på JFrame
		addMouseListener(this);

		// Skapar små runda bord
		circels = new ArrayList<Backend>();
		circels.add(new Backend(20,20));	
		circels.add(new Backend(20,120));	
		circels.add(new Backend(100,210));	
		circels.add(new Backend(20,300));	
		circels.add(new Backend(100,390));
		circels.add(new Backend(20,480));

		// Skapar små fyrkantiga bord
		smallSquares = new ArrayList<Backend>();
		smallSquares.add(new Backend(560,160));	
		smallSquares.add(new Backend(560,239));	
		smallSquares.add(new Backend(560,324));	
		smallSquares.add(new Backend(250,339));
		smallSquares.add(new Backend(300,339));

		// Skapar stora fyrkantiga bord
		bigSquares = new ArrayList<Backend>();
		bigSquares.add(new Backend(480,500));	
		bigSquares.add(new Backend(400,500));
		bigSquares.add(new Backend(320,500));
		bigSquares.add(new Backend(240,500));

		// Skapar stora runda bordet
		bigTabel = new Backend(400, 300);

		// Lägger till knappar på övre panelen
		northPanel.add(bookButton);
		northPanel.add(cancelButton);
		bookButton.addActionListener(this);
		cancelButton.addActionListener(this);
		northPanel.setBackground(Color.GRAY);

		//Lägger till kö-lista titel
		southPanel.add(titleQueList, BorderLayout.NORTH);

		//Lägger till scroll till kö-listan
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		//Lägger till knappar lägg till och ta bort gäst på panel syd
		buttonSouthPanel.add(addGuestButton);
		buttonSouthPanel.add(removeGuestButton);
		removeGuestButton.addActionListener(this);
		addGuestButton.addActionListener(this);

		//Lägger till kö-listan och knapp-panelen 
		southPanel.add(scrollPane, BorderLayout.CENTER);
		southPanel.add(buttonSouthPanel, BorderLayout.SOUTH);

		//Lägger till panel nord och syd på meny-panelen
		menuPanel.add(northPanel);
		menuPanel.add(southPanel);

		//Sätter meny-panelen på plats
		menuPanel.setBounds(607, 0, 193, 580);

		//Lägger till menyPanel på JFramen
		add(menuPanel);

		// Lägger till mouseListener på JList
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				mouseEvent.getSource();
				nameList = (JList) mouseEvent.getSource();
				index =	nameList.locationToIndex(mouseEvent.getPoint());
			}
		};
		nameList.addMouseListener(mouseListener);


	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D)g;

		//Väggar
		graphics.drawRect(199, 0, 6, 400);
		graphics.drawRect(199, 500, 6, 100);
		graphics.drawRect(250, 270, 250, 6);
		graphics.drawRect(205, 104, 230, 6);
		graphics.drawRect(199, 500, 6, 100);
		graphics.drawRect(500, 104, 100, 6);
		graphics.drawRect(600, 0, 6, 600);
		graphics.drawRect(494, 104, 6, 166);

		//Ändrar färgen om bordet är markerat, bokat eller avbokat för små runda bord
		for(Backend circel : circels) {
			if(circel.isActivated() && circel.isBooked()) {
				graphics.setColor(new Color(255, 231, 76));	
			}
			else if(circel.isBooked()) {
				graphics.setColor(new Color(255, 89, 100));
			}
			else if(circel.isActivated()) {
				graphics.setColor(new Color(255, 231, 76));	
			}
			else {
				graphics.setColor(new Color(44 ,148, 191));
			}
			graphics.fillOval(circel.getX(), circel.getY(), circel.WIDTH ,circel.HEIGHT);
			graphics.setColor(new Color(0, 0, 0));
			graphics.drawRect(circel.getX() + 25, circel.getY() - 7, 25, 1);
			graphics.drawRect(circel.getX() + 25, circel.getY() + 82, 25, 1);

			graphics.drawRect(circel.getX() - 7, circel.getY() + 25, 1, 25);
			graphics.drawRect(circel.getX() + 82, circel.getY() + 25, 1, 25);
		}

		//Ändrar färgen om bordet är markerat, bokat eller avbokat för små fyrkantiga bord
		for(Backend smallSquare : smallSquares) {
			if(smallSquare.isActivated() && smallSquare.isBooked()) {
				graphics.setColor(new Color(255, 231, 76));
			}
			else if(smallSquare.isBooked()) {
				graphics.setColor(new Color(255, 89, 100));
			}
			else if(smallSquare.isActivated()) {
				graphics.setColor(new Color(255, 231, 76));
			}
			else {
				graphics.setColor(new Color(44 ,148, 191));
			}
			graphics.fillRect(smallSquare.getX(), smallSquare.getY(), 40, smallSquare.HEIGHT /3 * 2);
			graphics.setColor(new Color(0, 0, 0));
			graphics.drawRect(smallSquare.getX() + 7 , smallSquare.getY() - 10, 25, 1);
			graphics.drawRect(smallSquare.getX() + 7, smallSquare.getY() + smallSquare.HEIGHT /3 * 2 + 10, 25, 1);
		}

		//Ändrar färgen om bordet är markerat, bokat eller avbokat för stora fyrkantiga bord
		for(Backend bigSquare : bigSquares) {
			if(bigSquare.isActivated() && bigSquare.isBooked()) {
				graphics.setColor(new Color(255, 231, 76));
			}
			else if(bigSquare.isBooked()) {
				graphics.setColor(new Color(255, 89, 100));
			}
			else if(bigSquare.isActivated()) {
				graphics.setColor(new Color(255, 231, 76));	
			}
			else {
				graphics.setColor(new Color(44 ,148, 191));
			}

			graphics.fillRect(bigSquare.getX(), bigSquare.getY(), 50, bigSquare.HEIGHT);
			graphics.setColor(new Color(0, 0, 0));
			graphics.drawRect(bigSquare.getX() - 10, bigSquare.getY(), 1, 25);
			graphics.drawRect(bigSquare.getX() + 55, bigSquare.getY(), 1, 25);
			graphics.drawRect(bigSquare.getX() - 10, bigSquare.getY() + 50, 1, 25);
			graphics.drawRect(bigSquare.getX() + 55, bigSquare.getY() + 50, 1, 25);
		}

		//Ändrar färgen om bordet är markerat, bokat eller avbokat för det stora runda bordet
		if(bigTabel.isActivated() && bigTabel.isBooked()) {
			graphics.setColor(new Color(255, 231, 76));	
		}
		else if(bigTabel.isBooked()) {
			graphics.setColor(new Color(255, 89, 100));
		}
		else if(bigTabel.isActivated()) {
			graphics.setColor(new Color(255, 231, 76));		
		}
		else {
			graphics.setColor(new Color(44 ,148, 191));

		}				
		graphics.fillOval(bigTabel.getX(), bigTabel.getY(), bigTabel.WIDTH + 10, bigTabel.HEIGHT + 10);

		// Ritar stolarna till det stora runda bordet
		graphics.setStroke(new BasicStroke(2));
		graphics.setColor(new Color(0, 0, 0));
		graphics.drawRect(bigTabel.getX() - 10, bigTabel.getY() + 29, 1, 25);
		graphics.drawRect(bigTabel.getX() + 95, bigTabel.getY() + 29, 1, 25);
		graphics.drawRect(bigTabel.getX() + 30, bigTabel.getY() - 10, 25, 1);
		graphics.drawRect(bigTabel.getX() + 30, bigTabel.getY() + 95, 25, 1);
		graphics.setStroke(new BasicStroke(2));
		graphics.drawLine(396, 370, 412, 386);
		graphics.drawLine(396, 310, 412, 296);
		graphics.drawLine(494, 370, 478, 386);
		graphics.drawLine(494, 310, 478, 296);

		//Bordsnummer
		graphics.setColor(new Color(0,0,0));
		graphics.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		graphics.drawString("1", 50, 65);
		graphics.drawString("2", 50, 165);
		graphics.drawString("3", 130, 255);
		graphics.drawString("4", 50, 345);
		graphics.drawString("5", 130, 435);
		graphics.drawString("6", 50, 525);
		graphics.drawString("7", 262, 374);
		graphics.drawString("8", 312, 374);
		graphics.drawString("9", 257, 547);
		graphics.drawString("10", 328, 547);
		graphics.drawString("11", 410, 547);
		graphics.drawString("12", 490, 547);
		graphics.drawString("13", 564, 195);
		graphics.drawString("14", 564, 275);
		graphics.drawString("15", 564, 360);
		graphics.drawString("16", 425, 352);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Lägger till sällskap i kö för drop-ing gäster
		if(e.getSource() == addGuestButton) {
			
			try {
				String name = JOptionPane.showInputDialog("Namn på sällskap och antal personer");
					nameModel.addElement(new String(name));
			}catch (Exception w) {
				System.out.println("Du är en bög");
				
			}
		}	
		//Tar bort Gäster ur kön
		if(e.getSource() == removeGuestButton) {
			//String avboka = JOptionPane.showInputDialog("Skriv namnet på sällskapet som ska tas bort: ");
			if(nameModel.getSize() == 0) {
			}
			else {

				int input = JOptionPane.showConfirmDialog(null, "Är du säker på att du vill ta bort " + nameModel.getElementAt(index).toString());
				if(input == JOptionPane.YES_OPTION) {
					nameModel.removeElementAt(index);
				}
			}
		}	
		//Boka bord små runda
		for(Backend circel : circels) {
			if(e.getSource() == bookButton && circel.isActivated()) {
				circel.book();
				circel.toggleActivated();
				this.repaint();
			}	
		}
		// Avboka bord små runda
		for(Backend circel : circels) {
			if(e.getSource() == cancelButton && circel.isActivated()) {
				circel.cancelBooking();
				circel.toggleActivated();
				this.repaint();
			}	
		}
		//Boka bord små fyrkantiga
		for(Backend smallSquare : smallSquares) {
			if(e.getSource() == bookButton && smallSquare.isActivated()) {
				smallSquare.book();
				smallSquare.toggleActivated();
				this.repaint();
			}	
		}

		// Avboka bord små fyrkantiga
		for(Backend smallSquare : smallSquares) {
			if(e.getSource() == cancelButton && smallSquare.isActivated()) {
				smallSquare.cancelBooking();
				smallSquare.toggleActivated();
				this.repaint();
			}	
		}
		//Boka Stora fyrkantiga bord
		for(Backend bigSquare : bigSquares) {
			if(e.getSource() == bookButton && bigSquare.isActivated()) {
				bigSquare.book();
				bigSquare.toggleActivated();
				this.repaint();
			}
		}
		//Avboka stora fyrkantiga bord
		for(Backend bigSquare : bigSquares) {
			if(e.getSource() == cancelButton && bigSquare.isActivated()) {
				bigSquare.cancelBooking();
				bigSquare.toggleActivated();
				this.repaint();
			}
		}
		//Boka stora runda bordet 
		if(e.getSource() == bookButton && bigTabel.isActivated()) {
			bigTabel.book();
			bigTabel.toggleActivated();
			this.repaint();
		}
		//Avboka stora runda bordet
		if(e.getSource() == cancelButton && bigTabel.isBooked()) {
			bigTabel.cancelBooking();
			bigTabel.toggleActivated();
			this.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			for(Backend smallSquare : smallSquares) {
				if(e.getX() >= smallSquare.getX() && e.getX() <= smallSquare.getX() + smallSquare.WIDTH - 35)
					if(e.getY() >= smallSquare.getY() && e.getY() <= smallSquare.getY() + smallSquare.HEIGHT / 3 * 2){
						smallSquare.toggleActivated();	
					}
				this.repaint();
			}

			for(Backend bigSquare : bigSquares) {
				if(e.getX() >= bigSquare.getX() && e.getX() <= bigSquare.getX() + bigSquare.WIDTH)
					if(e.getY() >= bigSquare.getY() && e.getY() <= bigSquare.getY() + bigSquare.HEIGHT){
						bigSquare.toggleActivated();
					}
				this.repaint();
			}

			for(Backend circel : circels) {
				if(e.getX() >= circel.getX() && e.getX() <= circel.getX() + circel.WIDTH)
					if(e.getY() >= circel.getY() && e.getY() <= circel.getY() + circel.HEIGHT){
						circel.toggleActivated();
					}
				this.repaint();
			}

			if(e.getX() >= bigTabel.getX() && e.getX() <= bigTabel.getX() + bigTabel.WIDTH)
				if(e.getY() >= bigTabel.getY() && e.getY() <= bigTabel.getY() + bigTabel.HEIGHT){
					bigTabel.toggleActivated();
				}
			this.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
