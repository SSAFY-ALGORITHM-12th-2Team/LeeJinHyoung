import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int N, M, totalHouse, maxHouse, nowHouse, map[][];
	private static Queue<Node> q;
	private static boolean[][] visit;
	private static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			q = new LinkedList<>();
			totalHouse = maxHouse = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) totalHouse++;
				}
			}
			startPoint();
			sb.append("#" + t + " " + maxHouse + "\n");
		}
		System.out.println(sb);
	}

	private static void startPoint() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nowHouse = (map[i][j] == 1) ? 1 : 0;
				q.clear();
				visit = new boolean[N][N];
				visit[i][j] = true;
				q.add(new Node(i, j));
				bfs();
			}
		}
	}

	private static void bfs() {
		int nowCost = 1;
		for (int idx = 1; idx <= N + 1; idx++) {
			nowCost += (idx - 1) * 4;
			
			if (nowHouse > 0) {
				if ((nowHouse * M) - nowCost >= 0) {
					if (maxHouse < nowHouse)
						maxHouse = nowHouse;
					
					if (maxHouse == totalHouse)
						return;
				}
			}
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node temp = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny]) continue;

					if (map[nx][ny] == 1) nowHouse++;
					visit[nx][ny] = true;
					q.add(new Node(nx, ny));
				}
			}
		}
	}
}