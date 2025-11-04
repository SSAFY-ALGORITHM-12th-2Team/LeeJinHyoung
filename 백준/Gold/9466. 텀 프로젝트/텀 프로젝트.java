import java.util.*;
import java.io.*;

public class Main {
	private static int answer, n;
	private static boolean[] vis;
	static int[] state; // 0: 미방문, 1: 방문중, 2: 완료

	static void dfs(int cur, int[] student) {
		state[cur] = 1;
		int next = student[cur];

		if (state[next] == 0) {
			dfs(next, student);
		} else if (state[next] == 1) {
			// 사이클 발견
			for (int i = next; i != cur; i = student[i]) {
				vis[i] = true;
			}
			vis[cur] = true;
		}
		state[cur] = 2;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		전체 테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
//			입력할 수
			n = Integer.parseInt(br.readLine());
//			입력한 배열
			int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int student[] = new int[n + 1];
			student[0] = 0;
			for (int i = 1; i <= n; i++) {
				student[i] = input[i - 1];
			}

			vis = new boolean[n + 1];

			answer = 0;
			state = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				if (!vis[i])
					dfs(i, student);
			}
			for (int i = 1; i <= n; i++) {
				if (!vis[i])
					answer++;
			}
			System.out.println(answer);
		}
	}
}