import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static int N = 10;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		dfs(0);
		if (Ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(Ans);
	}

	static int Ans = Integer.MAX_VALUE;

	private static void dfs(int cnt) {
		// basis part
		int sr = -1, sc = -1;
		L: for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1) {
					sr = r;
					sc = c;
					break L;
				}
			}
		}
		if (sr == -1 && sc == -1) {
			// 더이상 붙일때가 없다
			// 다 붙였다
			Ans = Math.min(Ans, cnt);
		}

		int size = getPageSize(sr, sc);
		// System.out.println(size);
		// inductive part
		for (int i = 1; i <= size; i++) {
			if (papers[i] > 0) {
				for (int r = 0; r < i; r++) {
					for (int c = 0; c < i; c++) {
						map[sr + r][sc + c] = 0;
					}
				}
				papers[i]--;
				dfs(cnt + 1);
				for (int r = 0; r < i; r++) {
					for (int c = 0; c < i; c++) {
						map[sr + r][sc + c] = 1;
					}
				}
				papers[i]++;
			}
		}
	}

	private static int getPageSize(int sr, int sc) {
		int SIZE = 5;
		for (int size = SIZE; size > 0; size--) {
			boolean flag = true;
			L: for (int r = sr; r < sr + size; r++) {
				for (int c = sc; c < sc + size; c++) {
					if (r < 0 || r >= N || c < 0 || c >= N || map[r][c] == 0) {
						flag = false;
						break L;
					}
				}
			}
			if (flag) {
				return size;
			}
		}
		return -1;
	}

	static int[] papers = { 0, 5, 5, 5, 5, 5 };

	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
}
