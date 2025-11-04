
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int T = input[0];
		int W = input[1];

		int[][][] memo = new int[T + 1][W + 1][2];

		for (int time = 1; time <= T; time++) {
			int tree = Integer.parseInt(br.readLine());

			for (int move = 0; move <= W; move++) {
				if (tree == 1) {
					if (move == 0) {
						memo[time][move][0] = memo[time - 1][move][0] + 1;
					} else {
						memo[time][move][0] = Math.max(memo[time - 1][move][0], memo[time - 1][move - 1][1]) + 1;
						memo[time][move][1] = Math.max(memo[time - 1][move][1], memo[time - 1][move - 1][0]);
					}
				} else {
					if (move == 0) {
						memo[time][move][0] = memo[time - 1][move][0];
					} else {
						memo[time][move][1] = Math.max(memo[time - 1][move][1], memo[time - 1][move - 1][0]) + 1;
						memo[time][move][0] = Math.max(memo[time - 1][move][0], memo[time - 1][move - 1][1]);
					}
				}
			}
		}

		int answer = 0;
		for (int move = 0; move <= W; move++) {
			answer = Math.max(answer, Math.max(memo[T][move][0], memo[T][move][1]));
		}
		System.out.println(answer);
	}
}