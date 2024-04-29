package labb1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class UserInterface extends JFrame implements ActionListener {
	
	private JTextArea textArea;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem newFile, openFile, saveAs, saveFile, exit;
	private Controller controller;
	private JFileChooser fileChooser;
	
	public UserInterface(Controller controller) {
		this.controller = controller;
		new JFrame();
		setLayout(new BorderLayout(10,10));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800,600));
		
		textArea = new JTextArea("");
		
		fileChooser = new JFileChooser();
		
		menu = new JMenu("Meny");
		menuBar = new JMenuBar();
		
		newFile = new JMenuItem("Ny fil");
		openFile = new JMenuItem("Öppna fil");
		saveAs = new JMenuItem("Spara som");
		saveFile = new JMenuItem("Spara fil");
		exit = new JMenuItem("Avsluta");
		
		newFile.addActionListener(this);
		openFile.addActionListener(this);
		saveAs.addActionListener(this);
		saveFile.addActionListener(this);
		exit.addActionListener(this);
		
		add(textArea);
		
		menu.add(newFile);
		menu.add(openFile);
		menu.add(saveAs);
		menu.add(saveFile);
		menu.add(exit);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		revalidate();
	}
	
	public void setView(String text) { textArea.setText(text); }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == newFile) {
			
			textArea.setText("");
			controller.handleEvent("newFile", null, null);
			
		} else if(e.getSource() == openFile) {
			
			
			fileChooser.showOpenDialog(null);
			controller.handleEvent("openFile", null, fileChooser.getSelectedFile().getAbsolutePath());
			
		} else if(e.getSource() == saveAs) {
			
			fileChooser.showSaveDialog(null);
			controller.handleEvent("saveAs", null, fileChooser.getSelectedFile().getAbsolutePath());
			
		} else if(e.getSource() == saveFile) {
			
			if(controller.fileExists()) {
				controller.handleEvent("saveFile", textArea.getText(), null);
			} else {
				fileChooser.showSaveDialog(null);
				controller.handleEvent("saveFile", textArea.getText(), fileChooser.getSelectedFile().getAbsolutePath());
			}
			
		} else if(e.getSource() == exit) {
			System.exit(0);
		}
	}
}
