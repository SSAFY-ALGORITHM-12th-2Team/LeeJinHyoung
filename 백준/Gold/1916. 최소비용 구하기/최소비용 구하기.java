import java.util.*;
import java.io.*;

public class Main {
	private static class Node implements Comparable<Node> {
		int dest;
		int cost;

		public Node(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	private static int N, M;
	private static List<Node>[] adjList;

	private static int dijkstra(int A, int B) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(A, 0));

		boolean[] vis = new boolean[N + 1];

		int[] cost = new int[N + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[A] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (vis[cur.dest]) {
				continue;
			}
			vis[cur.dest] = true;

			for (Node node : adjList[cur.dest]) {
				if (!vis[node.dest] && cost[node.dest] > cost[cur.dest] + node.cost) {
					cost[node.dest] = cost[cur.dest] + node.cost;
					pq.offer(new Node(node.dest, cost[node.dest]));
				}
			}
		}
		return cost[B];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adjList = new ArrayList[N + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		} // 인접 리스트 초기화

		for (int i = 0; i < M; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[edge[0]].add(new Node(edge[1], edge[2]));
		}
		int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int A = edge[0];
		int B = edge[1];
		System.out.println(dijkstra(A, B));
	}
}