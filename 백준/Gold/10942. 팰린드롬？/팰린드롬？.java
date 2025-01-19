import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] ary = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = Integer.parseInt(br.readLine());
		// 입력을 받는다

		boolean[][] memo = new boolean[N][N];// 중앙값을 메모하자
		// 아니네 양끝값?
		// 걍 메모를 다하고 쿼리에 답만 써주면?

		for (int E = 0; E < N; E++) {// 배열 순서대로 순회(열)
			for (int S = 0; S <= E; S++) {// 메모 결과(행)
				if (S == E) {
					memo[S][E] = true;
				} else if ((S + 1 < N && E - 1 >= 0) && memo[S + 1][E - 1] == true && ary[S] == ary[E]) {
					memo[S][E] = true;
				} else {
					int start = S;
					int end = E;
					if (end - start <= 1) {
						if (ary[end] == ary[start]) {
							memo[start][end] = true;
						}
					} else {
						while (start < end && ary[start] == ary[end]) {
							start++;
							end--;
						}
						if (E - S <= 1) {
							memo[S][E] = true;
						}
					}
				}
			}
		}

		for (int tc = 1; tc <= M; tc++) {
			int[] query = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			// 각 쿼리를 memoization하면 될 거 같은데
			// 야발 양 끝에서 찾아나가는 방식도 시간 초과가 나면 어떻게 하냐
			if (memo[query[0] - 1][query[1] - 1] == true) {
				answer.append(1 + "\n");
			} else {
				answer.append(0 + "\n");
			}
		}
		System.out.println(answer);
	}
}