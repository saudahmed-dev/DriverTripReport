package main.java.report;

import java.util.List;

import main.java.driver.Driver;
import main.java.driver.Trip;

public class DriverListStatCalculator {
	

	public void calculateDriverStats(List<Driver> drivers) {

		for (Driver driver : drivers) {
			double totalMilesDriven = 0;
			double totalMinsDriven = 0;
			for (Trip trip : driver.getTrips()) {
				totalMilesDriven += trip.getMilesDriven();
				totalMinsDriven += trip.getMinsDriven();
			}
			driver.setMilesDriven(totalMilesDriven);
			driver.setAvgMPH((int)Math.round(totalMilesDriven / (totalMinsDriven / 60)));
		}
	}
	
}