import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = input[0];
		int M = input[1];
		ArrayList<Integer>[] adjList = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjList[i] = new ArrayList();
		}
		int[] edgeCount = new int[N + 1];

		for (int i = 0; i < M; i++) {
			int[] edge = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adjList[edge[0]].add(edge[1]);
			edgeCount[edge[1]]++;
		}

		Queue<Integer> q = new ArrayDeque<Integer>();
		for (int i = 1; i < N + 1; i++) {
			if (edgeCount[i] == 0) {
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			int start = q.poll();

			System.out.print(start + " ");

			for (int i = 0; i < adjList[start].size(); i++) {
				int temp = adjList[start].get(i);
				edgeCount[temp]--;
				if (edgeCount[temp] == 0) {
					q.offer(temp);
				}
			}
		}
	}
}
