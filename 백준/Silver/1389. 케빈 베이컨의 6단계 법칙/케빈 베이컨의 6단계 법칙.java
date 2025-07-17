import java.util.*;
import java.io.*;

public class Main {
	static class node {
		int number;
		int depth;

		public node(int number, int depth) {
			this.number = number;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = NM[0];
		int M = NM[1];

		List<Integer>[] adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			adjList[input[0]].add(input[1]);
			adjList[input[1]].add(input[0]);
		}

		int[] answer = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			Queue<node> q = new ArrayDeque<>();
			q.offer(new node(i, 0));

			boolean[] vis = new boolean[N + 1];
			int[] kevinBacon = new int[N + 1];

			while (!q.isEmpty()) {
				node cur = q.poll();

				if (vis[cur.number])
					continue;

				vis[cur.number] = true;
				kevinBacon[cur.number] = cur.depth;
				for (int friend : adjList[cur.number]) {
					if (!vis[friend]) {
						q.offer(new node(friend, cur.depth + 1));
					}
				}
			}

			for (int kevin : kevinBacon) {
				answer[i] += kevin;
			}
		}
		int minValue = Integer.MAX_VALUE;
		int minIndex = 0;
		for (int i = 1; i <= N; i++) {
			if (minValue > answer[i]) {
				minValue = answer[i];
				minIndex = i;
			}
		}
		System.out.println(minIndex);
	}
}