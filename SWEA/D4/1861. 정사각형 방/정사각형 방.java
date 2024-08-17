import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	public pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

	int r;
	int c;
}

public class Solution {
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][];
			for(int i=0;i<N;i++) {
				map[i]=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			
			boolean[][] vis = new boolean[N][N];

			int[][] save = new int[N][N];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					Queue<pos> q = new ArrayDeque<pos>();
					int cnt = 1;
					q.add(new pos(r, c));
					while (!q.isEmpty()) {
						pos cur = q.poll();
						for (int dir = 0; dir < 4; dir++) {
							int nr = cur.r + dr[dir];
							int nc = cur.c + dc[dir];

							if (nr >= 0 && nc >= 0 && nr < N && nc < N && map[cur.r][cur.c] + 1 == map[nr][nc]) {
								q.add(new pos(nr, nc));
								cnt++;
							}
						}
					}
					save[r][c] = cnt;
				}
			} // 이 결과가 끝난다면 save에는 각 칸에서 갈 수 있는 모든 카운트가 나온다

			int room_number = 0;
			int max = Integer.MIN_VALUE;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (max < save[r][c]) {
						max = save[r][c];
						room_number = map[r][c];
					} else if (max == save[r][c]) {
						if (room_number > map[r][c]) {
							room_number = map[r][c];
						}
					}
				}
			}
			System.out.println("#" + tc + " " + room_number+" "+max);
		}
	}
}