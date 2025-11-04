import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int[] dr = { -2, -2, 2, 2, -1, 1, -1, 1 };
	private static int[] dc = { -1, 1, -1, 1, -2, -2, 2, 2 };

	private static int bfs(int length, int[] knight, int[] target) {
		int[][] movement = new int[length][length];
		boolean[][] vis = new boolean[length][length];

		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(knight[0], knight[1]));
		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			if (vis[cur.x][cur.y])
				continue;
			vis[cur.x][cur.y] = true;

			for (int dir = 0; dir < 8; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if (nr < 0 || nc < 0 || nr >= length || nc >= length || vis[nr][nc])
					continue;

				movement[nr][nc] = movement[cur.x][cur.y] + 1;
				queue.offer(new Point(nr, nc));
			}
		}
		return movement[target[0]][target[1]];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= N; tc++) {
			int length = Integer.parseInt(br.readLine());

			int[] knight = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			System.out.println(bfs(length, knight, target));
		}
	}
}