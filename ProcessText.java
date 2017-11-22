package lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.System;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.*;

public class ProcessText {
	public static ArrayList<String> vertexlist = new ArrayList<String>();
	public static Map<String,ArrayList<Edge>> edgelist = new HashMap<String,ArrayList<Edge>>(); 
	public static ArrayList<String> charlist = new ArrayList<String>();
	public static String walkpath = new String();
	public static int flag=0;
	
	public static void getWords(String line){
		String s = "[a-z\\sA-Z]+";
		Pattern pattern = Pattern.compile(s);
		Matcher ma = pattern.matcher(line);
		String str= new String();
		while(ma.find()) {
			str=str+ma.group();
		}
		String purestr=str.trim();
		String[] str1;
		str1=purestr.split("\\s+");
		for(String i:str1) {
			if(i!=null)
			charlist.add(i.toLowerCase());
		}
	}
	public static void createGraph() {
		for(int i=0;i<charlist.size()-1;i++) {
			String char1,char2;
			char1=charlist.get(i);char2=charlist.get(i+1);
			if(vertexlist.contains(char1)!=true) {
				ArrayList<Edge> Edge = new ArrayList<Edge>();
				vertexlist.add(char1);
				Edge no =new Edge(1,Integer.MAX_VALUE,char2,0,null);
				Edge.add(no);
				edgelist.put(char1,Edge);
			}
			else {
				int flag=1;
				for(Edge no : edgelist.get(char1)) {
					if(no.findChar(char2)==true) {
						no.increaseWeight();
						flag=0;
					}
				}
				if(flag==1) {
					Edge nod =new Edge(1,Integer.MAX_VALUE,char2,0,null);
					edgelist.get(char1).add(nod);
				}
			}
		}
		ArrayList<Edge> edge = new ArrayList<Edge>();
		if(vertexlist.contains(charlist.get(charlist.size()-1))!=true){
			vertexlist.add(charlist.get(charlist.size()-1));
			edgelist.put(charlist.get(charlist.size()-1), edge);
		}
	}
	
	public static void main(String[] args) {
		//readText("G://graph//work.txt");
		//showDirectedGraph(getDotFormat(null), "G://graph//out");
		//startWalk();
		//while(flag!=1) {
		//randomWalk();
		//}
		//generateNewText("only one friends");
		
	}
}