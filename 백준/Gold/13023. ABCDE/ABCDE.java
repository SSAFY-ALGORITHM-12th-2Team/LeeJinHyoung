import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static ArrayList<Integer>[] list;

	static int answer;

	private static void dfs(int cur, boolean[] vis, int depth) {
		if (depth == 5) {
			
			answer = 1;

			return;
		}
		//System.out.println("depth"+depth);
		vis[cur] = true;
		for (int i = 0; i < list[cur].size(); i++) {
			if (!vis[list[cur].get(i)]) {
				//System.out.println(list[cur].get(i));
				dfs(list[cur].get(i), vis, depth + 1);
			}
		}
		vis[cur] = false;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = input[0];
		int M = input[1];

		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList();
		}
		boolean[] vis = new boolean[N];
		for (int i = 0; i < M; i++) {
			int[] rel = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			list[rel[0]].add(rel[1]);
			list[rel[1]].add(rel[0]);
		}
		for (int i = 0; i < N; i++) {
			if (answer != 1) {
				dfs(i, vis, 1);
			}

		}
		System.out.println(answer);
	}
}
