import java.util.*;
import java.awt.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = input[0];
			int M = input[1];

			String[][] map = new String[N][M];
			int[][] dist = new int[N][M];
			Queue<Point> water = new ArrayDeque();
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().split("");
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				for (int j = 0; j < M; j++) {
					if (map[i][j].equals("W")) {
						water.offer(new Point(i, j));
						dist[i][j] = 0;
					}
				}
			}
			while (!water.isEmpty()) {
				Point cur = water.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.x + dr[dir];
					int nc = cur.y + dc[dir];
					if (nr >= 0 && nc >= 0 && nr < N && nc < M && dist[nr][nc] > dist[cur.x][cur.y] + 1) {
						dist[nr][nc] = dist[cur.x][cur.y] + 1;
						water.offer(new Point(nr, nc));
					}
				}
			}
			int sum=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					sum+=dist[i][j];
				}
			}
			System.out.println("#" + tc + " " + sum);
		}

	}

}
