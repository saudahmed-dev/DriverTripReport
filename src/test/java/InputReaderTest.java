package test.java;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.java.report.InputReader;

public class InputReaderTest {

	InputReader reportReader = new InputReader();
	
	@Test(expected = FileNotFoundException.class)
	public void test_file_not_found() throws FileNotFoundException {
		List<String> lines = reportReader.getLinesFromFile("testingFile.txt");
	}


	@Test
	public void test_number_of_lines_same_as_number_on_file() throws FileNotFoundException {
		List<String> lines = reportReader.getLinesFromFile("textFile.txt");

		Assert.assertEquals("Number of lines: ", 6, lines.size());

	}

	@Test
	public void test_number_of_lines_on_differentFile() throws FileNotFoundException {
		List<String> lines = reportReader.getLinesFromFile("textFileCopy.txt");

		Assert.assertEquals("Expected Number of lines: ", 9, lines.size());

	}

	
}
