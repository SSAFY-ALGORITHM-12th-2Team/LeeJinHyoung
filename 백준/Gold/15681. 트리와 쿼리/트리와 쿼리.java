import java.util.*;
import java.io.*;

public class Main {
	private static int N/* 정점의 갯수 */, R/* 간선의 갯수 */, Q/* 쿼리의 갯수 */;
	private static List<Integer>[] adjList;

	private static int[] size;

	// 우선 tree를 만드는 과정이 필요 부모로 올라가는 간선을 잘라낸다
	private static void makeTree(int cur, int parent) {
		if (adjList[cur].contains(parent)) {
			adjList[cur].remove(adjList[cur].indexOf(parent));
		}
		for (int node : adjList[cur]) {
			makeTree(node, cur);
		}
	}

	private static void countSubtreeNodes(boolean[] vis, int currentNode) {
		if (!vis[currentNode]) {
			vis[currentNode] = true;
			size[currentNode] = 1;
			for (int Node : adjList[currentNode]) {
				countSubtreeNodes(vis, Node);
				size[currentNode] += size[Node];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] NRQ = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = NRQ[0];
		R = NRQ[1];
		Q = NRQ[2];

		adjList = new ArrayList[N + 1];
		size = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList();
		} // 그래프 구성

		for (int i = 0; i < N - 1; i++) {
			int[] Edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[Edge[0]].add(Edge[1]);
			adjList[Edge[1]].add(Edge[0]);// 무방향 그래프 입력
		}

		
		int[] query = new int[Q];
		for (int i = 0; i < Q; i++) {
			query[i] = Integer.parseInt(br.readLine());
		}
		makeTree(R, -1);// 우선 트리를 만족해야하려면 사이클을 삭제해야 한다
		countSubtreeNodes(new boolean[N + 1], R);
		for (int i : query) {
			System.out.println(size[i]);
		}
	}
}