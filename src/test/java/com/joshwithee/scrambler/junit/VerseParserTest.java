package test.java.com.joshwithee.scrambler.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.com.joshwithee.scrambler.service.VerseParser;

public class VerseParserTest {

	public VerseParser vp = new VerseParser();
	
	@Before
	public void setup(){
		
	}
	
	
	@Test
	public void cleanVerseTest() {
		String testThisString = "\t   ___ddddd[234:123423423]s\nbbbbbb\n\t\n   sss \t\n(ESV)end\n";
		String checkThisString = vp.cleanVerse(testThisString);
		assertEquals("___ddddds bbbbbb sssend",checkThisString);
	}
	
	@Test
	public void removeBlanksFromArrayListTest(){
		ArrayList<String> a = new ArrayList<String>();
		a.add("   \t\n  ");
		a.add("");
		a.add("   .   ");
		a.add(" ");
		VerseParser.removeBlanksFromArrayList(a);
		assertEquals(1,a.size());
	}
	
	@Test
	public void trimStringsInArrayListTest(){
		ArrayList<String> a = new ArrayList<String>();
		a.add("   \t\n  ");
		a.add("");
		a.add("   .   ");
		a.add(" ");
		VerseParser.trimStringsInArrayList(a);
		String r = "";
		for(String s : a){
			r += s;
		}
		assertEquals(".",r);
	}

}
