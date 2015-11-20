package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.cs.pjm419.FindWords;

public class FindWordsTest {
	
	@Test
	public void isLettersTest() {
		assertEquals(false,FindWords.isLetters("This is a full blow sentence; I have 5 apples!"));
		assertEquals(false,FindWords.isLetters("abc123"));
		assertEquals(false,FindWords.isLetters("abc."));
		assertEquals(false,FindWords.isLetters("abc def"));
		assertEquals(false,FindWords.isLetters("abc,def"));
		assertEquals(true,FindWords.isLetters("ABCabc"));
	}
	
}
