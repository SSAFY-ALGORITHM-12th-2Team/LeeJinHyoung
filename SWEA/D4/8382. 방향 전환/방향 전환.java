import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static int x1, y1, x2, y2;
	public static int ans;
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	public static boolean[][][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 1;
			visit = new boolean[201][201][2];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			y1 = Integer.parseInt(st.nextToken())+100;
			x1 = Integer.parseInt(st.nextToken())+100;
			y2 = Integer.parseInt(st.nextToken())+100;
			x2 = Integer.parseInt(st.nextToken())+100;
			if(x1==x2 && y1==y2) {
				System.out.println("#" + tc + " " + 0);
				continue;
			}
			visit[x1][y1][0] = true;
			visit[x1][y1][1] = true;
			Queue<int[]> q = new LinkedList<int[]>();
			for (int i = 0; i < 4; i++) {
				int nx = x1 + dx[i];
				int ny = y1 + dy[i];
				if (nx < 0 || ny < 0 || nx > 200 || ny > 200)
					continue;
				visit[nx][ny][i%2] = true;
				q.offer(new int[] { nx, ny, i });
			}
			loop:
			while (!q.isEmpty()) {
				int qSize = q.size();
				while (qSize-- > 0) {
					int[] a = q.poll();
					int x = a[0];
					int y = a[1];
					int d = a[2];
					if (x == x2 && y == y2) {
						break loop;
					}
					if (d % 2 == 0) {// 0,2일때
						for (int i = 0; i < 4; i++) {
							if (i % 2 == 0)
								continue;
							int nx = x + dx[i];
							int ny = y + dy[i];
							if (nx < 0 || ny < 0 || nx > 200 || ny > 200 || visit[nx][ny][i%2] == true)
								continue;
							visit[nx][ny][i%2] = true;
							q.offer(new int[] { nx, ny, i });
						}
					} else {// 1,3일때
						for (int i = 0; i < 4; i++) {
							if (i % 2 != 0)
								continue;
							int nx = x + dx[i];
							int ny = y + dy[i];
							if (nx < 0 || ny < 0 || nx > 200 || ny > 200 || visit[nx][ny][i%2] == true)
								continue;
							visit[nx][ny][i%2] = true;
							q.offer(new int[] { nx, ny, i });
						}
					}
				}
				ans++;
			}

			System.out.println("#" + tc + " " + ans);

		}

	}

}