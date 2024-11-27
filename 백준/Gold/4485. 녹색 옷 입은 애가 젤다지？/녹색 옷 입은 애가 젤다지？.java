import java.util.*;
import java.util.stream.Stream;

import java.awt.Point;
import java.io.*;

public class Main {
	static int N;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static class Node implements Comparable<Node> {
		Point point;
		int score;

		public Node(Point point, int score) {
			super();
			this.point = point;
			this.score = score;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.score - o.score;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int count = 1;
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			int[][] table = new int[N][N];
			for (int i = 0; i < N; i++) {
				table[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			System.out.println("Problem " + count++ + ": " + Dijkstra(table));
		}
	}

	private static int Dijkstra(int[][] table) {
		// TODO Auto-generated method stub
		int[][] coin = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(coin[i], Integer.MAX_VALUE);
		}
		boolean[][] vis = new boolean[N][N];

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(new Point(0, 0), table[0][0]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			Point curPoint = cur.point;
			int curScore = cur.score;

			if (vis[curPoint.x][curPoint.y]) {
				continue;
			}
			coin[curPoint.x][curPoint.y] = curScore;
			vis[curPoint.x][curPoint.y] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nr = curPoint.x + dr[dir];
				int nc = curPoint.y + dc[dir];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N && coin[nr][nc] > curScore + table[nr][nc]) {
					pq.offer(new Node(new Point(nr, nc), curScore + table[nr][nc]));
				}
			}
		}
		return coin[N - 1][N - 1];
	}
}
