import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	public pos(int r, int c) {
		// TODO Auto-generated constructor stub
		this.r = r;
		this.c = c;
	}
	int r;
	int c;
}

public class Main {
	static int[] dr = { 0, 1, 0, -1, 1, -1, -1, 1 };
	static int[] dc = { 1, 0, -1, 0, 1, 1, -1, -1 };
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		while (!(input[0] == 0 && input[1] == 0)) {
			int island = 0;

			int w = input[0];
			int h = input[1];
			map = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				int[] map_input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int j = 0; j < w; j++) {
					if (map_input[j] == 1)
						map[i][j] = true;
					else if (map_input[j] == 0) {
						map[i][j] = false;
					}
				}
			}

			Queue<pos> q = new LinkedList<pos>();
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					q.clear();
					if (map[r][c] == true) {
						q.add(new pos(r, c));
						island++;
					}
					while (!q.isEmpty()) {
						pos cur = q.poll();
						
						for (int dir = 0; dir < 8; dir++) {
							int nr = cur.r + dr[dir];
							int nc = cur.c + dc[dir];

							if (nr >= 0 && nc >= 0 && nr < h && nc < w && map[nr][nc] == true) {
								q.offer(new pos(nr, nc));
								map[nr][nc] = false;
							}
						}
					}
				}
			}
			System.out.println(island);
			input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();// 재입력받음
		}
	}
}