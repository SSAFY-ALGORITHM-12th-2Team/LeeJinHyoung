import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	private static class baggage implements Comparable<baggage> {
		int weight;
		int value;

		public baggage(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(baggage o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] cond = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = cond[0];
		int K = cond[1];

		baggage[] bags = new baggage[N + 1];
		for (int i = 1; i <= N; i++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			bags[i] = new baggage(input[0], input[1]);
		}

		int[][] memo = new int[N + 1][K + 1];

		memo[0][0] = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= K; c++) {
				if (bags[r].weight > c) {
					memo[r][c] = memo[r - 1][c];
				} else {
					memo[r][c] = Math.max(memo[r - 1][c], memo[r - 1][c - bags[r].weight] + bags[r].value);
				}
			}
		}
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= K; j++) {
//				System.out.print(memo[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(memo[N][K]);
	}
}