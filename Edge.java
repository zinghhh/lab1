package lab1;



public class Edge implements Comparable<Edge>{
	private static int weight;
	private static int dst;
	private static String ver;
	private static String parent;
	private static int visitflag;
	public int getweight()
	{
		return this.weight;
	}
	public void setweight(int w)
	{
		this.weight=w;
	}
	public int getdst()
	{
		return this.dst;
	}
	public void setdst(int d)
	{
		this.dst=d;
	}
	public String getver()
	{
		return this.ver;
	}
	public void setver(String ver)
	{
		this.ver=ver;
	}
	public String getparent()
	{
		return this.parent;
	}
	public void setparent(String parent)
	{
		this.parent=parent;
	}
	public int getvisitflag()
	{
		return this.visitflag;
	}
	public void setvisitflag(int visitflag)
	{
		this.visitflag=visitflag;
	}
	public Edge(int i,int b, String char2,int a,String pa) {
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
	
	public int compareTo(Edge o)
	{
		return this.dst<o.dst?-1:1;
	}
	public int getWeight() {
		return this.weight;
	}
}
