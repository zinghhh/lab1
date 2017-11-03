package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class blacktextcalcshortpa {

	@Test
	public void testCalcShortestPath() {
		ProcessText protes=new ProcessText();
		protes.readText("E://A//JAVA//fff.txt");
		String result="���·��ȨֵΪ��2  how-->next-->we";
		assertEquals("����1������",result,protes.calcShortestPath("How", "we"));
		String result2=new String();
		assertNotEquals("����2������",result2,protes.calcShortestPath("Next", "#"));
		String result3=new String();
		assertNotEquals("����3������",result3,protes.calcShortestPath("#", "Explore"));
		String result4="No ���� and ���� in the graph!";
		assertEquals("����4������",result4,protes.calcShortestPath("", ""));
		String result5="No ��s34jm�� in the graph!";
		assertEquals("����5������",result5,protes.calcShortestPath("S34jm", "Are"));
		String result6="No ��find�� and ��to�� in the graph!";
		assertEquals("����6������",result6,protes.calcShortestPath("Find", "To"));
		String result7="No ��find�� in the graph!";
		assertEquals("����7������",result7,protes.calcShortestPath("Find", "Next"));
		String result8="No ��find�� in the graph!";
		assertEquals("����8������",result8,protes.calcShortestPath("we", "Find"));
		String result9="No ��!???&&�� and ���� in the graph!";
		assertEquals("����9������",result9,protes.calcShortestPath("!???&&", ""));
		String result10="nopath  are��how֮�䲻�ɴ�";
		assertEquals("����10������",result10,protes.calcShortestPath("Are", "How"));

	}

}
