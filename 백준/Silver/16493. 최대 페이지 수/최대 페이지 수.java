import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];// 날짜
		int M = input[1];// 챕터의 수
		int[][] memo = new int[M + 1][N + 1];

		int[][] chapter = new int[M + 1][2];
		for (int i = 1; i <= M; i++) {
			chapter[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		} // 입력 완료
		for (int i = 1; i <= M; i++) {// 챕터 갯수 순회
			for (int j = 1; j <= N; j++) {// 최대 날짜 순회
				// 만약 최대날짜보다 해당 챕터의 필요 일수가 작다면
				if (j >= chapter[i][0]) {
					memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - chapter[i][0]] + chapter[i][1]);
				} else {
					memo[i][j] = memo[i - 1][j];
				}
			}
		}
//		for (int r = 1; r <= M; r++) {
//			for (int c = 1; c <= N; c++) {
//				System.out.print(memo[r][c]);
//			}
//			System.out.println();
//		}
		System.out.println(memo[M][N]);
	}
}