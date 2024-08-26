import java.util.*;
import java.awt.Point;
import java.io.*;

public class Solution {
	private static int min;
	private static String map[][];
	private static int bomb[][];
	static int N;

	static int[] dr = { 1, 0, -1, 0, 1, 1, -1, -1 };
	static int[] dc = { 0, 1, 0, -1, 1, -1, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new String[N][N];
			bomb = new int[N][N];
			min = Integer.MAX_VALUE;
			boolean[][] vis = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					if (map[i][j].equals("*"))
						bomb[i][j] = -1;
				}
			} // 입력 완료

			int cnt = 0;
			for (int k = 0; k <= 8; k++) {
				if (fill(bomb) == true) {
					break;
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j].equals(".") && bombNum(map, i, j) == k && vis[i][j] == false) {
							bfs(i, j, vis);
							cnt++;
							//System.out.println(i + " " + j);
						}
					}
				}
			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(bomb[i][j]);
//				}
//				System.out.println();
//			}
			bw.write(String.valueOf("#" + tc + " " + cnt) + "\n");
			bw.flush();
		}
	}

	private static void bfs(int r, int c, boolean[][] vis) {
		// TODO Auto-generated method stub
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		vis[r][c] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			bomb[cur.x][cur.y] = bombNum(map, cur.x, cur.y);
			if (bomb[cur.x][cur.y] == 0) {
				for (int dir = 0; dir < 8; dir++) {
					int nr = cur.x + dr[dir];
					int nc = cur.y + dc[dir];

					if (nr >= 0 && nc >= 0 && nr < N && nc < N && !vis[nr][nc]) {
						vis[nr][nc] = true;
						q.offer(new Point(nr, nc));
					}
				}
			}
		}
	}

	private static boolean fill(int[][] bomb) {// 모든 칸이 다 찼는지 검사하는 함수
		// TODO Auto-generated method stub
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (bomb[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	private static int bombNum(String[][] map, int r, int c) {// 내 주변의 폭탄 갯수 검사 함수
		// TODO Auto-generated method stub
		int bombNum = 0;
		for (int dir = 0; dir < 8; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr >= 0 && nc >= 0 && nr < N && nc < N && map[nr][nc].equals("*")) {
				bombNum++;
			}
		}
		return bombNum;
	}
}