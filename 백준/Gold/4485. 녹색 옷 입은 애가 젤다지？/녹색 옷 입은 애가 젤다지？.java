import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	int r;
	int c;
	int cost;

	protected pos(int r, int c, int cost) {
		super();
		this.r = r;
		this.c = c;
		this.cost = cost;
	}
}

public class Main {
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = -1;
		int p_cnt = 1;
		while ((N = Integer.parseInt(br.readLine())) != 0) {

			int[][] cave = new int[N][N];
			for (int i = 0; i < N; i++) {
				cave[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			Queue<pos> q = new LinkedList<>();
			q.add(new pos(0, 0, cave[0][0]));
			dist[0][0] = cave[0][0];

			while (!q.isEmpty()) {
				pos cur = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
						if (cave[nr][nc] + cur.cost < dist[nr][nc]) {
							dist[nr][nc] = cave[nr][nc] + cur.cost;
							q.offer((new pos(nr, nc, cur.cost + cave[nr][nc])));
						}
					}
				}
			}
			System.out.println("Problem " + p_cnt + ": " + dist[N - 1][N - 1]);
			p_cnt++;
		}
	}
}