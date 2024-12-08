import java.util.*;
import java.io.*;

public class Main {
	private static int N, E;
	private static ArrayList<Node>[] adjList;

	static class Node implements Comparable<Node> {
		int dest, cost;

		public Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, cost));
			adjList[to].add(new Node(from, cost)); // 양방향
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		// 경우의 수 계산
		long path1 = 0, path2 = 0;

		path1 += dijkstra(1, v1);
		path1 += dijkstra(v1, v2);
		path1 += dijkstra(v2, N);

		path2 += dijkstra(1, v2);
		path2 += dijkstra(v2, v1);
		path2 += dijkstra(v1, N);

		long result = Math.min(path1, path2);

		// 도달 불가능한 경우 처리
		System.out.println(result >= Integer.MAX_VALUE ? -1 : result);
	}

	private static int dijkstra(int origin, int destination) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[origin] = 0;
		pq.offer(new Node(origin, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (dist[cur.dest] < cur.cost)
				continue;

			for (Node next : adjList[cur.dest]) {
				if (dist[next.dest] > dist[cur.dest] + next.cost) {
					dist[next.dest] = dist[cur.dest] + next.cost;
					pq.offer(new Node(next.dest, dist[next.dest]));
				}
			}
		}

		return dist[destination];
	}
}
