import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int K = input[1];

		int[][] memo = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			memo[i][0] = 1;
			memo[i][i] = 1;
		}

		for (int row = 2; row <= N; row++) {
			for (int col = 1; col <= row; col++) {
				if (memo[row][col] == 1)
					continue;
				memo[row][col] = (memo[row - 1][col - 1] + memo[row - 1][col])%10007;
			}
		}
		System.out.println(memo[N][K]);
	}
}