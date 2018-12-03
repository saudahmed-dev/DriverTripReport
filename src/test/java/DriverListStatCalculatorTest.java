package test.java;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.driver.*;
import main.java.report.DriverListStatCalculator;

public class DriverListStatCalculatorTest {

	Trip trip1, trip2, trip3, trip4, trip5, trip6, trip7, trip8, trip9, trip10;
	Driver dan, brian, samwise, frodo;
	List<Driver> drivers;

	@Before
	public void setup() {
		trip1 = new Trip(dan, LocalTime.of(8, 0), LocalTime.of(10, 0), 100);
		trip2 = new Trip(brian, LocalTime.of(19, 45), LocalTime.of(23, 30), 400);
		trip3 = new Trip(samwise, LocalTime.of(5, 00), LocalTime.of(5, 30), 100);
		trip4 = new Trip(samwise, LocalTime.of(5, 00), LocalTime.of(5, 30), 1);
		trip5 = new Trip(brian, LocalTime.of(7, 45), LocalTime.of(8, 30), 25);
		trip6 = new Trip(dan, LocalTime.of(9, 45), LocalTime.of(11, 50), 7);
		trip7 = new Trip(brian, LocalTime.of(11, 0), LocalTime.of(15, 0), 150);
		trip8 = new Trip(samwise, LocalTime.of(13, 34), LocalTime.of(18, 56), 200);
		trip9 = new Trip(dan, LocalTime.of(4, 45), LocalTime.of(10, 0), 250);
		trip10 = new Trip(dan, LocalTime.of(11, 30), LocalTime.of(12, 45), 50);
		dan = new Driver();
		brian = new Driver();
		samwise = new Driver();
		dan.addTrip(trip1);
		dan.addTrip(trip6);
		dan.addTrip(trip9);
		dan.addTrip(trip10);
		brian.addTrip(trip2);
		brian.addTrip(trip5);
		brian.addTrip(trip7);
		samwise.addTrip(trip3);
		samwise.addTrip(trip4);
		samwise.addTrip(trip8);

		drivers = new ArrayList<Driver>();

		drivers.add(dan);
		drivers.add(brian);
		drivers.add(samwise);

	}

	@Test
	public void test_stats_calculated_correctly() {
		DriverListStatCalculator statCalc = new DriverListStatCalculator();

		statCalc.calculateDriverStats(drivers);
		Assert.assertEquals("Dan's average MPH: ", 38, dan.getAvgMPH(), 0);

		Trip trip11 = new Trip(dan, LocalTime.of(11, 30), LocalTime.of(12, 50), 120);
		dan.addTrip(trip11);
		statCalc.calculateDriverStats(drivers);
		Assert.assertEquals("Dan's average MPH: ", 44, dan.getAvgMPH(), 0);
		Assert.assertEquals("Brian's average MPH: ", 68, brian.getAvgMPH(), 0);
		Assert.assertEquals("Samwise's average MPH: ", 47, samwise.getAvgMPH(), 0);

	}

}
