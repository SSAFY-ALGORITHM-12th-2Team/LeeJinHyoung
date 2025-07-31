import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int M = input[1];

		String[][] campus = new String[N][M];
		Point doyeon = new Point();
		for (int i = 0; i < N; i++) {
			campus[i] = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				if (campus[i][j].equals("I")) {
					doyeon.x = i;
					doyeon.y = j;
				}
			}
		}
		int answer = 0;

		Queue<Point> q = new ArrayDeque<>();
		boolean[][] vis = new boolean[N][M];
		q.offer(doyeon);
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (vis[cur.x][cur.y])
				continue;

			vis[cur.x][cur.y] = true;
			if (campus[cur.x][cur.y].equals("P")) {
				answer++;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || vis[nr][nc] || campus[nr][nc].equals("X")) {
					continue;
				}
				q.offer(new Point(nr, nc));
			}
		}
		System.out.println(answer == 0 ? "TT" : answer);
	}
}