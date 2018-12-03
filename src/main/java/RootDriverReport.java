package main.java;

import java.io.FileNotFoundException;
import java.util.List;

import main.java.driver.Driver;
import main.java.report.ReportDataManager;
import main.java.report.ReportPrinter;
import main.java.report.ReportReader;

public class RootDriverReport {

	public static void main(String[] args){
		final ReportReader reportReader = new ReportReader();
		final ReportDataManager reportDataManager = new ReportDataManager();
		final ReportPrinter reportPrinter = new ReportPrinter();

		
		if (args[0] != null) {
			
			String filePath = args[0];
			
			try {
			List<String> fileLines = reportReader.getLinesFromFile(filePath);
			List<Driver> drivers = reportDataManager.getDriverListFromFileLines(fileLines);
			reportPrinter.printDriverReport(drivers);	
			}
			catch(FileNotFoundException e) {
				System.out.println("File was not found. Please enter a valid file.");
			}
		}
		else {
			System.out.println("Please enter a file to get the report on (like a .txt or .csv file)!");
		}
		
	}

}
