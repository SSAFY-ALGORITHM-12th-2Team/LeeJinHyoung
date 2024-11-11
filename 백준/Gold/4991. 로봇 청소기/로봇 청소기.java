import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {
	static int h, w;
	static String board[][];
	static int[][] marker;
	static int[][] adjMatrix;// 먼지 중심으로

	static int min;

	static class info {
		public info(int dest, int dist) {
			// TODO Auto-generated constructor stub

			this.dest = dest;
			this.dist = dist;
		}

		int dest;
		int dist;
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void bfs(Point start) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		int[][] vis = new int[h][w];
		vis[start.x][start.y] = 1;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.x + dr[d];
				int nc = cur.y + dc[d];
				if (nr >= 0 && nc >= 0 && nr < h && nc < w && vis[nr][nc] == 0 && !board[nr][nc].equals("x")) {
					vis[nr][nc] = vis[cur.x][cur.y] + 1;
					q.offer(new Point(nr, nc));
					if ((board[nr][nc].equals("*") || board[nr][nc].equals("o"))
							&& (adjMatrix[marker[start.x][start.y]][marker[nr][nc]] == 0
									&& adjMatrix[marker[nr][nc]][marker[start.x][start.y]] == 0)) {
						adjMatrix[marker[start.x][start.y]][marker[nr][nc]] = vis[nr][nc] - 1;
						adjMatrix[marker[nr][nc]][marker[start.x][start.y]] = vis[nr][nc] - 1;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		while (input[0] != 0 & input[1] != 0) {
			h = input[1];
			w = input[0];
			board = new String[h][w];
			marker = new int[h][w];
			min = Integer.MAX_VALUE;

			Point robot = null;
			int dustidx = 2;
			List<Point> dustpos = new ArrayList();
			for (int i = 0; i < h; i++) {
				board[i] = br.readLine().split("");
				for (int j = 0; j < w; j++) {
					if (board[i][j].equals("*")) {
						marker[i][j] = dustidx++;
						dustpos.add(new Point(i, j));
					} else if (board[i][j].equals("o")) {
						marker[i][j] = 1;
						robot = new Point(i, j);
					}
				}
			}
			// 1은 청소기, 2는 먼지
			adjMatrix = new int[dustpos.size() + 2][dustpos.size() + 2];// 로봇 위치로 하나 밀리기 때문에
			bfs(robot);
			for (int i = 0; i < dustpos.size(); i++) {
				bfs(dustpos.get(i));
			}
			int[] src = new int[dustpos.size() + 1];
			int[] sel = new int[dustpos.size() + 1];
			boolean[] vis = new boolean[dustpos.size() + 1];
			for (int i = 0; i < src.length; i++) {
				src[i] = i + 1;
			}
			sel[0] = 1;
			vis[0] = true;
			perm(sel, src, vis, 1);

			System.out.println(min == 0 ? -1 : min);

			input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
	}

	private static void perm(int[] sel, int[] src, boolean[] vis, int idx) {
		// TODO Auto-generated method stub
		if (idx == sel.length) {
			int totalDist = 0;
			for (int i = 0; i < sel.length - 1; i++) {
				if (adjMatrix[sel[i]][sel[i + 1]] == 0) {
					min = -1;
					return;
				}
				totalDist += adjMatrix[sel[i]][sel[i + 1]];
			}
			min = Math.min(min, totalDist);
			return;
		}
		for (int i = 0; i < sel.length; i++) {
			if (!vis[i]) {
				vis[i] = true;
				sel[idx] = src[i];
				perm(sel, src, vis, idx + 1);
				vis[i] = false;
			}
		}
	}
}