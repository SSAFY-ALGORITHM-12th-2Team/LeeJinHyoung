import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int n;
	static int[][] adjMatrix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			adjMatrix = new int[n + 1][n + 1];
			for (int i = 0; i < adjMatrix.length; i++) {
				Arrays.fill(adjMatrix[i], 0);
			}
			int inDegree[] = new int[n + 1];
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < arr.length; j++) {
					adjMatrix[arr[i]][arr[j]] = 1;
					inDegree[arr[j]]++;
				}
			}
			int m = Integer.parseInt(br.readLine());
			for (int i = 0; i < m; i++) {
				int[] ch = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

				if (adjMatrix[ch[0]][ch[1]] == 0) {
					adjMatrix[ch[0]][ch[1]] = 1;
					adjMatrix[ch[1]][ch[0]] = 0;
					inDegree[ch[0]]--;
					inDegree[ch[1]]++;
				} else if (adjMatrix[ch[1]][ch[0]] == 0) {
					adjMatrix[ch[0]][ch[1]] = 0;
					adjMatrix[ch[1]][ch[0]] = 1;
					inDegree[ch[0]]++;
					inDegree[ch[1]]--;
				}
			} // 입력 완료
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i < inDegree.length; i++) {
				if (inDegree[i] == 0)
					q.offer(i);
			}
			if (q.isEmpty()) {
				sb.append("IMPOSSIBLE\n");
				continue;
			}
			/* 위상 정렬 part */
			int[] result = new int[n];
			int idx = 0;
			while (!q.isEmpty()) {
				int cur = q.poll();
				result[idx++] = cur;
				for (int i = 1; i < adjMatrix[cur].length; i++) {
					if (adjMatrix[cur][i] == 1) {
						if (--inDegree[i] == 0)
							q.offer(i);
					}
				}
			}
			/* 출력 파트 */
			if (idx == n) {
				for (int i = 0; i < result.length; i++) {
					sb.append(result[i] + " ");
				}
				sb.append("\n");
			} else {
				sb.append("IMPOSSIBLE\n");
			}
		}
		System.out.println(sb);
	}
}