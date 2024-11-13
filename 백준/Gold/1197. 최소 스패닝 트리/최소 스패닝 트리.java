import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static class Node implements Comparable<Node> {
		int dest;
		int weight;

		protected Node(int dest, int weight) {
			super();
			this.weight = weight;
			this.dest = dest;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	static int V, E;
	static List<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		V = input[0];
		E = input[1];

		adjList = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			adjList[i] = new ArrayList();
		}

		for (int i = 0; i < E; i++) {
			int[] Edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[Edge[0]].add(new Node(Edge[1], Edge[2]));
			adjList[Edge[1]].add(new Node(Edge[0], Edge[2]));
		}
		
		System.out.println(prim());
	}

	private static int prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] vis = new boolean[V + 1];

		pq.offer(new Node(1, 0));

		int totalWeight = 0;
		int idx = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (vis[cur.dest])
				continue;

			vis[cur.dest] = true;
			totalWeight += cur.weight;

			for (Node node : adjList[cur.dest]) {
				if (!vis[node.dest]) {
					pq.offer(node);
				}
			}
			idx++;
		}
		return totalWeight;
	}
}