import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static List<Integer> answer;
	private static int N, apartment[][];

	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

	private static int bfs(Point point, boolean[][] vis) {
		Queue<Point> q = new ArrayDeque<Point>();
		q.offer(point);

		int div = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (vis[cur.x][cur.y])
				continue;
			div++;
			vis[cur.x][cur.y] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N || vis[nr][nc] || apartment[nr][nc] == 0)
					continue;

				q.offer(new Point(nr, nc));
			}
		}
		return div;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		apartment = new int[N][];
		for (int i = 0; i < N; i++) {
			apartment[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		boolean[][] vis = new boolean[N][N];

		answer = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (apartment[i][j] == 1 && !vis[i][j]) {
					answer.add(bfs(new Point(i, j), vis));
				}
			}
		}
//		빠른 출력
		StringBuilder print = new StringBuilder();
		Collections.sort(answer);
		print.append(answer.size() + "\n");
		for (int i : answer)
			print.append(i + "\n");
		System.out.println(print);
	}
}