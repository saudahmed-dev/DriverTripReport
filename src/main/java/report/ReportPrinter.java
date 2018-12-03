package main.java.report;

import java.util.Comparator;
import java.util.List;

import main.java.driver.Driver;

public class ReportPrinter {

	public void printDriverReport(List<Driver> drivers) {
		
		drivers.sort(Comparator.comparing(Driver::getMilesDriven).reversed());
		
		for(Driver driver : drivers) {
			
			String name = driver.getDriverName();
			Long milesDriven = Math.round(driver.getMilesDriven());
			String avgMPH = driver.getAvgMPH() > 0 ? "@ " + driver.getAvgMPH() + " mph" : "";
			
			System.out.println( name + " " + milesDriven + " miles " + avgMPH);
		}
		
	}
}
