import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int Edge = input[1];
			int[] Edge_info = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			ArrayList<Integer>[] adjList = new ArrayList[100];
			boolean[] vis = new boolean[100];

			for (int i = 0; i < Edge_info.length - 1; i += 2) {
				if (adjList[Edge_info[i]] == null) {
					adjList[Edge_info[i]] = new ArrayList<Integer>();
				}
				adjList[Edge_info[i]].add(Edge_info[i + 1]);
			} // 유방향 인접리스트

			vis[0] = true;
			dfs(adjList, 0, vis);

			if (vis[99] == true) {
				System.out.println("#" + tc + " " + 1);
			} else {
				System.out.println("#" + tc + " " + 0);
			}
		}
	}
	private static void dfs(ArrayList<Integer>[] adjList, int cur, boolean[] vis) {
		// TODO Auto-generated method stub
		if (vis[99] == true) {
			return;
		}

		if (adjList[cur] == null) {// 진출할 노드가 없다는 의미
			return;
		}

		for (int i = 0; i < adjList[cur].size(); i++) {
			if (vis[adjList[cur].get(i)] == false) {
				vis[adjList[cur].get(i)] = true;
				dfs(adjList, adjList[cur].get(i), vis);
			}
		}
	}
}