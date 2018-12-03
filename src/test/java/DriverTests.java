package test.java;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.driver.Driver;
import main.java.driver.Trip;

public class DriverTests {
	Driver driver1, driver2, driver3;
	Trip trip1, trip2, trip3, trip4, trip5, trip6, trip7, trip8, trip9, trip10;
	
	@Before
	public void setup() {
		driver1 = new Driver();
		driver2 = new Driver();
		driver3 = new Driver();

	}

	@Test
	public void driver_name_equals_override_test() {
		
		driver1.setDriverName("Frodo");
		driver2.setDriverName("Frodo");
		driver3.setDriverName("Bilbo");
		
		Assert.assertEquals("Both drivers have the same name. Should be :", true, driver1.getDriverName().equals(driver2.getDriverName()));
		Assert.assertEquals("Both drivers do not have the same name. Should be :", false, driver3.getDriverName().equals(driver2.getDriverName()));
		Assert.assertEquals("Both drivers do not have the same name. Should be :", false, driver1.getDriverName().equals(driver3.getDriverName()));

	}
	
	@Test
	public void test_add_trip_to_drivers_trips() {
		driver1.addTrip(trip1);driver1.addTrip(trip2);driver1.addTrip(trip3);
		driver2.addTrip(trip4);driver2.addTrip(trip5);driver2.addTrip(trip6);
		driver3.addTrip(trip7);driver3.addTrip(trip8);driver3.addTrip(trip9);driver3.addTrip(trip10);
		
		Assert.assertEquals("driver1's number of trips: ", 3, driver1.getTrips().size());
		Assert.assertEquals("driver2's number of trips: ", 3, driver2.getTrips().size());
		Assert.assertEquals("driver3's number of Trips", 4, driver3.getTrips().size());
	}

}
