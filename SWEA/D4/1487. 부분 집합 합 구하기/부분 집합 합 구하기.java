
import java.util.Scanner;

public class Solution {
	private static int[] ary;
	private static int answer;

	private static void powerSet(boolean[] vis, int idx, int S, int sum) {
		if (idx == vis.length) {
			if (sum == S) {
				answer++;
			}
			return;
		}
		if (sum > S) {
			return;
		}
		vis[idx] = true;
		powerSet(vis, idx + 1, S, sum + ary[idx]);
		vis[idx] = false;
		powerSet(vis, idx + 1, S, sum);
	}

	public static void main(String[] args) throws NumberFormatException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int S = sc.nextInt();
			ary = new int[N];
			for (int i = 0; i < ary.length; i++) {
				ary[i] = sc.nextInt();
			}

			answer = 0;

			powerSet(new boolean[N], 0, S, 0);
			System.out.println(answer);
		}
	}
}