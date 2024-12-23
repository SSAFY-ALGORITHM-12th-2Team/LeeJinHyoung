import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static List<Integer> adjList[];
	private static boolean[] vis;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int M = input[1];

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int[] Edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[Edge[0]].add(Edge[1]);
			adjList[Edge[1]].add(Edge[0]);
		}

		vis = new boolean[N + 1];
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (!vis[i]) {
				dfs(i);
				answer++;
			}
		}
		System.out.println(answer);
	}

	private static void dfs(int start) {
		// TODO Auto-generated method stub
		if (vis[start]) {
			return;
		}
		vis[start] = true;
		for (int dest : adjList[start]) {
			if (!vis[dest]) {
				dfs(dest);
			}
		}
	}
}