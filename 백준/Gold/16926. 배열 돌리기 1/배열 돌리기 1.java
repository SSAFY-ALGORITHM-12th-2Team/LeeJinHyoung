import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	int r;
	int c;
	int val;

	public pos(int r, int c, int val) {
		super();
		this.r = r;
		this.c = c;
		this.val = val;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = input[0];
		int M = input[1];
		int R = input[2];

		int[][] board = new int[N][M];
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		int r_start = 0;
		int c_start = 0;
		int row_limit = N;
		int col_limit = M;
		while (visit[r_start][c_start] == false) {
			ArrayList<pos> list = new ArrayList<pos>();

			int r = r_start;
			int c = c_start;
			for (c = c_start; c < col_limit - 1; c++) {
				if (visit[r][c] == false) {
					list.add(new pos(r, c, board[r][c]));
					visit[r][c] = true;
				}
			}
			for (r = r_start; r < row_limit - 1; r++) {
				if (visit[r][c] == false) {
					list.add(new pos(r, c, board[r][c]));
					visit[r][c] = true;
				}
			}
			for (; c > c_start; c--) {
				if (visit[r][c] == false) {
					list.add(new pos(r, c, board[r][c]));
					visit[r][c] = true;
				}
			}
			for (; r > r_start; r--) {
				if (visit[r][c] == false) {
					list.add(new pos(r, c, board[r][c]));
					visit[r][c] = true;
				}
			}

			for (int rotate = 0; rotate < R; rotate++) {
				int head_val = list.get(0).val;
				for (int i = 0; i < list.size() - 1; i++) {
					list.get(i).val = list.get(i + 1).val;
				}
				list.get(list.size() - 1).val = head_val;
			}
			for (pos e : list) {
				board[e.r][e.c] = e.val;
			}

			
			col_limit--;
			row_limit--;
			r_start++;
			c_start++;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}