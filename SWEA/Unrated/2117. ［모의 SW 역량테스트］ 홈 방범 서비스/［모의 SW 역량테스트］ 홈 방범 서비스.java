import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int N, M;
	static int[][] map;

	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			N = input[0];
			M = input[1];
			map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			// 전략 : 손해를 보지 않는 선에서 최대한 넓은 곳에 서비스 하려고 한다.
			// 최대한 넓은 곳에서부터 손해가 날 경우 줄여간다.
			// 출력해야 하는 것은 서비스 받는 집의 갯수이다.
			// 자바 3초면 완전탐색 가능할 것으로 보임

			// 중심점에서 K-1만큼 맨해튼 거리로 떨어지면 된다 이때 비용은 K로 계산하면 된다.

			answer = Integer.MIN_VALUE;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {// 중심 종점을 잡는 for문
					for (int K = 2 * N; K >= 0; K--) {// K는 내가 설정하는 것이다.
						guardian(new boolean[N][N], row, col, K);
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void guardian(boolean[][] service, int row, int col, int K) {
		// TODO Auto-generated method stub
		int cost = 0;
		cost -= K * K + (K - 1) * (K - 1);
		int houseCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int dist = Math.abs(row - i) + Math.abs(col - j);
				if (dist <= K - 1 && map[i][j] == 1) {
					cost += M;
					houseCount++;
				}
			}
		} // service지역 확인
		if (cost >= 0) {
			answer = Math.max(answer, houseCount);
		}
	}

}
