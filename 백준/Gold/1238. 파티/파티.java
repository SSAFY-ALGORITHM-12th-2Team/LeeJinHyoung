
import java.util.*;
import java.io.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int end;
		int time;

		public Edge(int end, int time) {
			this.end = end;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return this.time - o.time;
		}
	}

	static int[] dijkstra(List<Edge>[] graph, int start, int N) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.end])
				continue;
			visited[cur.end] = true;

			for (Edge next : graph[cur.end]) {
				if (dist[next.end] > dist[cur.end] + next.time) {
					dist[next.end] = dist[cur.end] + next.time;
					pq.offer(new Edge(next.end, dist[next.end]));
				}
			}
		}

		return dist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int M = input[1];
		int X = input[2];

		List<Edge>[] village = new ArrayList[N + 1];
		List<Edge>[] reverseVillage = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			village[i] = new ArrayList<>();
			reverseVillage[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int start = edge[0];
			int end = edge[1];
			int time = edge[2];

			village[start].add(new Edge(end, time)); // 정방향
			reverseVillage[end].add(new Edge(start, time)); // 역방향
		}

		// X -> 다른 모든 노드 (파티에서 집으로 돌아오는 시간)
		int[] distFromX = dijkstra(village, X, N);
		// 다른 모든 노드 -> X (집에서 파티로 가는 시간)
		int[] distToX = dijkstra(reverseVillage, X, N);

		int maxTime = 0;
		for (int i = 1; i <= N; i++) {
			maxTime = Math.max(maxTime, distFromX[i] + distToX[i]);
		}

		System.out.println(maxTime);
	}
}