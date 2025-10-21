import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] ad = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ad[i][0] = Integer.parseInt(st.nextToken()); // cost
			ad[i][1] = Integer.parseInt(st.nextToken()); // customer
		}

		int[] dp = new int[C + 101]; // 여유 공간 확보
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0; // 고객 0명 확보 비용은 0

		for (int i = 0; i < N; i++) {
			int cost = ad[i][0];
			int customer = ad[i][1];
			for (int j = customer; j < dp.length; j++) {
				if (dp[j - customer] != Integer.MAX_VALUE)
					dp[j] = Math.min(dp[j], dp[j - customer] + cost);
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = C; i < dp.length; i++) {
			answer = Math.min(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
