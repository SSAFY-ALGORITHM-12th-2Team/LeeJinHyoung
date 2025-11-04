import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int N;
	private static char[][] field;

	// 상, 하, 좌, 우
	private static final int[] dr = { -1, 1, 0, 0 };
	private static final int[] dc = { 0, 0, -1, 1 };

	private static class Node {
		int r, c, dir, dist; // dir: 0=세로, 1=가로
		public Node(int r, int c, int dir, int dist) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.dist = dist;
		}
	}

	private static boolean canMove(int r, int c, int dir, int moveDir) {
		for (int i = -1; i <= 1; i++) {
			int nr, nc;
			if (dir == 0) { // 세로
				nr = r + dr[moveDir] + i;
				nc = c + dc[moveDir];
			} else { // 가로
				nr = r + dr[moveDir];
				nc = c + dc[moveDir] + i;
			}
			if (nr < 0 || nc < 0 || nr >= N || nc >= N || field[nr][nc] == '1')
				return false;
		}
		return true;
	}

	private static boolean canRotate(int r, int c) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int nr = r + i, nc = c + j;
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || field[nr][nc] == '1')
					return false;
			}
		}
		return true;
	}

	private static int bfs(Point[] tree, Point[] goal) {
		// 초기 방향
		int dir = (tree[0].x == tree[1].x) ? 1 : 0;
		int goalDir = (goal[0].x == goal[1].x) ? 1 : 0;

		boolean[][][] vis = new boolean[N][N][2];
		Queue<Node> q = new ArrayDeque<>();

		int sr = tree[1].x, sc = tree[1].y;
		int gr = goal[1].x, gc = goal[1].y;

		q.offer(new Node(sr, sc, dir, 0));
		vis[sr][sc][dir] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			// 목표 도달
			if (cur.r == gr && cur.c == gc && cur.dir == goalDir)
				return cur.dist;

			// 4방 이동
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (!canMove(cur.r, cur.c, cur.dir, d)) continue;
				if (vis[nr][nc][cur.dir]) continue;

				vis[nr][nc][cur.dir] = true;
				q.offer(new Node(nr, nc, cur.dir, cur.dist + 1));
			}

			// 회전
			if (canRotate(cur.r, cur.c)) {
				int nd = cur.dir ^ 1; // 0->1, 1->0
				if (!vis[cur.r][cur.c][nd]) {
					vis[cur.r][cur.c][nd] = true;
					q.offer(new Node(cur.r, cur.c, nd, cur.dist + 1));
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		field = new char[N][N];

		Point[] tree = new Point[3];
		Point[] goal = new Point[3];
		int bIdx = 0, eIdx = 0;

		for (int i = 0; i < N; i++) {
			field[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (field[i][j] == 'B') tree[bIdx++] = new Point(i, j);
				else if (field[i][j] == 'E') goal[eIdx++] = new Point(i, j);
			}
		}

		System.out.println(bfs(tree, goal));
	}
}
