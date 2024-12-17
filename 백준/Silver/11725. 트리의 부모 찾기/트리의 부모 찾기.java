import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int N;
	private static List<Integer>[] adjList;
	private static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList();
		}
		for (int i = 0; i < N - 1; i++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			adjList[input[0]].add(input[1]);
			adjList[input[1]].add(input[0]);
		}

		parents = new int[N + 1];
		bfs();
		for (int i = 2; i < parents.length; i++) {
			System.out.println(parents[i]);
		}
	}

	private static void bfs() {
		// TODO Auto-generated method stub
		boolean[] vis = new boolean[N + 1];
		vis[1] = true;
		Queue<Integer> q = new ArrayDeque();
		q.offer(1);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < adjList[cur].size(); i++) {
				if (!vis[adjList[cur].get(i)]) {
					vis[adjList[cur].get(i)] = true;
					parents[adjList[cur].get(i)] = cur;
					q.offer(adjList[cur].get(i));
				}
			}
		}
	}
}
