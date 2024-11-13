import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static class Node implements Comparable<Node> {
		int dest;
		int weight;

		protected Node(int dest, int weight) {
			super();
			this.dest = dest;
			this.weight = weight;

		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}

	static int V, E, K;
	static ArrayList<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		V = input[0];
		E = input[1];

		K = Integer.parseInt(br.readLine());

		adjList = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			int[] Edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[Edge[0]].add(new Node(Edge[1], Edge[2]));
		}
		int[] dist = dijkstra();
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else
				System.out.println(dist[i]);
		}
	}

	private static int[] dijkstra() {
		// TODO Auto-generated method stub
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(K, 0));
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			int weight = cur.weight;
			int dest = cur.dest;

			if (dist[cur.dest] < weight) {
				continue;
			}

			for (Node node : adjList[dest]) {
				int nextVertex = node.dest;
				int nextweight = node.weight;
				int nextDist = node.weight + cur.weight;

				if (nextDist < dist[node.dest]) {
					dist[node.dest] = nextDist;
					pq.offer(new Node(nextVertex, nextDist));
				}
			}
		}
		return dist;
	}

}
