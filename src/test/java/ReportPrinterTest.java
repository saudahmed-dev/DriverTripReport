package test.java;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.driver.Driver;
import main.java.report.ReportPrinter;


public class ReportPrinterTest {

	private Driver driver1, driver2, driver3;
	private List<Driver> drivers = new ArrayList<Driver>();
	private ReportPrinter reportPrinter;
	private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private PrintStream originalOut = System.out;


	@Before
	public void setup() {
		System.setOut(new PrintStream(outContent));
		
		reportPrinter = new ReportPrinter();
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	@Test
	public void test_print_driver_report() {
		driver1 = new Driver("Dan", 150, 45);
		driver2 = new Driver("Brian", 45, 35);
		driver3 = new Driver("Garth", 225, 60);
		
		drivers.add(driver1); drivers.add(driver2); drivers.add(driver3);
		reportPrinter.printDriverReport(drivers);
		
		Assert.assertEquals("Garth 225 miles @ 60 mph\n"+ 
							"Dan 150 miles @ 45 mph\n"+ 
							"Brian 45 miles @ 35 mph\n", outContent.toString());
		
		Driver driver4 = new Driver("Saud", 500, 100);
		
		drivers.add(driver4);
		outContent.reset();
		reportPrinter.printDriverReport(drivers);
		
		Assert.assertEquals("Saud 500 miles @ 100 mph\n"+
						   "Garth 225 miles @ 60 mph\n"+ 
							"Dan 150 miles @ 45 mph\n"+ 
							"Brian 45 miles @ 35 mph\n", outContent.toString());
	}

}
