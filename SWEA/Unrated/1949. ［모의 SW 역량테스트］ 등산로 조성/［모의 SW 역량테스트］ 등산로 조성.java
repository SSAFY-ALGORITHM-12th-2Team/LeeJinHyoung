import java.io.*;
import java.util.stream.Stream;
import java.util.*;

class pos {
	int r;
	int c;

	pos() {

	}

	pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Solution {
	static int dr[] = { 1, 0, -1, 0 };
	static int dc[] = { 0, -1, 0, 1 };

	static int N, K;

	static int max = Integer.MIN_VALUE;

	public static void backtracking(int[][] map, boolean[][] vis, pos cur, int dist, boolean cut) {
		// 이 코드 결과는 맞거나 초과하거나
		max = dist > max ? dist : max;

		vis[cur.r][cur.c] = true;
		for (int dir = 0; dir < 4; dir++) {
			int nr = cur.r + dr[dir];
			int nc = cur.c + dc[dir];
			boolean[][] re_vis = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					re_vis[i][j] = vis[i][j];
				}
			}

			if (nr >= 0 && nc >= 0 && nr < N && nc < N // 범위 계산
					&& map[cur.r][cur.c] > map[nr][nc]) {
				// 빼지 않고 그냥 낮은 방향으로 내려갈 경우
				backtracking(map, re_vis, new pos(nr, nc), dist + 1, cut);

			} else if (nr >= 0 && nc >= 0 && nr < N && nc < N // 범위 계산
					&& re_vis[nr][nc] == false// 방문하지 않은 칸이라면
					&& map[cur.r][cur.c] <= map[nr][nc] // 옆 칸이 크거나 같으며
					&& cut == true // 아직 깎을 수 있고
			) {// 딱 한번만 뺄 수 있고 최소 1에서 최대 K만큼만 뺄 수 있다
				// 그렇다면 K를 뺐는데도 여전히 크다면 이동할 수 없고 그 이하라면 상관 없다
				if (map[cur.r][cur.c] > map[nr][nc] - K) {
					int[][] re_map = new int[N][N];
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							re_map[i][j] = map[i][j];
						}
					}
					for (int c = 1; c <= K; c++) {
						if (re_map[cur.r][cur.c] > re_map[nr][nc] - c) {
							re_map[nr][nc] -= c;
							break;
						}
					}
					backtracking(re_map, re_vis, new pos(nr, nc), dist + 1, false);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int[] tc_input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();// N과 K의 입력

			max = Integer.MIN_VALUE;
			N = tc_input[0];
			K = tc_input[1];

			int[][] map = new int[N][];

			Queue<pos> sp = new LinkedList<>();// 시작점을 잡을 Queue
			for (int i = 0; i < N; i++) {
				map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int j = 0; j < N; j++) {
					if (sp.isEmpty()) {// Queue가 비어있을 경우 하나의 좌표를 넣는다
						sp.offer(new pos(i, j));
					} else if (map[sp.peek().r][sp.peek().c] < map[i][j]) {// Queue peek 보다 값이 클 경우 Queue를 비운 후 값을 넣는다
						sp.clear();
						sp.offer(new pos(i, j));
					} else if (map[sp.peek().r][sp.peek().c] == map[i][j]) {// Queue peek과 값이 같을 경우 Queue에 값을 추가
						sp.offer(new pos(i, j));
					}
				}
			} // 초기 시작점을 잡자
			while (!sp.isEmpty()) {
				int[][] re_map = new int[N][N];
				boolean[][] vis = new boolean[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						re_map[i][j] = map[i][j];
					}
				}
				pos cur = sp.poll();
				backtracking(re_map, vis, cur, 1, true);
			}
			System.out.println("#" + tc + " " + max);
		}
	}
}