import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	public static int N, M, V;
	public static boolean[][] map;

	public static void dfs(boolean[][] map, boolean[] vis, int cur, int next) {// next+1이 내가 원하는 값
		if(vis[next]==false) {
		System.out.print(next+1 + " ");}
		vis[next] = true;
		for (int i = 0; i < N; i++) {
			if (map[next][i] == true && vis[i] == false) {
				map[i][next] = false;
				map[next][i] = false;
				dfs(map, vis, next, i);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = input[0];
		M = input[1];
		V = input[2];// 시작점

		map = new boolean[N][N];// 갈 수 있는 배열인지 확인

		boolean[] vis = new boolean[N];

		for (int i = 0; i < M; i++) {
			int[] a = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			map[a[0] - 1][a[1] - 1] = true;
			map[a[1] - 1][a[0] - 1] = true;
		}

		boolean[][] dfs_map = new boolean[N][N];
		boolean[] dfs_vis = vis.clone();
		for (int i = 0; i < dfs_map.length; i++) {
			dfs_map[i] = map[i].clone();
		}

//	 	DFS part
		System.out.print(V + " ");
		dfs_vis[V - 1] = true;
		for (int i = 0; i < N; i++) {
			if (map[V - 1][i] == true && vis[i] == false) {
				dfs_map[V-1][i]=false;
				dfs_map[i][V-1]=false;
				dfs(dfs_map, dfs_vis, V - 1, i);
			}
		}
		System.out.println();
		// BFS part
		Arrays.fill(vis, false);
		Queue<Integer> q = new LinkedList<>();
		q.add(V - 1);
		while (!q.isEmpty()) {
			int cur = q.poll();

			if (vis[cur] == false) {
				System.out.print(cur + 1 + " ");
			}
			vis[cur] = true;
			for (int i = 0; i < N; i++) {
				if (map[cur][i] == true) {

					map[cur][i] = false;
					map[i][cur] = false;
					q.add(i);
				}
			}
		}
	}
}