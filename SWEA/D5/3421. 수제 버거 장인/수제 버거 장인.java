import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	private static ArrayList<Integer>[] source;
	static int N, M;
	static int total;

	public static void subset(boolean[] vis, int cnt) {
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				if (source[i] == null) {
					continue;
				}
				for (int j = 0; j < source[i].size(); j++) {
					if (vis[i] == true && vis[source[i].get(j)] == true) {
						total--;
						return;
					}
				}
			}
			return;
		}
		vis[cnt] = true;
		subset(vis, cnt + 1);
		vis[cnt] = false;
		subset(vis, cnt + 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			N = input[0];
			M = input[1];

			total = (int) Math.pow(2, N);

			source = new ArrayList[N];
			for (int i = 0; i < M; i++) {
				int[] unbalanced = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				if (source[unbalanced[0] - 1] == null || source[unbalanced[1] - 1] == null) {
					if (source[unbalanced[0] - 1] == null) {
						source[unbalanced[0] - 1] = new ArrayList<>();
						source[unbalanced[0] - 1].add(unbalanced[1] - 1);
					}
					if (source[unbalanced[1] - 1] == null) {
						source[unbalanced[1] - 1] = new ArrayList<>();
						source[unbalanced[1] - 1].add(unbalanced[0] - 1);
					}
				} else {
					source[unbalanced[0] - 1].add(unbalanced[1] - 1);
					source[unbalanced[1] - 1].add(unbalanced[0] - 1);
				}
			}

			subset(new boolean[N], 0);
			System.out.println("#" + tc + " " + total);
		}
	}
}
