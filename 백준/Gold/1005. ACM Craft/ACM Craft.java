import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = input[0];
			int K = input[1];

			int[] D = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			List<Integer>[] adjList = new ArrayList[N + 1];
			int[] inbound = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				adjList[i] = new ArrayList();
			}
			for (int i = 0; i < K; i++) {
				int[] Edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				adjList[Edge[0]].add(Edge[1]);
				inbound[Edge[1]]++;
			} // 단방향
			int W = Integer.parseInt(br.readLine());// 골

			// 위상정렬 시작 전에 최단 경로를 찾아내자 512MB임
			// 이때 필요한 경로를 모두 찾아내자
			// 1번부터 시작해서 필요한 W까지 필요한 경로를 찾아내자
			int[] time = new int[N + 1];
			Queue<Integer> q = new ArrayDeque();
			for (int i = 1; i < inbound.length; i++) {
				if (inbound[i] == 0) {
					q.offer(i);
					time[i] = D[i - 1];
				}
			}
			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int i : adjList[cur]) {
					inbound[i]--;
					time[i] = Math.max(time[i], time[cur] + D[i - 1]);
					if (inbound[i] == 0)
						q.offer(i);
				}
			}

			bw.write(String.valueOf(time[W]) + "\n");
		}
		bw.flush();
	}
}