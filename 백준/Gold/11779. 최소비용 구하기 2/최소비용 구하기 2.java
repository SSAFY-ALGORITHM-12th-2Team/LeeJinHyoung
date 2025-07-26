import java.util.*;
import java.io.*;

public class Main {
	static class dest implements Comparable<dest> {
		int dest;
		int cost;

		public dest(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(dest o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		List<dest>[] adjList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[input[0]].add(new dest(input[1], input[2]));
		}
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int sp = input[0];
		int ep = input[1];

		boolean[] vis = new boolean[n + 1];
		int[] totalCost = new int[n + 1];
		int[] prev = new int[n + 1];

		Arrays.fill(totalCost, Integer.MAX_VALUE);
		PriorityQueue<dest> pq = new PriorityQueue<>();
		pq.offer(new dest(sp, 0));
		totalCost[sp] = 0;

		int minCost = 0;
		List<Integer> answerRoute = new ArrayList<>();

		while (!pq.isEmpty()) {
			dest cur = pq.poll();

			if (cur.cost > totalCost[cur.dest])
				continue;

			for (dest next : adjList[cur.dest]) {
				if (!vis[next.dest] && totalCost[next.dest] > totalCost[cur.dest] + next.cost) {
					totalCost[next.dest] = totalCost[cur.dest] + next.cost;
					prev[next.dest] = cur.dest;
					pq.offer(new dest(next.dest, totalCost[next.dest]));
				}
			}
		}
//		정답 출력
//		여기까진 다익스트라 완료 역추적 방법 고민
		int curNode = ep;
		while (curNode != sp) {
			answerRoute.add(0, curNode);
			curNode = prev[curNode];
		}
		answerRoute.add(0, curNode);

		StringBuilder answer = new StringBuilder();
		for (int i : answerRoute)
			answer.append(i + " ");
		System.out.println(totalCost[ep] + "\n" + answerRoute.size() + "\n" + answer);
	}
}