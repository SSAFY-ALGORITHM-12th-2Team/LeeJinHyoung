import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {
	static int[][] map;

	static int cheese;

	static int N, M;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = input[0];
		M = input[1];

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					cheese++;
				}
			}
		} // 입력 끝

		// 어떻게 밀봉부분 찾아낼래?
		// 밀봉되지 않았다면 bfs로 했을 때 갇히곘지
		// 밀봉 부분 찾는 함수 찾고 해당 부분 map[r][c] =2로 맵핑

		int time = 0;
		while (cheese > 0) {
			boolean[][] isPacked = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 2) {
						map[r][c] = 0;
					}
				}
			}
			L: for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 0 && !isPacked[r][c]) {
						packed(r, c, isPacked);
						break L;
					}
				}
			} // 그럼 isPacked에 방문한 공기층이 나올 것이다.

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 0 && !isPacked[r][c]) {
						map[r][c] = 2;
					}
				}
			}

			boolean[][] vis = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1 && !vis[r][c]) {
						bfs(r, c, vis);
					}
				}
			}
			//print();
			time++;
		}
		System.out.println(time);
	}

	public static void print() {// 디버깅 용 프린트
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}

	private static void packed(int r, int c, boolean[][] isPacked) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		isPacked[r][c] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.x + dr[d];
				int nc = cur.y + dc[d];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && !isPacked[nr][nc] && map[nr][nc] == 0) {
					q.offer(new Point(nr, nc));
					isPacked[nr][nc] = true;
				}
			}
		}
	}

	private static void bfs(int r, int c, boolean[][] vis) {// bfs해서 녹일 치즈 찾자
		// TODO Auto-generated method stub
		Queue<Point> q = new ArrayDeque<>();
		vis[r][c] = true;
		q.offer(new Point(r, c));

		Queue<Point> melt = new ArrayDeque<>();// 여기서 녹이는 것까지 다할 예정이다.

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int empty = 0;
			for (int d = 0; d < 4; d++) {
				int nr = cur.x + dr[d];
				int nc = cur.y + dc[d];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 1 && !vis[nr][nc]) {
					q.offer(new Point(nr, nc));
					vis[nr][nc] = true;
				} else if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0) {// 빈칸이 몇 개인가 카운트
					empty++;
				}
			}
			if (empty >= 2) {
				melt.add(cur);
			}
		}

		while (!melt.isEmpty()) {
			Point cur = melt.poll();
			cheese--;
			map[cur.x][cur.y] = 0;
		}
	}
}