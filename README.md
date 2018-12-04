DriverTripReport
======

<b>*To install and run the application please follow these instructions* </b>

1. Navigate to parent folder that contains the 'pom.xml' file
2. Run this code: `mvn install`
3. Navigate to the  'target' folder 
4. Run this code: `java -cp RootDriverReport-1.0.jar main.java.RootDriverReport <arguments>`

And insert your file where `<arguments>` goes without the '<' and '>' on the ends.

***
### Project Summary

A command line application that reads a list of drivers and trips from a .txt file and returns a report with calculations of their total miles and average mph. 

The input file is read by the application with each line prompting one of two commands: 
+ Driver - registers a new driver
+ Trip - logs a trip that includes a driver's name, beginning time, end time, and miles driven.

Any trips with an average mph below 5 or above 100 are considered invalid and ignored.
***
### Process and Approach

My goals were to:
+ Write thorough tests for positive and negative outcomes 
+ To keep the code clean and easy to read
+ Maintain tight encapsulation within all the classes created.

##### Flow summary:

ReportReader reads file and ReportDataManager coverts it to list of drivers which is printed by ReportPrinter.
***

The firsts tests were written for the Driver class. The class was initially modeled with only a driver name, total miles driven and average MPH. However, in order to determine the driver's total miles and average MPH, a list of trips was added later on to be utilized by DriverStatCalculator class for reporting.

To keep the conversion and calculation of minutes driven on the Trip class simple, the start and stop times were stored as LocalTime variables taking advantage of it's static parse method and leveraging Java's ChronoUnit class's built in method to calculate the time between two LocalTime variables.  

Next, I wrote basic test cases for the ReportReader class that reads the input file and returns a List of Strings with each string containing the command and it's parameters. 

The list of strings is then converted by the ReportDataManager class to execute either registering a new driver or mapping trips to the correct driver if the trip passes validation. In order to check which driver the trip belonged to, an equals override was added to the Driver class with the driverName as the uniqueness identifier.

As functionality was added to the ReportDataManager the class grew harder to read. Upon refactoring, three private utility methods were extracted and tests for these methods were rewritten using method reflection. Before returning the lit of Drivers this class calls the DriverStatCalculator to cleanly calculate each of the driver's stats.

The final class, other than the main method, is the ReportPrinter which formats the list of drivers for output. Before formatting, this class's method leverages the Comparator interface to sort the list of drivers by miles driven.


