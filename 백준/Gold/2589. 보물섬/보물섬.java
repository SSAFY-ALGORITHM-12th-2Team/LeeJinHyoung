import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {
	static String[][] map;
	static int h, w;
	static int max;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		h = input[0];
		w = input[1];

		map = new String[h][w];
		for (int i = 0; i < h; i++) {
			map[i] = br.readLine().split("");
		}
		max = Integer.MIN_VALUE;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j].equals("L")) {
					bfs(i, j);
				}
			}
		}
		System.out.println(max-1);
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void bfs(int row, int col) {
		// TODO Auto-generated method stub
		Queue<Point> q = new ArrayDeque<>();
		int[][] vis = new int[h][w];
		q.offer(new Point(row, col));
		vis[row][col] = 1;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if (nr >= 0 && nc >= 0 && nr < h && nc < w && vis[nr][nc] == 0 & map[nr][nc].equals("L")) {
					vis[nr][nc] = vis[cur.x][cur.y] + 1;
					q.offer(new Point(nr, nc));
					max = Math.max(max, vis[nr][nc]);
				}
			}
		}
	}
}