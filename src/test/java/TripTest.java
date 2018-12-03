package test.java;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.driver.Driver;
import main.java.driver.Trip;

public class TripTest {
	private Trip trip, trip2, trip3, trip4;
	private Driver dan, brian, samwise;

	
	@Before 
	public void setup() {
	trip = new Trip(dan, LocalTime.of(8, 0), LocalTime.of(10, 0), 100);
	trip2 = new Trip(brian, LocalTime.of(19, 45), LocalTime.of(23, 30), 400);
	trip3 = new Trip(samwise, LocalTime.of(5, 00), LocalTime.of(5, 30), 100);
	trip4 = new Trip(brian, LocalTime.of(7, 45), LocalTime.of(8, 30), 25);
	}
	
	@Test
	public void test_trip_info_correct() {
		Assert.assertTrue(trip.getMilesDriven() == 100);
		Assert.assertTrue(trip.getStartTime() == LocalTime.of(8, 0));
		Assert.assertTrue(trip.getStopTime() == LocalTime.of(10, 00));
		Assert.assertFalse(trip.getStartTime().equals(LocalTime.of(9, 0)));
		Assert.assertFalse(trip.getStopTime().equals(LocalTime.of(9, 0)));

	}
	 
	 
	@Test
	public void test_mins_driven_calculated_correctly() {
		
		Assert.assertEquals("Trip 1:", trip.getMinsDriven(), 120);
		Assert.assertEquals("Trip 2: ", trip2.getMinsDriven(), 225 );
		Assert.assertEquals("Trip 3: ", trip3.getMinsDriven(), 30 );
		Assert.assertEquals("Trip 5: ", trip4.getMinsDriven(), 45 );
	}
}
