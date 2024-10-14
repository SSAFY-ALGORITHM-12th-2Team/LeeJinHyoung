import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int[][] map;
	static int N, X;

	private static int vertical() {// 세로 활주로 갯수 세기
		int answer = 0;
		L: for (int col = 0; col < N; col++) {
			boolean[] road = new boolean[N];
			for (int row = 1; row < N; row++) {
				if (map[row][col] == map[row - 1][col]) {// 이전 셀과 같은 경우
					continue;
				} else if (map[row][col] - 1 == map[row - 1][col]) {// 이전 셀보다 1이 클 경우
					for (int i = 1; i <= X; i++) {
						if (row - i < 0) {
							continue L;
						} else if (road[row - i]) {
							continue L;
						} else if (row - i >= 0 && !(map[row - i][col] == map[row - 1][col])) {
							continue L;
						}
					}
					for (int i = 1; i <= X; i++) {
						road[row - i] = true;
					}
				} else if (map[row][col] == map[row - 1][col] - 1) {// 이전 셀보다 1이 작을 경우
					for (int i = 0; i < X; i++) {
						if (row + i >= N) {
							continue L;
						} else if (road[row + i]) {
							continue L;
						} else if (row + i < N && !(map[row + i][col] == map[row][col])) {
							continue L;
						}
					}
					for (int i = 0; i < X; i++) {
						road[row + i] = true;
					}
				} else {// 차이가 1 초과로 나는 경우
					continue L;
				}
			}
			++answer;
//			System.out.println(col + " " + ++answer);
		}
		return answer;
	}

	private static int horizontal() {// 가로 활주로 갯수 세기
		int answer = 0;
		L: for (int row = 0; row < N; row++) {
			boolean[] road = new boolean[N];
			for (int col = 1; col < N; col++) {
				if (map[row][col] == map[row][col - 1]) {// 이전 셀과 같은 경우
					continue;
				} else if (map[row][col] - 1 == map[row][col - 1]) {// 이전 셀보다 1이 클 경우
					for (int i = 1; i <= X; i++) {
						if (col - i < 0) {// 범위가 안 맞으면
							continue L;
						} else if (road[col - i]) {// 이미 경사면 깔았으면 못 깔으므로
							continue L;
						} else if (col - i >= 0 && !(map[row][col - i] == map[row][col - 1])) {// 범위가 맞고 높이가 평탄하지 않으면
							continue L;
						}
					}
					for (int i = 1; i <= X; i++) {
						road[col - i] = true;
					}
				} else if (map[row][col] == map[row][col - 1] - 1) {// 이전 셀보다 1이 작을 경우
					for (int i = 0; i < X; i++) {
						if (col + i >= N) {// 범위가 안 맞으면
							continue L;
						} else if (road[col + i]) {// 이미 경사면 깔았으면 못 깔으므로
							continue L;
						} else if (col + i < N && !(map[row][col + i] == map[row][col])) {// 범위가 맞고 높이가 평탄하지 않으면
							continue L;
						}
					}
					for (int i = 0; i < X; i++) {
						road[col + i] = true;
					}
				} else {// 차이가 1 초과로 나는 경우
					continue L;
				}
			}
			++answer;
//			System.out.println(row + " " + ++answer);
		}
		return answer;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N = input[0];
			X = input[1];
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			System.out.println("#" + tc + " " + (vertical() + horizontal()));
		}
	}
}