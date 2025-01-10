import java.util.*;
import java.io.*;

public class Main {
	private static class Node {
		int dest;
		int dist;

		public Node(int dest, int dist) {
			super();
			this.dest = dest;
			this.dist = dist;
		}
	}

	private static List<Node>[] adjList;
	private static List<Integer> leaf;
	private static List<Integer> maxList;
	private static Node maxNode;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		maxNode = new Node(0, Integer.MIN_VALUE);
		leaf = new ArrayList<>();

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Node>();
		} // 인접리스트 초기화

		for (int i = 1; i <= N; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			for (int j = 1; edge[j] != -1; j += 2) {
				adjList[edge[0]].add(new Node(edge[j], edge[j + 1]));
			}
			if (adjList[edge[0]].size() == 1) {
				leaf.add(edge[0]);
			}
		}

		// leaf Node에서 DFS 하기 해서 다른 Leaf node까지 가자
		// 우선 Leaf node를 알아야겠지?
		boolean[] vis = new boolean[N + 1];
		vis[1] = true;
		dfs(1, vis, 0);
//		System.out.println(maxNode.dest + " " + maxNode.dist);
		Arrays.fill(vis, false);
		vis[maxNode.dest] = true;
		dfs(maxNode.dest, vis, 0);
		System.out.println(maxNode.dist);
	}

	private static void dfs(int cur, boolean[] vis, int dist) {
		// TODO Auto-generated method stub
		if (isLeaf(cur, vis)) {
			if (maxNode.dist < dist) {
				maxNode = new Node(cur, dist);
			}
			return;
		}
		for (Node node : adjList[cur]) {
			if (!vis[node.dest]) {
				vis[node.dest] = true;
				dfs(node.dest, vis, dist + node.dist);
				vis[node.dest] = false;
			}
		}
	}

	private static boolean isLeaf(int cur, boolean[] vis) {
		// TODO Auto-generated method stub
		for (Node node : adjList[cur]) {
			if (!vis[node.dest]) {
				return false;
			}
		}
		return true;
	}
}