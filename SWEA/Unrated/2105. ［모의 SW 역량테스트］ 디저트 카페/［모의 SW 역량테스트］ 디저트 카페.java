import java.util.*;
import java.util.stream.Stream;
import java.awt.*;
import java.io.*;

public class Solution {
	static int dr[] = { 1, 1, -1, -1, };
	static int dc[] = { 1, -1, -1, 1 };

	private static int maxDepth;
	private static int N;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			maxDepth = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			} // 입력 완료

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == 0 && j == 0)
						continue;
					else if (i == 0 && j == N - 1)
						continue;
					else if (i == N - 1 && j == 0)
						continue;
					else if (i == N - 1 && j == N - 1)
						continue;// 각 꼭짓점은 불가하므로

					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];

						boolean[] dessert = new boolean[101];
						boolean[][] vis = new boolean[N][N];

						if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
							vis[nr][nc] = true;
							dessert[map[nr][nc]] = true;
							makeSquare(new Point(i, j), new Point(nr, nc), vis, dessert, dir, 1, 1);
						}
					}
				}
			}
			if (maxDepth != Integer.MIN_VALUE) {
				System.out.println("#" + tc + " " + maxDepth);
			} else {
				System.out.println("#" + tc + " -1");
			}
		}
	}

	private static void makeSquare(Point start, Point cur, boolean[][] vis, boolean[] dessert, int dir, int dirCnt,
			int depth) {
		// TODO Auto-generated method stub
		if ((cur.x == start.x && cur.y == start.y) && dirCnt == 4) {
			maxDepth = Math.max(maxDepth, depth);
			return;
		}

		for (int i = 0; i <= 1; i++) {
			int nr = cur.x + dr[(dir + i) % 4];
			int nc = cur.y + dc[(dir + i) % 4];// 직진하거나 우회전하거나

			if (nr >= 0 && nc >= 0 && nr < N && nc < N) {// 범위 제한
				if (vis[nr][nc] == false && dessert[map[nr][nc]] == false) {
					vis[nr][nc] = true;
					dessert[map[nr][nc]] = true;
					makeSquare(start, new Point(nr, nc), vis, dessert, dir + i, dirCnt + i, depth + 1);
					vis[nr][nc] = false;
					dessert[map[nr][nc]] = false;
				}
			}
		}
	}
}