package lab1;

import static org.junit.Assert.*;

import org.junit.Test;



public class whitetest1 {

	@Test
	public void testQueryBridgeWords1() {
		String words1="Next";
		String words2="Any";
		String expResult=null;
		Control temp = new Control();
		temp.readText("E://A//JAVA//fff.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);
	}
	@Test
	public void testQueryBridgeWords2() {
		String words1="Are";
		String words2="Any";
		String expResult=null;
		Control temp = new Control();
		temp.readText("E://A//JAVA//fff.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);
	}
	@Test
	public void testQueryBridgeWords3() {
		String words1="Explore";
		String words2="We";
		String expResult=null;
		Control temp = new Control();
		temp.readText("E://A//JAVA//fff.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);
	}
	@Test
	public void testQueryBridgeWords4() {
		String words1="Next";
		String words2="Some";
		String expResult=" we";
		Control temp = new Control();
		temp.readText("E://A//JAVA//fff.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);


	}
	@Test
	public void testQueryBridgeWords5() {
		String words1="To";
		String words2="first";
		String expResult=null;
		Control temp = new Control();
		temp.readText("E://A//JAVA//fff.txt");
		String  result=temp.queryBridgeWords(words1, words2);
        
         assertEquals(expResult, result);

	}
	@Test
	public void testQueryBridgeWords6() {
		String words1="First";
		String words2="is";
		String expResult=null;
		Control temp = new Control();
		temp.readText("E://A//JAVA//fff.txt");
		String  result=temp.queryBridgeWords(words1, words2);
         assertEquals(expResult, result);

	}
}
