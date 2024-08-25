import java.util.*;
import java.io.*;

public class Solution{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, C;
	static int[][] map;
	static boolean[][] visited;
	static int maxNum = 0;

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + tc + " " + combination());
		}
	}

	private static int combination() {
		int result = 0;
		int max1 = 0;
		int max2 = 0;
		for (int i = 0; i < N; i++) { // 첫 행부터 살펴본다.
			for (int j = 0; j <= N - M; j++) { // 첫 열부터 벌통 선택할 수 있는 열(N-M)까지
				// 일꾼1 벌통 선택 시작
				// 일꾼1이 벌통을 다시 선택하므로 이익 0으로 초기화
				maxNum = 0;
				// x좌표, y좌표, 벌통의 개수, 채취한 꿀의 양, 얻은 이익
				getMaxHoney(i, j, 0, 0, 0);
				max1 = maxNum; // j열에서 벌통을 선택했을 때, 일꾼1이 얻은 최대 이익

				// 일꾼2 벌통 선택 시작
				maxNum = 0; // 일꾼2가 벌통을 선택할 차례, 이익 0으로 초기화
				max2 = 0; // 일꾼1의 선택이 바뀌었으므로, 일꾼2의 이익도 0으로 초기화
				// 일꾼2는 일꾼1이 선택한 벌통 다음 열에서 벌통을 다시 선택한다.
				for (int j2 = j + M; j2 <= N - M; j2++) {
					// 현재 좌표 (i, j2), 선택한 벌통 수, 꿀의 양, 이익
					getMaxHoney(i, j2, 0, 0, 0);
					max2 = Math.max(max2, maxNum); // 이익 최댓값으로 갱신
				}

				// 일꾼2는 다른 행에서 벌통을 선택하는게 더 클 수도 있다.
				// 일꾼1의 다음 행부터 살펴본다.
				for (int i2 = i + 1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N - M; j2++) { // 첫 열부터 벌통 선택할 수 있는 열(N-M)까지
						getMaxHoney(i2, j2, 0, 0, 0);
						max2 = Math.max(max2, maxNum); // 이익 최댓값으로 갱신
					}
				}
				// 일꾼1이 벌통을 새로 선택했을 때마다 전체 이익 최댓값으로 갱신
				result = Math.max(result, max1 + max2);
			}
		}
		return result;
	}

	private static void getMaxHoney(int i, int j, int cnt, int sum, int benefit) {
		// 채취한 꿀이 최대 채취 양을 넘었으면 그냥 return
		if (sum > C)
			return;

		// 벌통 M개 선택했으면
		if (cnt == M) {
			// 최대 이익 갱신
			if (maxNum < benefit)
				maxNum = benefit;
			return;
		}

		// 선택
		getMaxHoney(i, j + 1, cnt + 1, sum + map[i][j], benefit + map[i][j] * map[i][j]);
		// 비선택
		getMaxHoney(i, j + 1, cnt + 1, sum, benefit);

	}
}