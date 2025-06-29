import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] input = new int[N][];

		for (int i = 0; i < N; i++) {
			input[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		int[][] memo = new int[N][N];
		memo[0][0] = input[0][0];

		for (int row = 1; row < N; row++) {
			for (int col = 0; col <= row; col++) {
				if (col == 0) {
					memo[row][col] = input[row][col] + memo[row - 1][col];
				} else if (col == N - 1) {
					memo[row][col] = input[row][col] + memo[row - 1][col - 1];
				} else {
					memo[row][col] = input[row][col] + Math.max(memo[row - 1][col - 1], memo[row - 1][col]);
				}
			}
		}
		int answer = 0;

		for (int i = 0; i < N; i++) {
			answer = Math.max(memo[N - 1][i], answer);
		}
		System.out.println(answer);
	}
}