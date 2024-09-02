import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class Node {
	int to;
	int weight;

	protected Node(int to, int weight) {
		super();
		this.to = to;
		this.weight = weight;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int V = input[0];
		int E = input[1];

		ArrayList<Node>[] graph = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList();
		}

		for (int i = 0; i < E; i++) {
			int[] Edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph[Edge[0]].add(new Node(Edge[1], Edge[2]));
			graph[Edge[1]].add(new Node(Edge[0], Edge[2]));
		}

		int cost = 0, cnt = 0;

		// 프림으로 풀어보자
		boolean[] vis = new boolean[V + 1];// 방문 배열
		int[] minEdge = new int[V + 1];// 간선의 최소 가중치

		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;

		for (int i = 1; i <= V; i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE;

			for (int j = 1; j <= V; j++) {
				if (!vis[j] && min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}

			if (minVertex == -1) {// 선택한 정점이 없다는 의미이므로
				break;
			}
			vis[minVertex] = true;// 방문 처리하고
			cost += min;// 비용 추가한다

			for (int j = 0; j < graph[minVertex].size(); j++) {// 최소 정점으로 선택한 정점에서
				if (!vis[graph[minVertex].get(j).to] // 아직 방문하지 않았고 그 가중치가 0이 아니며
						&& minEdge[graph[minVertex].get(j).to] > graph[minVertex].get(j).weight) {
					// 트리에 연결된 가중치보다 더 적은 가중치로 연결할 수 있으면 가중치 갱신
					minEdge[graph[minVertex].get(j).to] = graph[minVertex].get(j).weight;
				}
			}

			/* 디버깅용 아래는 */
//			System.out.println(cost + " " + min + " " + minVertex);
//			for (boolean b : vis) {
//				System.out.print(b + " ");
//			}
//			System.out.println();
//			for (int m : minEdge) {
//				System.out.print(m + " ");
//			}
//			System.out.println();
		}
		System.out.println(cost);
	}
}