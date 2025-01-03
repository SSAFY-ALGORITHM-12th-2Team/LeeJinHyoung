import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	private static int N, M, X;
	private static List<Edge>[] adjList;
	private static int[] dist;

	private static class Edge implements Comparable<Edge> {
		int dest;
		int cost;

		public Edge(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	private static int dijkstra(int start, int x) {
		// TODO Auto-generated method stub
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.offer(new Edge(start, 0));

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			for (Edge e : adjList[cur.dest]) {
				if (dist[e.dest] > dist[cur.dest] + e.cost) {
					dist[e.dest] = dist[cur.dest] + e.cost;
					pq.offer(e);
				}
			}
		}
		return dist[x];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] cond = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N = cond[0];
			M = cond[1];
			X = cond[2];

			adjList = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<Edge>();
			}

			dist = new int[N + 1];

			for (int i = 0; i < M; i++) {
				int[] edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				adjList[edge[0]].add(new Edge(edge[1], edge[2]));
			}
			int answer = Integer.MIN_VALUE;

			for (int start = 1; start <= N; start++) {
				dist[start] = dijkstra(start, X) + dijkstra(X, start);
			}
			int max = Integer.MIN_VALUE;
			for (int start = 1; start <= N; start++) {
				max = Math.max(dist[start], max);
			}
			System.out.println("#" + tc + " " + max);
		}
	}
}