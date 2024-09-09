import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			cnt = 0;
			int N = Integer.parseInt(br.readLine());
			int[] ary = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			perm(ary, N, new boolean[N], 0, 0, 0);
			sb.append("#" + tc + " " + cnt + "\n");
		}
		System.out.print(sb);
	}

	private static void perm(int[] ary, int N, boolean[] vis, int time, int left, int right) {
		// TODO Auto-generated method stub
		if (time == N) {
//			for (int i = 0; i < 2; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(arr[i][j]);
//				}
//				System.out.println();
//			}
			cnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!vis[i]) {
				vis[i] = true;
				if (left >= right + ary[i]) {
					perm(ary, N, vis, time + 1, left, right + ary[i]);
					perm(ary, N, vis, time + 1, left + ary[i], right);
				} else {
					perm(ary, N, vis, time + 1, left + ary[i], right);
				}
				vis[i] = false;
			}
		}
	}
}