import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int N;
	private static int[] holeCost;
	private static int[][] adjMatrix;

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

	private static int prim(int start, int init) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		boolean[] vis = new boolean[N];

		pq.offer(new Edge(start, 0));

		int totalCost = init;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();// 간선 시작점

			int cur = edge.dest;
			int cost = edge.cost;

			if (vis[cur]) {
				continue;
			}
			vis[cur] = true;
			totalCost += cost;
			for (int dest = 0; dest < adjMatrix[cur].length; dest++) {
				if (!vis[dest]) {
					if (adjMatrix[cur][dest] > holeCost[dest]) {
						pq.offer(new Edge(dest, holeCost[dest]));
					} else {
						pq.offer(new Edge(dest, adjMatrix[cur][dest]));
					}
				}
			}
		}
		return totalCost;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		holeCost = new int[N];
		adjMatrix = new int[N][N];

		int answer = Integer.MAX_VALUE;
		int start = 0;

		for (int i = 0; i < N; i++) {
			holeCost[i] = Integer.parseInt(br.readLine());
			answer = Math.min(answer, holeCost[i]);
			if (holeCost[i] == answer) {
				start = i;
			}
		}
		for (int i = 0; i < N; i++) {
			adjMatrix[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		} // 입력 끝
			// 1. 우선 가장 우물을 파기 가장 저렴한 위치에서 우물을 판다.
			// 입력을 받으면서 실행
			// start는 이제 시작점

		System.out.println(prim(start, answer));
		// 우물을 파는게 나은지 아니면 다른 논에서 잇는 게 나은지 판단한다.
	}
}