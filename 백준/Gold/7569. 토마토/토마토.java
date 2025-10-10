import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int H, N, M, board[][][];

	private static int[] dm = { 0, 0, 0, 0, -1, 1 };
	private static int[] dn = { 0, 0, -1, 1, 0, 0 };
	private static int[] dh = { -1, 1, 0, 0, 0, 0 };

	private static class Point {
		int m;
		int n;
		int h;
		int time;

		public Point(int m, int n, int h, int time) {
			super();
			this.m = m;
			this.n = n;
			this.h = h;
			this.time = time;
		}
	}

	private static int bfs(Queue<Point> tomatoes) {
		Queue<Point> q = new ArrayDeque<>();
		q.addAll(tomatoes);

		boolean[][][] vis = new boolean[H][N][M];

		int answer = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (vis[cur.h][cur.n][cur.m])
				continue;

			answer = cur.time;
			board[cur.h][cur.n][cur.m] = 1;
			vis[cur.h][cur.n][cur.m] = true;

			for (int direction = 0; direction < 6; direction++) {
				int nm = dm[direction] + cur.m;
				int nn = dn[direction] + cur.n;
				int nh = dh[direction] + cur.h;

				if (nm < 0 || nm >= M || nn < 0 || nn >= N || nh < 0 || nh >= H || vis[nh][nn][nm])
					continue;
				if (board[nh][nn][nm] == -1)
					continue;

				q.offer(new Point(nm, nn, nh, cur.time + 1));
			}
		}
		return answer;
	}

	private static boolean check() {
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (board[h][n][m] == 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		M = input[0];
		N = input[1];
		H = input[2];

		board = new int[H][N][M];
		Queue<Point> tomatoes = new ArrayDeque<>();

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				board[h][n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int m = 0; m < M; m++) {
					if (board[h][n][m] == 1) {
						tomatoes.offer(new Point(m, n, h, 0));
					}
				}
			}
		}

		int answer = bfs(tomatoes);
		System.out.println(check() ? answer : -1);
	}
}