import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int n, m, r;
	private static int[] item;
	private static List<Edge>[] adjList;

	private static class Edge implements Comparable<Edge> {
		int dest;
		int dist;

		public Edge(int dest, int dist) {
			super();
			this.dest = dest;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.dist - o.dist;
		}
	}

	private static int dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.offer(new Edge(start, 0));

		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			for (Edge e : adjList[cur.dest]) {
				if (dist[e.dest] > dist[cur.dest] + e.dist) {
					pq.offer(e);
					dist[e.dest] = dist[cur.dest] + e.dist;
				}
			}
		}
		int itemCount = 0;
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] > m) { // 수색범위보다 크다면
				continue;
			}
			itemCount += item[i];
		}

		return itemCount;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		n = input[0];// 지역 갯수
		m = input[1];// 수색범위
		r = input[2];// 길의 갯수

		int[] itemtemp = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		item = new int[n + 1];
		for (int i = 0; i < itemtemp.length; i++) {
			item[i + 1] = itemtemp[i];
		} // item 초기화

		adjList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList();
		} // 인접리스트 초기화

		for (int i = 0; i < r; i++) {
			int[] edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[edge[0]].add(new Edge(edge[1], edge[2]));
			adjList[edge[1]].add(new Edge(edge[0], edge[2]));
		}

		// 전략 : 전체 정점 돌면서 최댓값 구하면 될 거 같음
		int answer = Integer.MIN_VALUE;
		for (int start = 1; start <= n; start++) {
			int getItem = dijkstra(start);
			answer = Math.max(getItem, answer);
		}
		System.out.println(answer);
	}
}