package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DictionaryTrieTest {
	
	private DictionaryTrie tester1;
	private DictionaryTrie tester2;
	
	@Before
	public void setUp() throws Exception {
		tester1 = new DictionaryTrie("testdict.txt");
		tester2 = new DictionaryTrie("TWL06.txt");
	}

	@After
	public void tearDown() {
		tester1 = null;
		tester2 = null;
	}
	
	@Test
	public void containsPrefixTest() {
		assertEquals(true, tester1.containsPrefix("alpha"));
		assertEquals(true, tester2.containsPrefix("abridge"));
		assertEquals(false, tester1.containsPrefix("foo"));
		assertEquals(false, tester2.containsPrefix("xyz"));
	}
	
	@Test
	public void containsTest() {
		 assertEquals(true,tester1.contains("rusty"));
		 assertEquals(true,tester2.contains("computer"));
		 assertEquals(false,tester1.contains(""));
		 assertEquals(false,tester2.contains("abc"));
	}
	
	@Test
	public void sizeTest() {
		assertEquals(29, tester1.size);
		assertEquals(178691,tester2.size);
	}
	
	@Test
	public void insertTest() {
		tester1.insert("foobar");
		assertEquals(true,tester1.contains("foobar"));
	}
}
