import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int N, M, earth[][];
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

//	1. 녹인다.
	private static void melt(List<Point> gracier) {
		int[][] newEarth = new int[N][M];

		for (int i = 0; i < gracier.size(); i++) {
//			이미 녹은 빙산은 빙산 리스트에서 없애 최적화한다.
			Point p = gracier.get(i);
			if (earth[p.x][p.y] == 0) {
				gracier.remove(i);
				i--;
				continue;
			}

//			하나의 빙산이 접한 바다 수를 세 빙녹인다.
			int sea = 0;
			for (int dir = 0; dir < 4; dir++) {
				int nr = p.x + dr[dir];
				int nc = p.y + dc[dir];

				if (earth[nr][nc] == 0) {
					sea++;
				}
			}
			if (earth[p.x][p.y] - sea < 0)
				newEarth[p.x][p.y] = 0;
			else
				newEarth[p.x][p.y] = earth[p.x][p.y] - sea;
		}
		earth = newEarth;
	}

//	2. 덩어리수를 확인한다.
	private static int chunkNumber() {
		boolean[][] vis = new boolean[N][M];

		int n = 0;
		int sea = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!vis[r][c] && earth[r][c] > 0) {
					n += 1;
					if (n >= 2)
						return n;
					bfs(r, c, vis);
				} else if (earth[r][c] == 0) {
					sea++;
				}
			}
		}
		if (sea == N * M) {
			return Integer.MAX_VALUE;
		}
		return n;
	}

	private static void bfs(int r, int c, boolean[][] vis) {
		// TODO Auto-generated method stub
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (vis[cur.x][cur.y])
				continue;

			vis[cur.x][cur.y] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || vis[nr][nc] || earth[nr][nc] == 0)
					continue;

				q.offer(new Point(nr, nc));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = input[0];
		M = input[1];

//		입력 끝
		earth = new int[N][M];
		List<Point> gracier = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			earth[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < M; j++) {
				if (earth[i][j] > 0)
					gracier.add(new Point(i, j));
			}
		}

//		구해야하는 것: 두 개의 덩어리가 되는 시간
//		순서 : 
//		1. 녹인다.
//		2. 덩어리 수 확인한다.
//		3. 반복
		int time = 0;
		int chunk = 0;
		while ((chunk = chunkNumber()) < 2) {
			melt(gracier);
			time++;
		}
		System.out.println(chunk == Integer.MAX_VALUE ? 0 : time);
	}
}