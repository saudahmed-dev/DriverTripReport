package main.java;

import java.io.FileNotFoundException;
import java.util.List;

import main.java.driver.Driver;
import main.java.report.DataManager;
import main.java.report.ReportPrinter;
import main.java.report.InputReader;

public class DriverTripReport {

	public static void main(String[] args) {
		final InputReader reportReader = new InputReader();
		final DataManager reportDataManager = new DataManager();
		final ReportPrinter reportPrinter = new ReportPrinter();

		try {
			if (args[0] != null) {

				String filePath = args[0];

				try {
					List<String> fileLines = reportReader.getLinesFromFile(filePath);
					List<Driver> drivers = reportDataManager.getDriverListFromFileLines(fileLines);
					reportPrinter.printDriverReport(drivers);
				} catch (FileNotFoundException e) {
					System.out.println("File was not found. Please enter a valid file.");
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Input was invalid. Please re-format your file with this format only\n"
									 + "Driver Saud\n" 
									 + "Driver Dan\n" 
									 + "Driver Chris\n" 
									 + "Trip Saud 12:35 12:59 12.2\n"
									 + "Trip Dan 17:24 18:24 40.1");

				}
			}
		} catch (ArrayIndexOutOfBoundsException f) {
			System.out.println("Please enter a file after the main class to get the report on");
		}
	}

}
