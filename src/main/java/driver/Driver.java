package main.java.driver;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	private String driverName;
	private double milesDriven;
	private int avgMPH;
	private List<Trip> trips = new ArrayList<>();
	
	

	public Driver(String driverName, double milesDriven, int avgMPH) {
		super();
		this.driverName = driverName;
		this.milesDriven = milesDriven;
		this.avgMPH = avgMPH;
	}

	public Driver() {
		
	}
	public int getAvgMPH() {
		return avgMPH;
	}

	public void setAvgMPH(int avgMPH) {
		this.avgMPH = avgMPH;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public double getMilesDriven() {
		return milesDriven;
	}

	public void setMilesDriven(double milesDriven) {
		this.milesDriven = milesDriven;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driverName == null) ? 0 : driverName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (driverName == null) {
			if (other.driverName != null)
				return false;
		} else if (!driverName.equals(other.driverName))
			return false;
		return true;
	}

	
}
