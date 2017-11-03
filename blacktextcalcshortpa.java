package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class blacktextcalcshortpa {

	@Test
	public void testCalcShortestPath() {
		ProcessText protes=new ProcessText();
		protes.readText("E://A//JAVA//fff.txt");
		String result="最短路径权值为：2  how-->next-->we";
		assertEquals("用例1有问题",result,protes.calcShortestPath("How", "we"));
		String result2=new String();
		assertNotEquals("用例2有问题",result2,protes.calcShortestPath("Next", "#"));
		String result3=new String();
		assertNotEquals("用例3有问题",result3,protes.calcShortestPath("#", "Explore"));
		String result4="No “” and “” in the graph!";
		assertEquals("用例4有问题",result4,protes.calcShortestPath("", ""));
		String result5="No “s34jm” in the graph!";
		assertEquals("用例5有问题",result5,protes.calcShortestPath("S34jm", "Are"));
		String result6="No “find” and “to” in the graph!";
		assertEquals("用例6有问题",result6,protes.calcShortestPath("Find", "To"));
		String result7="No “find” in the graph!";
		assertEquals("用例7有问题",result7,protes.calcShortestPath("Find", "Next"));
		String result8="No “find” in the graph!";
		assertEquals("用例8有问题",result8,protes.calcShortestPath("we", "Find"));
		String result9="No “!???&&” and “” in the graph!";
		assertEquals("用例9有问题",result9,protes.calcShortestPath("!???&&", ""));
		String result10="nopath  are和how之间不可达";
		assertEquals("用例10有问题",result10,protes.calcShortestPath("Are", "How"));

	}

}
