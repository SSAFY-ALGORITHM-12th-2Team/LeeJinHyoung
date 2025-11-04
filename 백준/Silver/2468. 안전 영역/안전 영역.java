import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int N, field[][];
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

	private static void bfs(Point start, boolean[][] vis, int depth) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (vis[cur.x][cur.y])
				continue;
			vis[cur.x][cur.y] = true;
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || vis[nr][nc] || field[nr][nc] < depth)
					continue;
				q.offer(new Point(nr, nc));
			}
		}
	}

	private static int solve(int depth) {
		boolean[][] vis = new boolean[N][N];
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (vis[i][j] || field[i][j] < depth)
					continue;
				bfs(new Point(i, j), vis, depth);
				answer++;
			}
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		field = new int[N][N];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			field[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < N; j++) {
				max = Math.max(max, field[i][j]);
			}
		}
		int answer = 0;
		for (int depth = 0; depth <= max; depth++) {
			int s = solve(depth);
			answer = Math.max(answer, s);
		}
		System.out.println(answer);
	}
}