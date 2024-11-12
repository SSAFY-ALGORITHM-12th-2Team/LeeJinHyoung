import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int D, W, K;
	static int[][] film;

	static boolean exitFlag;// 만약 검사를 통과한다면 exitFlag를 true로 바꾼다
	static int answer;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			D = input[0];
			W = input[1];
			K = input[2];

			film = new int[D][W];

			for (int i = 0; i < D; i++) {
				film[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			exitFlag = false;
			answer = Integer.MAX_VALUE;

			// 전략 : 자바 5초 의미는 완전탐색을 하라는 의미
			// 2^13을 해도 8192밖에 안됨

			// 약품을 칠할 필름을 고른다.

			for (int length = 0; length <= D; length++) {
				if (!exitFlag)
					combi(new int[length], new boolean[D], 0, 0);
			}
			System.out.println("#" + tc + " " + answer);
			// 골랐으면 A도 칠해보고 B도 칠해본다.
			// 만약 해당 회차에서 나온다면 return 한다.

		}
	}

	private static void combi(int[] sel, boolean[] vis, int idx, int start) {
		// TODO Auto-generated method stub
		if (idx == sel.length) {
			int[][] filmCopy = new int[D][W];// 복사본
			for (int r = 0; r < D; r++) {
				for (int c = 0; c < W; c++) {
					filmCopy[r][c] = film[r][c];
				}
			}
			dfs(sel, filmCopy, 0);

			// DFS로 가짓수를 파고 들어야 한다.

			return;
		}
		for (int i = start; i < vis.length; i++) {
			if (!vis[i]) {
				vis[i] = true;
				sel[idx] = i;
				combi(sel, vis, idx + 1, i + 1);
				vis[i] = false;
			}
		}

	}

	private static void dfs(int[] sel, int[][] filmCopy, int idx) {
		// TODO Auto-generated method stub
		if (idx == sel.length) {
			boolean[] checked = new boolean[W];
			for (int col = 0; col < W; col++) {
				int repeat = 1;// 연속하는 층의 갯수
				for (int row = 1; row < D; row++) {
					if (filmCopy[row][col] == filmCopy[row - 1][col]) {// 만약 연속하는 층이라면
						repeat++;
						if (repeat == K) {
							break;
						}
					} else {// 연속하지 않는다면
						repeat = 1;
					}
				}
				if (repeat == K) {
					checked[col] = true;
				} else {
					return;
				}
			}
			exitFlag = true;
			answer = sel.length;
			return;
			// 구태여 탈출 함수의 마지막에서 탈출할 필요 없다 막 하나라도 검증 안되면 탈출
		}
		for (int paint = 0; paint < 2; paint++) {
			int[] save = new int[W];
			for (int i = 0; i < W; i++) {// 색칠하기 전 원상복구를 위한 배열 만들고 값 넣기
				save[i] = filmCopy[sel[idx]][i];
			}
			for (int col = 0; col < W; col++) {// 색칠후
				filmCopy[sel[idx]][col] = paint;
			}
			dfs(sel, filmCopy, idx + 1);
			for (int col = 0; col < W; col++) {// 원상 복귀
				filmCopy[sel[idx]][col] = save[col];
			}
		}
	}
}