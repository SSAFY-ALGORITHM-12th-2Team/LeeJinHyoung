import java.util.*;
import java.io.*;

public class Main {
	private static List<Integer>[] adjList;
	private static int answer;

	private static void dfs(int root, int remove) {
		if (adjList[root].size() == 0) {
			answer++;
			return;
		} else if (adjList[root].size() == 1 && remove == adjList[root].get(0)) {
			answer++;
			return;
		}

		for (int i = 0; i < adjList[root].size(); i++) {
			if (adjList[root].get(i) == remove)
				continue;
			dfs(adjList[root].get(i), remove);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] parent = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int remove = Integer.parseInt(br.readLine());
//		입력 끝
		answer = 0;

//		인접리스트 선언
		adjList = new ArrayList[51];
		for (int i = 0; i <= 50; i++) {
			adjList[i] = new ArrayList<>();
		}
//		인접리스트 초기화
		for (int i = 0; i < N; i++) {
			if (parent[i] == -1)
				continue;
			adjList[parent[i]].add(i);
		}
//		DFS 시작
		for (int i = 0; i < N; i++) {
			if (remove != i && parent[i] == -1) {
				dfs(i, remove);
			}
		}

		System.out.println(answer);
	}
}