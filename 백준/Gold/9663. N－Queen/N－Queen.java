import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int[] dr = { 0, 1, 0, -1, 1, 1, -1, -1 };
	private static int[] dc = { 1, 0, -1, 0, 1, -1, -1, 1 };

	private static int answer;

	private static void func(int[][] used, int row, int col) {
		if (row == N - 1) {
			answer++;
			return;
		}

		for (int dir = 0; dir < 8; dir++) {
			for (int n = 1; n <= N; n++) {
				int nr = row + dr[dir] * n;
				int nc = col + dc[dir] * n;
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					break;
				}
				used[nr][nc]++;
			}
		}
		for (int c = 0; c < N; c++) {
			if (used[row + 1][c] == 0) {
				func(used, row + 1, c);
			}
		}
		for (int dir = 0; dir < 8; dir++) {
			for (int n = 1; n <= N; n++) {
				int nr = row + dr[dir] * n;
				int nc = col + dc[dir] * n;
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					break;
				}
				used[nr][nc]--;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		answer = 0;
		int[][] used = new int[N][N];
		for (int i = 0; i < N; i++) {
			func(used, 0, i);
		}
		System.out.println(answer);
	}
}