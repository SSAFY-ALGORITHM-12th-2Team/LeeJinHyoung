import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Solution {
	static int N, W, H;
	static int[][] board;
	static int Answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N = input[0];
			W = input[1];
			H = input[2];

			Answer = Integer.MAX_VALUE;
			board = new int[H][W];

			for (int i = 0; i < H; i++) {
				board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			perm(new int[N], 0);
			System.out.println("#" + tc + " " + Answer);
		}
	}

	static int[] dh = { 0, 1, 0, -1 };
	static int[] dw = { 1, 0, -1, 0 };

	private static void bombing(int h, int w, boolean[][] vis, int[][] copyboard, Queue<Point> q) {
		for (int c = 0; c < copyboard[h][w]; c++) {
			for (int d = 0; d < 4; d++) {
				int nh = h + dh[d] * c;
				int nw = w + dw[d] * c;
				if (nh >= 0 && nw >= 0 && nh < H && nw < W && !vis[nh][nw]) {
					vis[nh][nw] = true;
					q.offer(new Point(nh, nw));
					bombing(nh, nw, vis, copyboard, q);
				}
			}
		}
	}

	private static void gravity(int[][] copyboard) {
		// TODO Auto-generated method stub
		for (int c = 0; c < W; c++) {
			for (int r = H - 1; r >= 0; r--) {
				if (copyboard[r][c] == 0) {
					int k = 0;
					while (r - k > 0 && copyboard[r - k][c] == 0) {
						k++;
					}
					int temp = copyboard[r - k][c];
					copyboard[r - k][c] = copyboard[r][c];
					copyboard[r][c] = temp;
				}
			}
		}
	}

	private static void perm(int[] sel, int idx) {
		// TODO Auto-generated method stub
		if (idx == sel.length) {
			int[][] copyboard = new int[H][W];
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					copyboard[i][j] = board[i][j];
				}
			}

			for (int w = 0; w < sel.length; w++) {
				Queue<Point> q = new ArrayDeque<>();
				for (int h = 0; h < H; h++) {// 열 선택후 행을 깊게 들어간다
					if (copyboard[h][sel[w]] >= 1) {
						bombing(h, sel[w], new boolean[H][W], copyboard, q);
						break;
					}
				} // 열마다 블록 내려보내기
				while (!q.isEmpty()) {
					Point p = q.poll();
					copyboard[p.x][p.y] = 0;
				} // 폭탄 터뜨리기
				gravity(copyboard);
			}
			int Answer = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (copyboard[i][j] >=1) {
						Answer++;
					}
				}
			}
			Solution.Answer = Math.min(Answer, Solution.Answer);
			return;
		}
		for (int i = 0; i < W; i++) {
			sel[idx] = i;
			perm(sel, idx + 1);
		}
	}
}