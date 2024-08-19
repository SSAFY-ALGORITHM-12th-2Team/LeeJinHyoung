import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	private static int N;
	private static int[][] taste;

	private static int min;

	private static void combi(int[] ans, boolean[] vis, int cnt, int start) {// 2팀으로 나눈다
		if (cnt == ans.length) {
			int team1_taste = 0, team2_taste = 0;
			int[] team1 = new int[N / 2];
			int[] team2 = new int[N / 2];
			int team1_idx = 0, team2_idx = 0;
			for (int i = 0; i < vis.length; i++) {
				if (vis[i] == true)
					team1[team1_idx++] = i;
				else if (vis[i] == false) {
					team2[team2_idx++] = i;
				}
			}
			for (int i = 0; i < N / 2 - 1; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					team1_taste += taste[team1[i]][team1[j]];
					team1_taste += taste[team1[j]][team1[i]];
					team2_taste += taste[team2[i]][team2[j]];
					team2_taste += taste[team2[j]][team2[i]];
				}
			}
			int diff = Math.abs(team1_taste - team2_taste);
			min = Math.min(diff, min);
			return;
		}
		for (int i = start; i < N; i++) {
			if (vis[i] == false) {
				vis[i] = true;
				ans[cnt] = i;
				combi(ans, vis, cnt + 1, i + 1);
				vis[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			taste = new int[N][];
			for (int i = 0; i < N; i++) {
				taste[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			combi(new int[N / 2], new boolean[N], 0, 0);// 식재료 뽑아낸다
			System.out.println("#" + tc + " " + min);
		}
	}
}