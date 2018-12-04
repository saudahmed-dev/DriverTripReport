package main.java.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportReader {

	private List<String> fileLineList;

	public List<String> getLinesFromFile(String filePath) throws FileNotFoundException  {
		
		fileLineList = new ArrayList<String>();
		File inputFile = new File(filePath);
		
		Scanner in = new Scanner(inputFile); 
			while (in.hasNextLine()) {
				fileLineList.add(in.nextLine());
			}
		in.close();
		return fileLineList;
	}

}
