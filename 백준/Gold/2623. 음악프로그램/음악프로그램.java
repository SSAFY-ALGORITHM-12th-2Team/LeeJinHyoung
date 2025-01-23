import java.util.*;
import java.io.*;

public class Main {
	private static int N, M;
	private static List<Integer>[] adjList;

	private static List<Integer> answer;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = NM[0];
		M = NM[1];

		adjList = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		int[] inDegree = new int[N + 1];
		for (int i = 0; i < M; i++) {// O(n) 100
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 1; j < input[0]; j++) {// O(n) 이건 안주네
				adjList[input[j]].add(input[j + 1]);
				inDegree[input[j + 1]]++;
			}
		} // 입력 끝

		List<Integer> start = new ArrayList<>();
		for (int i = 1; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				start.add(i);
			}
		}
		// 위상정렬 시작
		topologicalSort(start, inDegree);
	}

	private static void topologicalSort(List<Integer> start, int[] inDegree) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new ArrayDeque<Integer>();
		for (int i : start) {
			q.offer(i);
		} // 진입차수가 0인 곳을 모두 넣는다

		boolean[] vis = new boolean[N + 1];

		answer = new ArrayList<>();

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (vis[cur]) {// 방문한 곳 또 방문하면 중단
				break;
			}
			vis[cur] = true;
			answer.add(cur);

			for (int next : adjList[cur]) {
				if (vis[next])
					continue;
				inDegree[next]--;
				if (inDegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		if (answer.size() == N) {
			for (int i : answer) {
				System.out.println(i);
			}
		} else {
			System.out.println(0);
		}
	}
}