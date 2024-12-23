import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int n, m;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

	private static int[][] board;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		n = input[0];
		m = input[1];

		board = new int[n][m];
		int goalRow = 0;
		int goalCol = 0;

		for (int i = 0; i < n; i++) {
			board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 2) {
					goalRow = i;
					goalCol = j;
				}
			}
		}
		int[][] answer = bfs(goalRow, goalCol);
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[i].length; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int[][] bfs(int goalRow, int goalCol) {
		// TODO Auto-generated method stub
		Queue<Point> q = new ArrayDeque();
		q.offer(new Point(goalRow, goalCol));
		int[][] cost = new int[n][m];
		cost[goalRow][goalCol] = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nr = dr[dir] + cur.x;
				int nc = dc[dir] + cur.y;

				if (nr == goalRow & nc == goalCol) {
					continue;
				} else if (nr >= 0 & nc >= 0 && nr < n && nc < m && cost[nr][nc] == 0 && board[nr][nc] != 0) {
					cost[nr][nc] = cost[cur.x][cur.y] + 1;
					q.offer(new Point(nr, nc));
				}
			}
		}
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[i].length; j++) {
				if (i == goalRow && j == goalCol) {
					continue;
				} else if (cost[i][j] == 0 && board[i][j] > 0) {
					cost[i][j] = -1;
				} else if (cost[i][j] == 0 && board[i][j] == 0) {
					cost[i][j] = 0;
				}
			}
		}
		return cost;
	}
}