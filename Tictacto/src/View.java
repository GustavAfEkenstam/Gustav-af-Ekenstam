
import java.awt.*;
import javax.swing.*;


public class View extends JFrame {
	
	//Koden skriven av Andreas Abramo(980917) och Gustav Af ekenstam(970505), 

	//Game Frame:
	JButton[] b = new JButton[9];
	private JPanel panelButtons, panelBottom, panelTop;
	private JFrame frame;
	private JLabel topRight, topCenter, topLeft, imageBottom;
	
	private String first;
	
	//Klasser
	private Control control;
	public Model model;
	
	public View(Control control) {
		super("TicTacToe");
		this.control = control;
		
		//Panel på Toppen + JLabels.
		topRight = new JLabel();
		topCenter = new JLabel();
		topLeft = new JLabel();
		topLeft.setFont(new Font("Gotham", Font.BOLD, 20));
		topLeft.setHorizontalAlignment(JLabel.LEFT);
		topCenter.setFont(new Font("Gotham", Font.BOLD, 20));
		topCenter.setText(getPlayerString(first));
		topCenter.setHorizontalAlignment(JLabel.CENTER);
		topRight.setFont(new Font("Gotham", Font.BOLD, 20));
		topRight.setHorizontalAlignment(JLabel.RIGHT);
		topLeft.setText("Player X Score: 0");
		topLeft.setForeground(Color.blue);
		topRight.setText("Player O Score: 0");
		topRight.setForeground(Color.red);
		panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.setPreferredSize(new Dimension(800, 40));
		panelTop.add(topLeft, BorderLayout.WEST);
		panelTop.add(topCenter, BorderLayout.CENTER);
		panelTop.add(topRight, BorderLayout.EAST);  
		
		//Panelen för knapparna plus skapandet av knapparna.
		panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(3,3));
		for(int i = 0; i < 9; i++) {
			b[i] = new JButton();
			b[i].setFont(new Font("Gotham", Font.BOLD, 200));
			b[i].setFocusable(false);
			b[i].setActionCommand(String.valueOf(i));
			b[i].addActionListener(control);
			b[i].setBackground(Color.white);
			panelButtons.add(b[i]);
		}	
		
		//Bilden till spelet + panel och label på botten.
		ImageIcon icon = new ImageIcon("/Users/andreasabramo/Desktop/eclipse-workspace/TicTacToe/Bild/tttlogo1.png");
		imageBottom = new JLabel("",icon,JLabel.CENTER);
		panelBottom = new JPanel();
		panelBottom.add(imageBottom);

		//Huvudframen av spelet. 
		frame = new JFrame();
		frame.setSize(800, 800);
		frame.setTitle("Tic Tac Toe");
		frame.setLayout(new BorderLayout());
		frame.add(panelTop, BorderLayout.NORTH);
		frame.add(panelButtons,BorderLayout.CENTER);
		frame.add(panelBottom,BorderLayout.SOUTH);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		control.setView(this);
	}
	
	public void GameOverGui(String win) {
	//JOption pane för att starta om spelet eller spela igen	
		JFrame popUp = new JFrame();
		Object[] option={"Exit","Play Again"};
        int n=JOptionPane.showOptionDialog(popUp,win,"Game Over",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
        if(n==0){
        	popUp.dispose();
            System.exit(0);
        }
        else{
            popUp.dispose();
            restartGame();
        }
		
	}
	
	public void updateB(String[] board) {
		for(int i = 0; i < 9; i++) {	
			if(board[i] == "0")
				continue;
			switch(board[i]) {
			case "X":
				b[i].setText(board[i]);
				b[i].setForeground(Color.blue);
				break;
			case "O":
				b[i].setText(board[i]);
				b[i].setForeground(Color.red);
				break;
			}	
		}
	}
	
	public String getPlayerString(String x) {
		return x;
	}
	
	public void setText(String x) {
		first = x;
		if(first == "It's Player X turn:")
			topCenter.setForeground(Color.blue);
		else {
			topCenter.setForeground(Color.red);
		}
		topCenter.setText(first);
	}
	
	public void updateText(String x) {
		if(x == "It's Player X turn:")
			topCenter.setText("<html>It´s <font color='blue'>X's</font> turn!</html>");
		else {
			topCenter.setText("<html>It´s <font color='red'>O's</font> turn!</html>");
		}
		//topCenter.setText(x);
	}
	public void updateScore() {
		
		topRight.setText("Player O Score: " + model.getOscore());
		
		topLeft.setText("Player X Score: " + model.getXscore());
	}
	
	public void endGame(int a, int x, int c) {
		for(int i = 0; i < 9; i++) {
			b[i].setEnabled(false);
			b[a].setBackground(Color.green);
			b[x].setBackground(Color.green);
			b[c].setBackground(Color.green);
		}
		updateScore();
	}
	public void restartGame() {
		for(int i = 0; i < 9; i++) {
			b[i].setText("");
			b[i].setEnabled(true);
			b[i].setBackground(Color.white);
		}
		updateScore();
	}
}