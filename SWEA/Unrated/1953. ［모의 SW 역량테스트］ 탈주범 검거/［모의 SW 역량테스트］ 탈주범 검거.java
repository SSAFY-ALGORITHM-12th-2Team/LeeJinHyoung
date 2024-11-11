import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Solution {
	static int[][] dr = { { 0, 1, 0, -1 }, { 1, -1 }, { 0, 0 }, { -1, 0 }, { 1, 0 }, { 1, 0 }, { -1, 0 } };
	static int[][] dc = { { 1, 0, -1, 0 }, { 0, 0 }, { -1, 1 }, { 0, 1 }, { 0, 1 }, { 0, -1 }, { 0, -1 } };

	static int N, M, L;
	static int[][] road;

	public static int bfs(int R, int C) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(R, C));
		int[][] time = new int[N][M];
		time[R][C] = 1;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (time[cur.x][cur.y] >= L) {
				break;
			}
			for (int dir = 0; dir < dr[road[cur.x][cur.y] - 1].length; dir++) {// 파이프 종류가 제공할 수 있는 방향의 가짓수
				int nr = cur.x + dr[road[cur.x][cur.y] - 1][dir];
				int nc = cur.y + dc[road[cur.x][cur.y] - 1][dir];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && time[nr][nc] == 0 && road[nr][nc] > 0) {
					int[] next_dr = dr[road[nr][nc] - 1];
					int[] next_dc = dc[road[nr][nc] - 1];

					boolean canCome = false;
					for (int next = 0; next < next_dr.length; next++) {
						if (nr + next_dr[next] == cur.x && nc + next_dc[next] == cur.y) {
							canCome = true;
						}
					}
					if (!canCome) {
						continue;
					}

					time[nr][nc] = time[cur.x][cur.y] + 1;
					q.offer(new Point(nr, nc));
				}
			}
		}

		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				total += time[i][j] > 0 ? 1 : 0;
			}
		}
		return total;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			N = input[0];
			M = input[1];
			int R = input[2];
			int C = input[3];
			L = input[4];

			road = new int[N][M];
			for (int i = 0; i < N; i++) {
				road[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			System.out.println("#" + tc + " " + bfs(R, C));
		}
	}
}