import java.util.*;
import java.io.*;

public class Main {
	private static String[][] board;

	private static void base(int row, int col) {
		for (int r = row; r < row + 3; r++) {
			for (int c = col; c < col + 3; c++) {
				if (r == row + 1 && c == col + 1)
					board[r][c] = " ";
				else
					board[r][c] = "*";
			}
		}
	}

	private static void recursive(int N, int r, int c) {
		if (N == 3) {
			base(r, c);
			return;
		}
		int quot = N / 3;
		for (int row = r; row < r + N; row += quot) {
			for (int col = c; col < c + N; col += quot) {
				if (row == r + quot && col == c + quot)
					continue;
				recursive(quot, row, col);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		board = new String[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[i], " ");
		}
//		System.out.println(N);
		recursive(N, 0, 0);
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer.append(board[i][j]);
			}
			answer.append("\n");
		}
		System.out.println(answer);
	}
}