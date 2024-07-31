import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int[][] sudoku;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		sudoku = new int[9][9];
		for (int tc = 1; tc <= T; tc++) {
			boolean flag = true;
			for (int i = 0; i < 9; i++) {
				sudoku[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			} // 입력 끝

			for (int i = 0; i < 9; i++) {// 행체크
				boolean[] vis = new boolean[9];
				for (int j = 0; j < 9; j++) {
					if (vis[sudoku[i][j] - 1] == true) {
						flag = false;
					}
					vis[sudoku[i][j] - 1] = true;
				}
				for (boolean x : vis) {
					if (x == false) {
						flag = false;
					}
				}
			}
			if (flag == false) {
				System.out.println("#" + tc + " " + 0);
				continue;
			}

			for (int i = 0; i < 9; i++) {// 열체크
				boolean[] vis = new boolean[9];
				for (int j = 0; j < 9; j++) {
					if (vis[sudoku[j][i] - 1] == true) {
						flag = false;
					}
					vis[sudoku[j][i] - 1] = true;
				}
				for (boolean x : vis) {
					if (x == false) {
						flag = false;
					}
				}
			}
			if (flag == false) {
				System.out.println("#" + tc + " " + 0);
				continue;
			}

			for (int i = 0; i < 9; i += 3) {
				for (int j = 0; j < 9; j += 3) {
					boolean[] vis = new boolean[9];
					for (int row = 0; row < i + 3; row++) {
						for (int col = 0; col < j + 3; col++) {

							vis[sudoku[row][col] - 1] = true;
						}
					}
					for(boolean v:vis) {
						if(v==false) {
							flag=false;
						}
					}
				}
			}
			if (flag == false) {
				System.out.println("#" + tc + " " + 0);
				continue;
			} else
				System.out.println("#" + tc + " " + 1);
		}
	}
}