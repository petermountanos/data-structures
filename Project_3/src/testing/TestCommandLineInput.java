package testing;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pjm419.ConsoleCalculator;

public class TestCommandLineInput {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void out() {
	    System.out.print("hello");
	    assertEquals("hello", outContent.toString());
	}

	@Test
	public void err() {
	    System.err.print("hello again");
	    assertEquals("hello again", errContent.toString());
	}
	
	@Test
	public void test() {
	    String[] arr = {"testing_in.txt", "proj3_data/output.txt"};
	    //ConsoleCalculator.validateCLInput(arr);
	    assertEquals("Error: could not find input file \"testing_in.txt\"", errContent.toString());
	}

}
