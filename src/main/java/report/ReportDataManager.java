package main.java.report;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import main.java.driver.Driver;
import main.java.driver.Trip;

public class ReportDataManager {

	private List<Driver> drivers = new ArrayList<Driver>();
	private DriverListStatCalculator statCalc = new DriverListStatCalculator();

	public List<Driver> getDriverListFromFileLines(List<String> fileLineList) {

		for (String s : fileLineList) {
			String[] line = s.split("\\s");
			if (line[0].equals("Driver")) {
				Driver driver = new Driver();
				driver.setDriverName(line[1]);
				drivers.add(driver);
			} else {
				Trip trip = mapLineToTrip(line);
				if (isTripValid(trip)) {
					addTripToDriverInList(drivers, trip);
				}
			}
		}
		statCalc.calculateDriverStats(drivers);

		return drivers;
	}
	
	private boolean isTripValid(Trip trip) {
		if (trip.mph() <=5 ||trip.mph() >=100){
		return false;
		}
		else return true;
	}

	private Trip mapLineToTrip(String[] line) {
		Driver driver = new Driver();
		driver.setDriverName(line[1]);

		return new Trip(driver, LocalTime.parse(line[2]), LocalTime.parse(line[3]), Double.parseDouble(line[4]));
	}

	private void addTripToDriverInList(List<Driver> drivers, Trip trip) {
		Driver driver = drivers.get(drivers.indexOf(trip.getDriver()));
		driver.addTrip(trip);
	}

}