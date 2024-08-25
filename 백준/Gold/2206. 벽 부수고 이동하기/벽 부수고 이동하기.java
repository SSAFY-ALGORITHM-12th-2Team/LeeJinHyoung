import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = input[0];
		int M = input[1];
		int[][] map = new int[N][M];
		int[][][] dist = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		Queue<Point> q = new LinkedList<>();
		dist[0][0][0] = 1;
		q.add(new Point(0, 0));

		while (!q.isEmpty()) {

			Point cur = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if (map[nr][nc] == 0 && dist[cur.x][cur.y][0] > 0 && dist[nr][nc][0] == 0) {// 벽 부술 수 있고 현
						dist[nr][nc][0] = dist[cur.x][cur.y][0] + 1;
						q.offer(new Point(nr, nc));
					} else if (map[nr][nc] == 1 && dist[cur.x][cur.y][0] != 0) {
						dist[nr][nc][1] = dist[cur.x][cur.y][0] + 1;
						q.offer(new Point(nr, nc));
					} else if (map[nr][nc] == 0 && dist[cur.x][cur.y][1] != 0 && dist[nr][nc][1] == 0) {
						dist[nr][nc][1] = dist[cur.x][cur.y][1] + 1;
						q.offer(new Point(nr, nc));
					}
				}
			}
		}
		int one = dist[N - 1][M - 1][0];
		int two = dist[N - 1][M - 1][1];

		if (one == 0 && two != 0) {
			System.out.println(two);
		} else if (one != 0 && two == 0) {
			System.out.println(one);
		} else if (one != 0 && two != 0) {
			System.out.println(one < two ? one : two);
		} else
			System.out.println(-1);
	}
}