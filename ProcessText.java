package he;
//one change
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
	private static ArrayList<String> vertexlist = new ArrayList<String>();
	private static Map<String,ArrayList<Node>> edgelist = new HashMap<String,ArrayList<Node>>(); 
	private static ArrayList<String> charlist = new ArrayList<String>();
	private static String walkpath = new String();
	private static int flag=0;
	public static class Node implements Comparable<Node>{
		private int weight;
		private int dst;
		private String ver;
		private String parent;
		private int visitflag;
		public Node(int i,int b, String char2,int a,String pa) {
			this.weight=i;
			this.dst=b;
			this.ver=char2;
			this.visitflag=a;
			this.parent=pa;
		}
		public boolean findChar(String char1) {
			if(this.ver.equals(char1)==false)
				return false;
			else
				return true;
		}
		public void increaseWeight() {
			this.weight++;
		}
		public String getChar() {
			return ver;
		}
		
		public int compareTo(Node o)
		{
			return this.dst<o.dst?-1:1;
		}
		public int getWeight() {
			return this.weight;
		}
	}
	private void getWords(String line){
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
	private  void createGraph() {
		for(int i=0;i<charlist.size()-1;i++) {
			String char1,char2;
			char1=charlist.get(i);char2=charlist.get(i+1);
			if(vertexlist.contains(char1)!=true) {
				ArrayList<Node> edge = new ArrayList<Node>();
				vertexlist.add(char1);
				Node no =new Node(1,Integer.MAX_VALUE,char2,0,null);
				edge.add(no);
				edgelist.put(char1,edge);
			}
			else {
				int flag=1;
				for(Node no : edgelist.get(char1)) {
					if(no.findChar(char2)==true) {
						no.increaseWeight();
						flag=0;
					}
				}
				if(flag==1) {
					Node nod =new Node(1,Integer.MAX_VALUE,char2,0,null);
					edgelist.get(char1).add(nod);
				}
			}
		}
		ArrayList<Node> edge = new ArrayList<Node>();
		if(vertexlist.contains(charlist.get(charlist.size()-1))!=true){
			vertexlist.add(charlist.get(charlist.size()-1));
			edgelist.put(charlist.get(charlist.size()-1), edge);
		}
	}
	public void readText(String filename) {
		File file = new File(filename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while((line=reader.readLine()) != null) {
				getWords(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		createGraph();
	}
	//查询桥接词
	public   String queryBridgeWords(String words3,String words4){
		String words1=words3.toLowerCase();String words2=words4.toLowerCase();
		if(isContains(words1)==true && isContains(words2)==true) {
			String words = new String();
			int flag=1;
			for(Node i:edgelist.get(words1)) {
				for(Node j:edgelist.get(i.getChar())) {
					if(j.findChar(words2)==true) {
						words=words+" "+i.getChar();
						flag=0;
					}
				}
			}
			if(flag==0) {
				return "The bridge words from “"+words1+"” to “"+words2+"” is: " + words;
			}
			else {
				return "No bridge words from “"+words1+"” to “"+words2+"”";
			}
		}
		else if(isContains(words1)==false && isContains(words2)==false) {
			return "No “"+words1+"” and “"+words2+"” in the graph!";
		}
		else if(isContains(words1)==false && isContains(words2)==true) {
			return "No “"+words1+"” in the graph!";
		}
		else {
			return "No “"+words2+"” in the graph!";
		}
	}
	private static boolean isContains(String words) {
		for(String s:vertexlist) {
			if(s.equals(words)==true)
				return true;
		}
		return false;
	}
	//插入桥接词
	public   String generateNewText(String inputtext) {
		String[] input = inputtext.split("\\s+");
		String output = new String();
		for(int i=0;i<input.length-1;i++) {
			String[] temp=queryBridgeWords(input[i].toLowerCase(), input[i+1].toLowerCase()).split("\\s+");
			if(temp[0].equals("No")) {
				output=output+" "+input[i];
			}
			else {
				output=output+" "+input[i]+" "+temp[8];
			}
		}
		output=output+" "+input[input.length-1];
		return output;
	}
	//计算最短路径
	public static  void Dijkstra(String words1,ArrayList<Node> distList){
		PriorityBlockingQueue<Node> q=new PriorityBlockingQueue<Node>();
		for(String s:vertexlist){
			if(s.equals(words1)){
				Node nod =new Node(1,0,s,0,null);
				distList.add(nod);
				q.add(nod);
			}
			else{
				Node nod =new Node(1,Integer.MAX_VALUE,s,0,null);
				distList.add(nod);
			}
		}
		while(q.isEmpty()!=true){
			Node cd=q.poll();
			if(cd.visitflag==1){
				continue;
			}
			cd.visitflag=1;
			int indecd=distList.indexOf(cd);
			for(Node i:edgelist.get(cd.ver)){
				for(Node j:distList){
					if(i.ver.equals(j.ver)==true){
						if(i.visitflag==0&&(j.dst)>(distList.get(indecd).dst+i.weight)){
							j.dst=distList.get(indecd).dst+i.weight;
							Node n =new Node(1,j.dst,j.ver,0,cd.ver);
							int in=distList.indexOf(j);
							distList.set(in,n);
							q.add(n);
						}
						break;
					}
				}
			}
		}
	}
	public static  void printPath(String words2,ArrayList<Node> distList,ArrayList<String> pathlist){
		String pa=words2;
		while(pa!=null){
			for(Node i:distList){
				if(i.ver.equals(pa)){
					pathlist.add(pa);
					pa=i.parent;
					break;
				}
			}
		}
	}
	public String calcShortestPath(String words3,String words4) {
		String words1=words3.toLowerCase();String words2=words4.toLowerCase();
		ArrayList<Node> distList = new ArrayList<Node>();
		ArrayList<String> pathlist= new ArrayList<String>();
		String shortpath = new String();
		if(isContains(words1)==true && isContains(words2)==true) {
			Dijkstra(words1,distList);
			for(Node i:distList){
				if(i.ver.equals(words2)){
					if(i.dst==Integer.MAX_VALUE){
						return "nopath  "+words1+"和"+words2+"之间不可达";
					}
					else{
						shortpath="最短路径权值为："+i.dst+"   "+words1;
						break;
					}
				}
			}
			printPath(words2,distList,pathlist);
			for(int i=pathlist.size()-2;i>=0;i--){
				shortpath=shortpath+"-->"+pathlist.get(i);
			}
		}
		else if((isContains(words1)==true && words2.equals("#"))||(isContains(words2)==true && words1.equals("#"))) {
			String words;
			if(isContains(words1)==true) words=words1;
			else words=words2;
			Dijkstra(words,distList);
			int ind=(int)Math.floor(Math.random()*distList.size());
			if(distList.get(ind).dst==Integer.MAX_VALUE){
				return "nopath";
			}
			else{
				shortpath="最短路径权值为："+distList.get(ind).dst+"   "+words;
				printPath(distList.get(ind).ver,distList,pathlist);
				for(int i=pathlist.size()-2;i>=0;i--){
					shortpath=shortpath+"-->"+pathlist.get(i);
				}
			}
		}
		else if(isContains(words1)==false && isContains(words2)==false) {
			return "No “"+words1+"” and “"+words2+"” in the graph!";
		}
		else if(isContains(words1)==false && isContains(words2)==true) {
			return "No “"+words1+"” in the graph!";
		}
		else {
			return "No “"+words2+"” in the graph!";
		}
		String dotformat = getDotFormat(pathlist);
		showDirectedGraph(dotformat, "G://graph//outshortpath");
		return shortpath;
	}
	//随机游走
	public static  void writeText()
	{
		flag=1;
		File file = new File("G://graph//randomtext.txt");
		try{
			file.createNewFile();
		}catch(IOException e){
			e.printStackTrace();
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(walkpath, 0, walkpath.length());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static  String startWalk() {
		for(String i:vertexlist) {
			for(Node j:edgelist.get(i)){
				j.visitflag=0;
			}
		}
		flag=0;
		int ind=(int)Math.floor(Math.random()*vertexlist.size());
		String str=vertexlist.get(ind);
		walkpath= str+" ";
		return walkpath;
	}
	public static  String randomWalk(){
		String str1[]=walkpath.split("\\s+");
		String str = new String();
		str=str1[str1.length-1];
		if(edgelist.get(str).size()==0  || flag==1) {
			writeText();
			return walkpath;
		}
		int in=(int)Math.floor(Math.random()*edgelist.get(str).size());
		flag=edgelist.get(str).get(in).visitflag;
		edgelist.get(str).get(in).visitflag=1;
		str=edgelist.get(str).get(in).ver;
		walkpath=walkpath+" "+str;
		return walkpath;
	}
	
	public void showDirectedGraph(String dotformat,String filename)
	{
		if(dotformat!=null) {
		    GraphViz gv=new GraphViz();
		    gv.addln(gv.start_graph());
		    gv.add(dotformat);
		    gv.addln(gv.end_graph());
		    String type = "gif";
		    //String type = "pdf";
		  // gv.increaseDpi();
		    gv.decreaseDpi();
		    File out = new File(filename+"."+ type); 
		    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
		}
		else
			System.out.println("The graph is empty!");
	}
	public String getDotFormat(ArrayList<String> path) {
		if(vertexlist.isEmpty()==true) {
			return null;
		}
		else {
			String dotformat = new String();
			if(path!=null)
			if(path.size()!=0) {
				dotformat = dotformat + path.get(0)+"[color="+"red"+",style="+"filled"+",fillcolor="+"red"+"];";
				for(int i=1;i<path.size()-1;i++) {
					dotformat = dotformat + path.get(i)+"[color="+"red"+"];";
				}
				dotformat = dotformat + path.get(path.size()-1)+"[color="+"red"+",style="+"filled"+",fillcolor="+"yellow"+"];";
			}
			for(String char1:vertexlist) {
				for(Node edge:edgelist.get(char1)) {
					dotformat = dotformat +char1+"->"+edge.getChar()+"[label="+edge.getWeight()+"]"+";";
				}
			}
			return dotformat;
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