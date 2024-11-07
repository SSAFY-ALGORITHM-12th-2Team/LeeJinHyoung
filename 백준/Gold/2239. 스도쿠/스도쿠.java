import java.util.*;
import java.util.stream.Stream;

import javax.swing.InputMap;

import java.awt.Point;
import java.io.*;

public class Main {
	static int[][] sudoku;

	public static void squareCheck(int row, int col, boolean[] used) {
		for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
			for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
				if (sudoku[i][j] == 0) {
					continue;
				} else {
					used[sudoku[i][j] - 1] = true;
				}
			}
		}
	}

	public static void rowCheck(int row, int col, boolean[] used) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][col] == 0) {
				continue;
			} else {
				used[sudoku[i][col] - 1] = true;
			}
		}
	}

	public static void colCheck(int row, int col, boolean[] used) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[row][i] == 0) {
				continue;
			} else {
				used[sudoku[row][i] - 1] = true;
			}
		}
	}

	public static void backtracking(List<Point> zero, int idx) {
		if (zero.size() == idx) {
			for (int i = 0; i < sudoku.length; i++) {
				for (int j = 0; j < sudoku[i].length; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		boolean[] used = new boolean[9];
		colCheck(zero.get(idx).x, zero.get(idx).y, used);
		rowCheck(zero.get(idx).x, zero.get(idx).y, used);
		squareCheck(zero.get(idx).x, zero.get(idx).y, used);

		for (int i = 0; i < used.length; i++) {
			if (!used[i]) {
				sudoku[zero.get(idx).x][zero.get(idx).y] = i + 1;
				backtracking(zero, idx + 1);
				sudoku[zero.get(idx).x][zero.get(idx).y] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sudoku = new int[9][9];
		List<Point> zero = new ArrayList();
		for (int i = 0; i < 9; i++) {
			sudoku[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < 9; j++) {
				if (sudoku[i][j] == 0) {
					zero.add(new Point(i, j));
				}
			}
		}
		int idx = 0;
		backtracking(zero, idx);
		// 위에서부터 수를 넣을 때 가능한 거 뽑아보자
		// 백트래킹하되 상행 좌열부터 작은 수 넣어보기
	}
}
