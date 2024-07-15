import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			boolean[][] vis = new boolean[N][N];
			int[][] snare = new int[N][N];
			int cnt = 1;

			int row = 0;
			int col = 0;

			snare[row][col] = cnt;
			vis[row][col] = true;

			while (cnt != N * N) {
				while (col + 1 < N && vis[row][col + 1] == false) {
					col++;
					cnt++;
					vis[row][col] = true;
					snare[row][col] = cnt;
				}
				while (row + 1 < N && vis[row + 1][col] == false) {
					row++;
					cnt++;
					vis[row][col] = true;
					snare[row][col] = cnt;
				}
				while (col - 1 >= 0 && vis[row][col - 1] == false) {
					col--;
					cnt++;
					vis[row][col] = true;
					snare[row][col] = cnt;
				}
				while (row - 1 >= 0 && vis[row - 1][col] == false) {
					row--;
					cnt++;
					vis[row][col] = true;
					snare[row][col] = cnt;
				}

			}
			System.out.println("#"+test_case);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(snare[i][j] + " ");
				}
				System.out.println();
			}

		}
	}
}