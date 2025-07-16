import java.util.*;
import java.io.*;
import java.nio.DoubleBuffer;

public class Main {
	private static int N, adjMatrix[][], answer[][];

	private static void bfs() {
//		최초 행 시작점
		for (int begin = 0; begin < N; begin++) {
//			BFS 시작
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.offer(begin);
			boolean[] vis = new boolean[N];

			while (!q.isEmpty()) {
				int start = q.poll();

				if (start < begin) {
					for (int dest = 0; dest < N; dest++) {
						if (answer[start][dest] == 1) {
							answer[begin][dest] = answer[start][dest];
						}
					}
				}

				if (vis[start])
					continue;
				vis[start] = true;

				for (int dest = 0; dest < N; dest++) {
					if (answer[begin][dest] == 0) {
						answer[begin][dest] = adjMatrix[start][dest];
					}
					if (adjMatrix[start][dest] == 1) {
						q.offer(dest);
					}
				}
			}
		}
	}

	private static void print() {
		StringBuilder print = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				print.append(answer[i][j] + " ");
			}
			print.append("\n");
		}
		System.out.println(print);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		adjMatrix = new int[N][N];
		answer = new int[N][N];
		for (int i = 0; i < N; i++) {
			adjMatrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
//		BFS를 해서 경로를 방문 후 해당 경로를 갈 수 있다면 기억했다가 만약 그 경로 방문 시 해당 답을 옮긴다.
		bfs();
		print();
	}
}