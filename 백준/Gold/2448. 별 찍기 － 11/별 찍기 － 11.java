import java.util.*;
import java.io.*;

public class Main {
	private static String matrix[][];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		matrix = new String[N][2 * N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(matrix[i], " ");
		}

		makeTriangle(N, 0, N);
		StringBuilder sb = new StringBuilder();

		for (String[] arr : matrix) {
			for (String s : arr) {
				sb.append(s);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void makeTriangle(int N, int row, int col) {
		// TODO Auto-generated method stub
		if (N == 3) {
			matrix[row][col - 1] = "*";
			matrix[row + 1][col - 2] = matrix[row + 1][col] = "*";
			for (int i = col - 3; i <= col + 1; i++) {
				matrix[row + 2][i] = "*";
			}
			return;
		}
		makeTriangle(N / 2, row, col);
		makeTriangle(N / 2, row + N / 2, col - N / 2);
		makeTriangle(N / 2, row + N / 2, col + N / 2);
	}
}