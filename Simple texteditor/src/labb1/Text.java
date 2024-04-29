package labb1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Text {
	
	private String content; 
	private String fileName;
	private String filePath = "";
	
	public Text() {
		content = new String();
	}
	
	public String getContent() { return content; }
	
	public void setContent(String content) { this.content = content; }
	
	public String getFileName() { return fileName; }
	
	public void setFileName(String fileName) { this.fileName = fileName; }

	public String getFilePath() { return filePath; }
	
	public void setFilePath(String filePath) { this.filePath = filePath; }
	
	public void newFile() {
		setContent("");
		setFileName("");
		setFilePath("");
	}
	
	public String openFile() {
		
		try {
			
			List<String> lines = Files.readAllLines(Paths.get(getFilePath()));
			
			for(String line : lines) {
				content += line;
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return content;
	}
	
	public void saveAs(String content, String filePath) {
		
		try {
			Files.write(Paths.get(filePath), Arrays.asList(content));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveFile(String content) {
		this.content = content;
		
		try {
			Files.write(Paths.get(getFilePath()), Arrays.asList(content));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getAll() {
		System.out.println("Content: " + getContent());
		System.out.println("Filename: " + getFileName());
		System.out.println("Filepath: " + getFilePath());
		System.out.println("--------------------------");
	}
}
