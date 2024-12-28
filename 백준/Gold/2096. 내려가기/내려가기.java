import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int N, board[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][3];

		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		// Sliding Window?? 기법을 쓰기 위해 필요

		int maxVal = Integer.MIN_VALUE;
		int minVal = Integer.MAX_VALUE;
		maxVal = Math.max(maxVal, maxDP(new int[N][3]));
		minVal = Math.min(minVal, minDP(new int[N][3]));

		System.out.println(maxVal + " " + minVal);
	}

	private static int maxDP(int window[][]) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			window[0][i] = board[0][i];
		}
		for (int i = 1; i < N; i++) {
			window[i][0] = Math.max(window[i - 1][0], window[i - 1][1]) + board[i][0];
			window[i][1] = Math.max(window[i - 1][0], Math.max(window[i - 1][1], window[i - 1][2])) + board[i][1];
			window[i][2] = Math.max(window[i - 1][1], window[i - 1][2]) + board[i][2];
		}
		int ans = Integer.MIN_VALUE;
		for (int i : window[N - 1])
			ans = Math.max(i, ans);
		return ans;
	}

	private static int minDP(int window[][]) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			window[0][i] = board[0][i];
		}
		for (int i = 1; i < N; i++) {
			window[i][0] = Math.min(window[i - 1][0], window[i - 1][1]) + board[i][0];
			window[i][1] = Math.min(window[i - 1][0], Math.min(window[i - 1][1], window[i - 1][2])) + board[i][1];
			window[i][2] = Math.min(window[i - 1][1], window[i - 1][2]) + board[i][2];
		}
		int ans = Integer.MAX_VALUE;
		for (int i : window[N - 1])
			ans = Math.min(i, ans);
		return ans;
	}
}