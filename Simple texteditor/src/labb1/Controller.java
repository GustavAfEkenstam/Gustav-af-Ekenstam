package labb1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {
	
	private UserInterface view;
	private Text text;
	
	public Controller() {
		text = new Text();
		view = new UserInterface(this);
	}

	public void handleEvent(String command, String content, String filePath){
		
		if(command == "newFile") {
			
			text.newFile();
			text.getAll();
			
		} else if(command == "openFile") {
			
			text.setFilePath(filePath);
			Path path = Paths.get(filePath);
			text.setFileName(path.getFileName().toString());
			view.setView(text.openFile());
			text.getAll();
		
		} else if(command == "saveAs") {
			
			text.saveAs(content, filePath);
			text.getAll();
			
		} else if(command == "saveFile") {
			
			if(filePath == null) {
				text.saveFile(content);
			} else {
				Path path = Paths.get(filePath);
				text.setFileName(path.getFileName().toString());
				text.setFilePath(filePath);
				text.setContent(content);
				text.saveFile(content);
			}
			text.getAll();
		}
	}
	
	public boolean fileExists() {
		
		if(text.getFilePath() == "") 
			return false;
		 else 
			return true;
	}
	
	public static void main(String[] args) { new Controller(); }
}
