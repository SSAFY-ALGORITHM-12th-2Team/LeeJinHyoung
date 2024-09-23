import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = input[0];// 곡의 갯수
		int S = input[1];// 시작 볼륨
		int M = input[2];// M보다 큰 값으로 볼륨을 바꿀 순 없다

		int[] song = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		boolean[][] memo = new boolean[N + 1][M + 1];

		memo[0][S] = true;
		int idx = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c <= M; c++) {
				if (memo[r][c] == true) {
					if (c + song[idx] <= M) {
						memo[r + 1][c + song[idx]] = true;
					}
					if (c - song[idx] >= 0) {
						memo[r + 1][c - song[idx]] = true;
					}
				}
			}
			idx++;
		}
		for (int c = M; c >=0; c--) {
			if (memo[N][c] == true) {
				System.out.println(c);
				return;
			}
		}
		System.out.println(-1);
	}
}