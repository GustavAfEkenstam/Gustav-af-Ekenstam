import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Highscore extends JFrame  {
	
	//Koden skriven av Andreas Abramo(980917-0310) & Gustav af Ekenstam(970505-5995)
	
	private JFrame f = new JFrame();  
	private JLabel titleHigh, titleLatest;
	private DefaultListModel<Score> highscore = new DefaultListModel<Score>();
	private DefaultListModel<Integer> latestRun = new DefaultListModel<Integer>();
	private DefaultListModel<Score> temp = new DefaultListModel<Score>();
	private JList<Score> highscoreList = new JList<>(highscore);
	private JList<Integer> latestRunList = new JList<>(latestRun);
	
	private int MAX_SCORES=10;
    private int MAX_LATESTRUNS=3;
    
	public Highscore() {
		
	}
	
	public void addHighscore(Score e) {
		if(MAX_SCORES > highscore.size()){
			e.setName(JOptionPane.showInputDialog(null ,"Skriv in dina initialer 3 tecken"));
			if(e.getName().length() < 3) {
				while(e.getName().length() < 3) {
					e.setName(JOptionPane.showInputDialog("Du skrev för få tecken, vänligen försök igen"));
				}
			}
			e.setName(e.getName().substring(0,3));
			highscore.addElement(new Score(e.getName(), e.getScore()));
		}
		else if (MAX_SCORES <= highscore.size() && e.getScore() > highscore.get(MAX_SCORES - 1).getScore()){
			highscore.remove(highscore.size() -1);
			e.setName(JOptionPane.showInputDialog("Skriv in dina initialer 3 tecken"));
			if(e.getName().length() < 3) {
				
				while(e.getName().length() < 3) {
					e.setName(JOptionPane.showInputDialog("Du skrev för få tecken, vänligen försök igen"));
				}
			}
			e.setName(e.getName().substring(0,3));
			highscore.addElement(new Score(e.getName(), e.getScore()));
		}
		if(MAX_LATESTRUNS > latestRun.size()) {
			latestRun.add(0, e.getScore());
			System.out.println("Hej" + latestRun.size());
	
		}
		else if(MAX_LATESTRUNS <= latestRun.size()) {
			latestRun.remove(latestRun.size()-1);
			latestRun.add(0, e.getScore());
			System.out.println("He12j");
		}
		highscoreListSort(highscore, highscore.size());
	}
	
	public void highscoreListSort(DefaultListModel<Score> highScores, int n){
        for (int i = 0; i < n; i++){
            for (int j = 1; j < (n-i) ; j++){
                if (highScores.get(j- 1).getScore() < highScores.get(j).getScore()){
                	temp.addElement(new Score (highscore.get(j-1).getName(), highscore.get(j - 1).getScore()));
                	highScores.set(j - 1, highScores.get(j));
                    highScores.set(j, temp.elementAt(0));
                    temp.clear();
                }
            }
        }	 
    }
	
	public void highscoreGUI() {
	
		JPanel jp = new JPanel();
		
		titleHigh = new JLabel(); 
		titleLatest = new JLabel(); 
		
		highscoreList.setBounds(0, 30, 200, 600);  
		latestRunList.setBounds(200, 30, 200, 600);
		
		jp.setLayout(new BorderLayout());
		jp.setBounds(0, 0, 400, 30);
		
		titleHigh.setFont(new Font("Open Sans", Font.BOLD, 20));
		titleLatest.setFont(new Font("Open Sans", Font.BOLD, 20));
		titleHigh.setText("HIGHSCORE");
		titleLatest.setText("LATEST RUN");
		titleHigh.setBounds(0, 0, 200, 30);
		titleLatest.setBounds(200, 0, 200, 30);
		
		jp.add(titleLatest);
		jp.add(titleHigh);
		
		f.setTitle("Highscore/Latest Run");
		f.add(jp);
		f.setLayout(null);
		f.add(highscoreList);
		f.add(latestRunList);
		f.setSize(400,600);  
		f.setLocationRelativeTo(null);
		f.setVisible(true);	 
	}
}
