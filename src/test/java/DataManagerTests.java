package test.java;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.driver.Driver;
import main.java.driver.Trip;
import main.java.report.DataManager;
import main.java.report.InputReader;

public class DataManagerTests {
	
	InputReader reader;
	DataManager dataManager;
	List<String> fileLinesList1;
	List<String> fileLinesList2;
	List<Driver> drivers;
	Trip trip1, trip2, trip3, trip4, trip5, trip6;
	Driver dan, brian, samwise;
	String filePath = "textFileCopy.txt";
	String filePath2 = "textFile.txt";
	Method isTripValid;
	Method mapLineToTrip;
	
	@Before
	public void setup() throws FileNotFoundException, NoSuchMethodException, SecurityException {
		reader = new InputReader();
		dataManager = new DataManager();
		fileLinesList1 = reader.getLinesFromFile(filePath);
		fileLinesList2 = reader.getLinesFromFile(filePath2);
		trip1 = new Trip(dan, LocalTime.of(8, 0), LocalTime.of(10, 0), 100);
		trip2 = new Trip(brian, LocalTime.of(19, 45), LocalTime.of(23, 30), 400);
		trip3 = new Trip(samwise, LocalTime.of(5, 00), LocalTime.of(5, 30), 100);
		trip4 = new Trip(samwise, LocalTime.of(5, 00), LocalTime.of(5, 30), 1);
		trip5 = new Trip(brian, LocalTime.of(7, 45), LocalTime.of(8, 30), 25);
		trip6 = new Trip(dan, LocalTime.of(9, 45), LocalTime.of(11, 50), 7);
		
		isTripValid = DataManager.class.getDeclaredMethod("isTripValid", Trip.class);
		isTripValid.setAccessible(true);
		
		mapLineToTrip = DataManager.class.getDeclaredMethod("mapLineToTrip", String[].class);
		mapLineToTrip.setAccessible(true);
		
	}
	

	@Test
	public void test_driverList_retrieved_from_file1() {
		drivers = dataManager.getDriverListFromFileLines(fileLinesList1);
		Assert.assertEquals("Number of drivers: ", 5, drivers.size());
	}
	
	@Test
	public void test_driverList_retrieved_from_file2() {
		drivers = dataManager.getDriverListFromFileLines(fileLinesList2);
		Assert.assertEquals("Number of drivers: ", 3, drivers.size());

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_mapLineToTrip_invalid_input() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		String[] invalidString = {"aksdjkas", "akjsdhkajshd,"};
		Trip newTrip = (Trip) mapLineToTrip.invoke(dataManager, invalidString);
	}
	@Test
	public void test_valid_trips() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Assert.assertEquals("Trip 1: ", (Boolean) isTripValid.invoke(dataManager, trip1), true);
		Assert.assertEquals("Trip 5: ", (Boolean) isTripValid.invoke(dataManager, trip5), true);

	}
	
	@Test
	public void test_trips_invalid_under_5mph() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Assert.assertEquals("Trip 4", (Boolean) isTripValid.invoke(dataManager, trip4), false);
		Assert.assertEquals("Trip 6", (Boolean) isTripValid.invoke(dataManager, trip6), false);

	}
	
	@Test
	public void test_trips_invalid_over_100mph() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Assert.assertEquals("Trip 2", (Boolean) isTripValid.invoke(dataManager, trip2), false);
		Assert.assertEquals("Trip 3", (Boolean) isTripValid.invoke(dataManager, trip3), false);
	}
	
	@Test
	public void test_private_map_line_to_trip() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String [] fileLine = {"Trip", "Dan", "07:45", "08:45", "25.8"};
		
		Method mapLineToTrip = DataManager.class.getDeclaredMethod("mapLineToTrip", String[].class);
		mapLineToTrip.setAccessible(true);
		Trip trip = (Trip) mapLineToTrip.invoke(dataManager, new Object[] {fileLine});
		
		Assert.assertEquals("Driver's name should be: ", "Dan", trip.getDriver().getDriverName());
		Assert.assertEquals("Trip's start time should be: ", LocalTime.of(7, 45), trip.getStartTime());
		Assert.assertEquals("Trip's stop time should be: ", LocalTime.of(8, 45), trip.getStopTime());
		Assert.assertEquals("Trip's stop time should be: ", LocalTime.of(8, 45), trip.getStopTime());
		Assert.assertEquals("Trip's miles should be: ", 25.8, trip.getMilesDriven(), 0);
	}
	
	@Test
	public void test_private_add_trip_to_driver_in_list() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		List<Driver> driverTestList = new ArrayList<Driver>();
		Driver driver1 = new Driver();
		Driver driver2 = new Driver();
		driverTestList.add(driver1);
		driverTestList.add(driver2);
		Trip trip = new Trip(driver1, LocalTime.of(7, 45), LocalTime.of(8, 45), 25.8);

		Assert.assertEquals(driver1.getTrips().size(), 0); // test number of trips before method call
		Assert.assertEquals(driver2.getTrips().size(), 0);
		
		Method addTripToDriverInList = DataManager.class.getDeclaredMethod("addTripToDriverInList", List.class, Trip.class);
		addTripToDriverInList.setAccessible(true);
		addTripToDriverInList.invoke(dataManager, driverTestList,trip );
		
		Assert.assertEquals(driver1.getTrips().size(), 1); // test number of trips after method call
		Assert.assertEquals(driver2.getTrips().size(), 0);
	}
	
	@Test
	public void trips_added_to_correct_driver_file1() {
		drivers = dataManager.getDriverListFromFileLines(fileLinesList1);
		List<Trip>driver1Trips = drivers.get(0).getTrips();
		List<Trip>driver2Trips = drivers.get(1).getTrips();
		List<Trip>driver3Trips = drivers.get(2).getTrips();
		List<Trip>driver4Trips = drivers.get(3).getTrips();
		List<Trip>driver5Trips = drivers.get(4).getTrips();


		for (Trip t : driver1Trips) {
			String driversName = t.getDriver().getDriverName();
			Assert.assertEquals("Trip belongs to driver: ", "Dan" , driversName);
			Assert.assertNotEquals("Trip should not belong to driver: ", "Saud", driversName);
		}
		Assert.assertEquals("driver1's number of trips: ", 2, driver1Trips.size());
		
		for (Trip t : driver2Trips) {
			String driversName = t.getDriver().getDriverName();
			Assert.assertEquals("Trip belongs to driver: ", "Alex" , driversName);
		}
		Assert.assertEquals("driver2's number of trips: ", 1, driver2Trips.size());
		
		for (Trip t : driver3Trips) {
			String driversName = t.getDriver().getDriverName();
			Assert.assertEquals("Trip belongs to driver: ", "Bob" , driversName);
		}
		Assert.assertEquals("driver3's number of trips: ", 0, driver3Trips.size());
		
		for (Trip t : driver4Trips) {
			String driversName = t.getDriver().getDriverName();
			Assert.assertEquals("Trip belongs to driver: ", "Saud" , driversName);
		}
		Assert.assertEquals("driver4's number of trips: ", 1, driver4Trips.size());
		
		for (Trip t : driver5Trips) {
			String driversName = t.getDriver().getDriverName();
			Assert.assertEquals("Trip belongs to driver: ", "Brian" , driversName);
		}
		Assert.assertEquals("driver5's number of trips: ", 0, driver5Trips.size());
	}
	
	

}
