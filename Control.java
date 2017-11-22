package lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Control {
	public static void readText(String filename) {
		File file = new File(filename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while((line=reader.readLine()) != null) {
				ProcessText.getWords(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ProcessText.createGraph();
	}
	public static String getDotFormat(ArrayList<String> path) {
		if(ProcessText.vertexlist.isEmpty()==true) {
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
			for(String char1:ProcessText.vertexlist) {
				for(Edge edge:ProcessText.edgelist.get(char1)) {
					dotformat = dotformat +char1+"->"+edge.getChar()+"[label="+edge.getWeight()+"]"+";";
				}
			}
			return dotformat;
		}
	}
	//查询桥接词
	public  static String queryBridgeWords(String words3,String words4){
		String words1=words3.toLowerCase();String words2=words4.toLowerCase();
		if(isContains(words1)==true && isContains(words2)==true) {
			String words = new String();
			int flag=1;
			for(Edge i:ProcessText.edgelist.get(words1)) {
				for(Edge j:ProcessText.edgelist.get(i.getChar())) {
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
		for(String s:ProcessText.vertexlist) {
			if(s.equals(words)==true)
				return true;
		}
		return false;
	}
	//插入桥接词
		public static  String generateNewText(String inputtext) {
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
		public static  void Dijkstra(String words1,ArrayList<Edge> distList){
			PriorityBlockingQueue<Edge> q=new PriorityBlockingQueue<Edge>();
			for(String s:ProcessText.vertexlist){
				if(s.equals(words1)){
					Edge nod =new Edge(1,0,s,0,null);
					distList.add(nod);
					q.add(nod);
				}
				else{
					Edge nod =new Edge(1,Integer.MAX_VALUE,s,0,null);
					distList.add(nod);
				}
			}
			while(q.isEmpty()!=true){
				Edge cd=q.poll();
				if(cd.getvisitflag()==1){
					continue;
				}
				cd.setvisitflag(1);
				int indecd=distList.indexOf(cd);
				for(Edge i:ProcessText.edgelist.get(cd.getver())){
					for(Edge j:distList){
						if(i.getver().equals(j.getver())==true){
							if(i.getvisitflag()==0&&(j.getdst())>(distList.get(indecd).getdst()+i.getweight())){
								j.setdst(distList.get(indecd).getdst()+i.getweight());
								Edge n =new Edge(1,j.getdst(),j.getver(),0,cd.getver());
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
		public static  void printPath(String words2,ArrayList<Edge> distList,ArrayList<String> pathlist){
			String pa=words2;
			while(pa!=null){
				for(Edge i:distList){
					if(i.getver().equals(pa)){
						pathlist.add(pa);
						pa=i.getparent();
						break;
					}
				}
			}
		}
		public static String calcShortestPath(String words3,String words4) {
			String words1=words3.toLowerCase();String words2=words4.toLowerCase();
			ArrayList<Edge> distList = new ArrayList<Edge>();
			ArrayList<String> pathlist= new ArrayList<String>();
			String shortpath = new String();
			if(isContains(words1)==true && isContains(words2)==true) {
				Dijkstra(words1,distList);
				for(Edge i:distList){
					if(i.getver().equals(words2)){
						if(i.getdst()==Integer.MAX_VALUE){
							return "nopath  "+words1+"和"+words2+"之间不可达";
						}
						else{
							shortpath="最短路径权值为："+i.getdst()+"  "+words1;
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
				if(distList.get(ind).getdst()==Integer.MAX_VALUE){
					return "nopath";
				}
				else{
					shortpath="最短路径权值为："+distList.get(ind).getdst()+"   "+words;
					printPath(distList.get(ind).getver(),distList,pathlist);
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
			showDirectedGraph(dotformat, "E://A//JAVA//outshortpath");
			return shortpath;
		}
		public static void showDirectedGraph(String dotformat,String filename)
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
		//随机游走
		public static  void writeText()
		{
			ProcessText.flag=1;
			File file = new File("E://A//JAVA//randomtext.txt");
			try{
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter out = new BufferedWriter(fw);
				out.write(ProcessText.walkpath, 0, ProcessText.walkpath.length());
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public static  String startWalk() {
			for(String i:ProcessText.vertexlist) {
				for(Edge j:ProcessText.edgelist.get(i)){
					j.setvisitflag(0);
				}
			}
			ProcessText.flag=0;
			int ind=(int)Math.floor(Math.random()*ProcessText.vertexlist.size());
			String str=ProcessText.vertexlist.get(ind);
			ProcessText.walkpath= str+" ";
			return ProcessText.walkpath;
		}
		public static  String randomWalk(){
			String str1[]=ProcessText.walkpath.split("\\s+");
			String str = new String();
			str=str1[str1.length-1];
			if(ProcessText.edgelist.get(str).size()==0  || ProcessText.flag==1) {
				writeText();
				return ProcessText.walkpath;
			}
			int in=(int)Math.floor(Math.random()*ProcessText.edgelist.get(str).size());
			ProcessText.flag=ProcessText.edgelist.get(str).get(in).getvisitflag();
			ProcessText.edgelist.get(str).get(in).setvisitflag(1);
			str=ProcessText.edgelist.get(str).get(in).getver();
			ProcessText.walkpath=ProcessText.walkpath+" "+str;
			return ProcessText.walkpath;
		}
}
