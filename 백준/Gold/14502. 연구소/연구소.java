import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	int r;
	int c;

	pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	private static int N, M;
	private static int[][] lab;
	private static boolean[] vis;
	private static Queue<pos> q = new ArrayDeque<>();

	private static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = input[0];
		M = input[1];

		lab = new int[N][M];
		vis = new boolean[N * M];

		for (int i = 0; i < N; i++) {
			lab[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < M; j++) {
				if (lab[i][j] == 1 || lab[i][j] == 2)
					vis[i * M + j] = true;
			}
		}

		combi(new pos[3], vis, 0, 0);
		System.out.println(max);
	}

	static // 조합의 개수는 최악일 때 41502개인데 이정도면 할만 하지 않나?
	// 이후 BFS로 채우면서..?
	int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void combi(pos[] ans, boolean[] vis, int cnt, int start) {
		// TODO Auto-generated method stub
		if (cnt == 3) {
			// 벽을 세울 위치를 뽑아냈다 여기서부터는 바이러스의 BFS 진행
			boolean[] visCopy = Arrays.copyOf(vis, vis.length);

			for (int i = 0; i < 3; i++) {
				visCopy[ans[i].r * M + ans[i].c] = true;
			} // 벽 막기

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (lab[i][j] == 2)
						q.offer(new pos(i, j));
				}
			} // 바이러스 전파
			while (!q.isEmpty()) {
				pos cur = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (nr >= 0 && nc >= 0 && nr < N && nc < M && visCopy[nr * M + nc] == false) {
						visCopy[nr * M + nc] = true;
						q.offer(new pos(nr, nc));
					}
				}
			}
			int cmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visCopy[i * M + j] == false) {
						cmp++;
					}
				}
			}
			max = Math.max(cmp, max);
			return;
		}
		for (int l = start; l < N * M; l++) {
			if (vis[l] == false) {
				vis[l] = true;
				ans[cnt] = new pos(l / M, l % M);
				combi(ans, vis, cnt + 1, l + 1);
				vis[l] = false;
			}
		}
	}
}