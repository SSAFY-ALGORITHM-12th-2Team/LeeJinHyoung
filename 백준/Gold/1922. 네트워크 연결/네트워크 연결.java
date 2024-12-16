import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static ArrayList<Node>[] adjList;
	static class Node implements Comparable<Node>{
		int dest;
		int cost;
		public Node(int dest,int cost) {
			this.dest=dest;
			this.cost=cost;
		}
		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.cost-o.cost;
		}
	}
	
	public void prim() {
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int M=Integer.parseInt(br.readLine());
		
		adjList=new ArrayList[N+1];
		for(int i=0;i<=N;i++) {
			adjList[i]=new ArrayList();
		}
		for(int i=0;i<M;i++) {
			int[] input=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[input[0]].add(new Node(input[1],input[2]));
			adjList[input[1]].add(new Node(input[0],input[2]));
		}
		
		PriorityQueue<Node>pq=new PriorityQueue<Node>();
		boolean[] vis=new boolean[N+1];
		pq.offer(new Node(1,0));
		
		int cost=0;
				
		while(!pq.isEmpty()) {
			Node cur=pq.poll();
			
			if(vis[cur.dest]) {
				continue;
			}
			
			vis[cur.dest]=true;
			cost+=cur.cost;
			
			for(Node node:adjList[cur.dest]) {
				if(!vis[node.dest]) {
					pq.offer(node);
				}
			}
		}
		System.out.println(cost);
	}
}