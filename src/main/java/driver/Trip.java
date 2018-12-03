package main.java.driver;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Trip {

	private Driver driver;
	private LocalTime startTime;
	private LocalTime stopTime;
	private double milesDriven;

	public Trip(Driver driver, LocalTime startTime, LocalTime stopTime, double milesDriven) {
		this.driver = driver;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.milesDriven = milesDriven;
	} 

	public Driver getDriver() {
		return driver;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getStopTime() {
		return stopTime;
	}

	public double getMilesDriven() {
		return milesDriven;
	}
	
	public int getMinsDriven() {
		return (int) ChronoUnit.MINUTES.between(startTime, stopTime);
	}
	
	public double mph() {
		return milesDriven/((double)getMinsDriven() / 60);
	}

}
