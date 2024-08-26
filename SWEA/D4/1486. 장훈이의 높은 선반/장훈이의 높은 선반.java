import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.stream.Stream;

public class Solution {
	private static int N, B;
	private static int[] input;
	static int diff_min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int[] NB = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			N = NB[0];
			B = NB[1];
			diff_min = Integer.MAX_VALUE;
			// 1개 선택할 경우
			// 2개 선택할 경우 ... N개 선택할 경우

			for (int len = 1; len <= N; len++) {
				combi(new int[len], len, new boolean[N], 0, 0);
			}
			System.out.println("#" + tc + " "+diff_min);
		}
	}

	private static void combi(int[] arr, int len, boolean[] vis, int cnt, int start) {
		// TODO Auto-generated method stub
		if (cnt == len) {
			int sum = 0;
			for (int e : arr)
				sum += e;
			if (sum - B < 0)
				return;

			if(diff_min>sum-B) {
				diff_min=sum-B;
			}
			
			return;
		}

		for (int i = start; i < N; i++) {
			if (vis[i] == false) {
				vis[i] = true;
				arr[cnt] = input[i];
				combi(arr, len, vis, cnt + 1, i + 1);
				vis[i] = false;
			}
		}
	}
}