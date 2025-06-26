import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] trees = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = input1[0];
		int M = input1[1];

		Arrays.sort(trees);

		int answer = 0;
		long sum = 0;

		for (int i = N - 1; i > 0; i--) {
			int diff = trees[i] - trees[i - 1];
			int cnt = N - i; // 잘리는 나무 개수

			long totalCut = (long) diff * cnt;
			if (sum + totalCut < M) {
				sum += totalCut;
				answer = trees[i - 1]; // 다음 낮은 나무 기준
			} else {
				long remain = M - sum;
				long cutHeight = remain / cnt;
				long extra = remain % cnt > 0 ? 1 : 0;
				answer = trees[i] - (int)(cutHeight + extra);
				System.out.println(answer);
				return;
			}
		}

		// 만약 끝까지 와도 부족하다면 전체 자르기
		long remain = M - sum;
		long cutHeight = remain / N;
		long extra = remain % N > 0 ? 1 : 0;
		answer = trees[0] - (int)(cutHeight + extra);
		System.out.println(Math.max(answer, 0));
	}
}
