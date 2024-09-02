import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static boolean[] vis;
	static ArrayList<Integer>[] list;
	static int N;

	static void union(int a, int b) {
		while (!list[b].isEmpty()) {
			list[a].add(list[b].remove(0));
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int N = input[0];
			int M = input[1];

			list = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				int[] edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				// System.out.println(edge[0] + " " + edge[1]);
				list[edge[0]].add(edge[1]);
				list[edge[1]].add(edge[0]);
			}

			vis = new boolean[N + 1];
		
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (!vis[i]) {
					vis[i] = true;
					dfs(i);
					cnt++;
					
				}
			}

			System.out.println("#" + tc + " " + cnt);
		}
	}

	private static void dfs(int idx) {
		// TODO Auto-generated method stub

		for (int i = 0; i < list[idx].size(); i++) {
			if (vis[list[idx].get(i)] == false) {
				vis[list[idx].get(i)] = true;
				dfs(list[idx].get(i));
			}
		}
	}
}