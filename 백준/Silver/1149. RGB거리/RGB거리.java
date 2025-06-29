import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	public static class colorCost {
		private int color;
		private int cost;

		public colorCost(int color, int cost) {
			this.color = color;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] cost = new int[N][3];
		for (int i = 0; i < N; i++) {
			cost[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		int[][] memo = new int[N][3];
		memo[0][0] = cost[0][0];
		memo[0][1] = cost[0][1];
		memo[0][2] = cost[0][2];

		for (int i = 1; i < N; i++) {
			memo[i][0] = cost[i][0] + Math.min(memo[i - 1][1], memo[i - 1][2]);
			memo[i][1] = cost[i][1] + Math.min(memo[i - 1][0], memo[i - 1][2]);
			memo[i][2] = cost[i][2] + Math.min(memo[i - 1][0], memo[i - 1][1]);
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			answer = Math.min(answer, memo[N - 1][i]);
		}

		System.out.println(answer);
	}
}