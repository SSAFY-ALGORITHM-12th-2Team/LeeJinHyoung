import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int max;

	static public void dfs(ArrayList<Integer>[] adjList, boolean[] vis, int v, int dist) {
		max = Math.max(max, dist);
		for (int i : adjList[v]) {
			if (!vis[i]) {
				vis[i] = true;
				dfs(adjList, vis, i, dist + 1);
				vis[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			max = Integer.MIN_VALUE;
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int N = input[0];
			int M = input[1];

			ArrayList<Integer>[] adjList = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				int[] vertex = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				adjList[vertex[0]].add(vertex[1]);
				adjList[vertex[1]].add(vertex[0]);
			}
			boolean[] vis = new boolean[N + 1];
			for (int v = 1; v <= N; v++) {
				vis[v] = true;
				dfs(adjList, vis, v, 1);
				vis[v] = false;
			}
			System.out.println("#" + tc + " " + max);
		}
	}
}