import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class Node {
	private int row;
	private int column;
	int distance;
	
	Node(int r,int c){
		this.row=r;
		this.column=c;
		
	}
	public int row(){
		return row;
	}
	
	public int column() {
		return column;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[] size=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int row=size[0];
		int column=size[1];
		int[][] maze=new int[row][column];
		int[][] dist=new int[row][column];
		
		for(int i=0;i<row;i++) {
			int[] r=Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dist[i], -1);
			for(int j=0;j<column;j++) {
				maze[i][j]=r[j];
			}
		}
			
		int[] dX= {1,0,-1,0};
		int[] dY= {0,1,0,-1};
				
		Node init=new Node(0,0);
		Queue<Node> q=new LinkedList<Node>();
		q.offer(init);
		
		int count=0;
		
		while(!q.isEmpty()) {
			Node cur=q.poll();
			if(dist[row-1][column-1]!=-1) {
				break;
			}
			for(int i=0;i<4;i++)
			{				
				int nX=cur.row()+dX[i];
				int nY=cur.column()+dY[i];
				
				if(nX<0||nY<0||nX>=row||nY>=column) continue;
				if(dist[nX][nY]!=-1||maze[nX][nY]!=1) continue;
				
				dist[nX][nY]=dist[cur.row()][cur.column()]+1;
				q.offer(new Node(nX,nY));
			}	
		}
		System.out.println(dist[row-1][column-1]+2);
	}
}