import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static class Node implements Comparable<Node> {
		int to;
		int weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int M = input[1];

		ArrayList<Node>[] vil = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			vil[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < M; i++) {
			int[] Edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			vil[Edge[0]].add(new Node(Edge[1], Edge[2]));
			vil[Edge[1]].add(new Node(Edge[0], Edge[2]));// 무방향이므로
		}

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(1, 0));
		boolean[] vis = new boolean[N + 1];
		int cost = 0;
		int[] costList = new int[N + 1];
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (vis[cur.to])
				continue;
			cost += cur.weight;
			costList[cur.to] = cur.weight;
			vis[cur.to] = true;

			for (int i = 0; i < vil[cur.to].size(); i++) {
				if (!vis[vil[cur.to].get(i).to])
					pq.offer(vil[cur.to].get(i));
			}
		}
//		for (int e : costList) {
//			System.out.print(e + " ");
//		}
//		System.out.println();
		Arrays.sort(costList);
		int sum=0;
		for(int i=0;i<costList.length-1;i++) {
			sum+=costList[i];
		}
		System.out.println(sum);
	}
}