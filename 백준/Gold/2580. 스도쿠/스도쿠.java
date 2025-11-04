import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int[][] board;
	private static boolean solve;

	private static boolean[] vertical(int row, int col) {
		boolean[] used = new boolean[10];
		for (int r = 0; r < 9; r++) {
			if (r == row)
				continue;
			used[board[r][col]] = true;
		}
		return used;
	}

	private static boolean[] horizontal(int row, int col) {
		boolean[] used = new boolean[10];
		for (int c = 0; c < 9; c++) {
			if (c == col)
				continue;
			used[board[row][c]] = true;
		}
		return used;
	}

	private static boolean[] square(int row, int col) {
		boolean[] used = new boolean[10];
		int rowStart = (row / 3) * 3;
		int colStart = (col / 3) * 3;
		for (int r = rowStart; r < rowStart + 3; r++) {
			for (int c = colStart; c < colStart + 3; c++) {
				if (r == row && c == col)
					continue;
				used[board[r][c]] = true;
			}
		}
		return used;
	}

	public static void backtracking(List<Point> blankPosition, int blankPositionIdx) {
		if (solve) {
			return;
		}
		if (blankPositionIdx == blankPosition.size()) {
			solve = true;
			StringBuilder answer = new StringBuilder();
			for (int[] i : board) {
				for (int j : i) {
					answer.append(j + " ");
				}
				answer.append("\n");
			}
			System.out.println(answer);
			return;
		}
		Point p = blankPosition.get(blankPositionIdx);
//		해당 좌표에서 가능한 숫자
		int[] possible = new int[10];
		Arrays.fill(possible, 1);
		boolean[] vertical = vertical(p.x, p.y);
		boolean[] horizontal = horizontal(p.x, p.y);
		boolean[] square = square(p.x, p.y);

		for (int k = 1; k < 10; k++) {
			if (vertical[k])
				possible[k] = 0;
			if (horizontal[k])
				possible[k] = 0;
			if (square[k])
				possible[k] = 0;
		}
		for (int next = 1; next < 10; next++) {
			if (possible[next] == 1) {
				board[p.x][p.y] = next;
				backtracking(blankPosition, blankPositionIdx + 1);
				board[p.x][p.y] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[9][9];
		List<Point> blankPosition = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 0) {
					blankPosition.add(new Point(i, j));
				}
			}
		}
//		전략 : 0의 위치가 있는 좌표 중 가장 선택 수가 적은 곳부터 채워 나간다.
//		1. 0이 써있는 칸 중 가용 숫자가 적은 칸부터 하나씩 채워나간다.
//		2. 하나씩 채워가면서 
		solve = false;
		backtracking(blankPosition, 0);
	}
}