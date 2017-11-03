package whitetest;

import static org.junit.Assert.*;

import org.junit.Test;

import he.ProcessText;

public class whitetest1 {

	@Test
	public void testQueryBridgeWords1() {
		String words1="Next";
		String words2="Any";
		String expResult=null;
		ProcessText temp = new ProcessText();
		temp.readText("G://graph//test.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);
	}
	@Test
	public void testQueryBridgeWords2() {
		String words1="Are";
		String words2="Any";
		String expResult=null;
		ProcessText temp = new ProcessText();
		temp.readText("G://graph//test.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);
	}
	@Test
	public void testQueryBridgeWords3() {
		String words1="Explore";
		String words2="We";
		String expResult=null;
		ProcessText temp = new ProcessText();
		temp.readText("G://graph//test.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);
	}
	@Test
	public void testQueryBridgeWords4() {
		String words1="Next";
		String words2="Some";
		String expResult="we";
		ProcessText temp = new ProcessText();
		temp.readText("G://graph//test.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);


	}
	@Test
	public void testQueryBridgeWords5() {
		String words1="To";
		String words2="first";
		String expResult=null;
		ProcessText temp = new ProcessText();
		temp.readText("G://graph//test.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);

	}
	@Test
	public void testQueryBridgeWords6() {
		String words1="First";
		String words2="is";
		String expResult=null;
		ProcessText temp = new ProcessText();
		temp.readText("G://graph//test.txt");
		String  result=temp.queryBridgeWords(words1, words2);
         assertEquals(expResult, result);

	}
}
