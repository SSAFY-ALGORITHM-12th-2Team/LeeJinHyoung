import java.util.*;
import java.util.stream.Stream;
import java.awt.*;
import java.io.*;

public class Main {
	private static int R, C;
	private static int[][] map;
	private static int min = Integer.MAX_VALUE;

	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int longer;
	static ArrayList<Point> cctv;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] size = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		R = size[0];
		C = size[1];

		map = new int[R][C];
		longer = R > C ? R : C;
		boolean[][] check = new boolean[R][C];
		cctv = new ArrayList<Point>();
		for (int i = 0; i < R; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < C; j++) {
				if ((map[i][j] >= 1 && map[i][j] <= 5)) {
					cctv.add(new Point(i, j));
				} else if (map[i][j] == 6) {
					check[i][j] = true;
				}
			}
		}
		backtracking(check, 0);

		System.out.println(min);
	}

	private static void backtracking(boolean[][] check, int idx) {
		// TODO Auto-generated method stub
		if (idx == cctv.size()) {
			int unchecked = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (!check[i][j]) {
						unchecked++;
					}
					//System.out.print(check[i][j]);
				}
				//System.out.println();
			}
			//System.out.println(unchecked + " ");
			min = Math.min(unchecked, min);
			return;
		}

		Point cur = cctv.get(idx);
		check[cur.x][cur.y] = true;

		/*
		 * 현재 겹치는 거 만들어야 함
		 */
		if (map[cur.x][cur.y] == 1) {
			int[][] overlap = new int[R][C];
			for (int dir = 0; dir < 4; dir++) {
				int k = 1;
				for (k = 1; k < longer; k++) {
					int nr = cur.x + dr[dir] * k;
					int nc = cur.y + dc[dir] * k;

					if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {
						break;
					} else if (map[nr][nc] == 6) {
						break;
					} else if (check[nr][nc] == true) {// 다른 cctv에서 확인한 구역
						overlap[nr][nc]++;
						continue;
					}
					check[nr][nc] = true;
				}
				backtracking(check, idx + 1);
				for (k = k - 1; k >= 1; k--) {
					int nr = cur.x + dr[dir] * k;
					int nc = cur.y + dc[dir] * k;

					if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {
						break;
					} else if (map[nr][nc] == 6) {
						break;
					} else if (overlap[nr][nc] > 0) {// 다른 cctv에서 확인한 구역
						overlap[nr][nc]--;
						continue;
					}
					check[nr][nc] = false;
				}
			}
		} else if (map[cur.x][cur.y] == 2) {
			int[][] overlap = new int[R][C];
			for (int dir = 0; dir < 2; dir++) {
				int k1 = 1, k2 = 1;
				for (k1 = 1; k1 < longer; k1++) {
					int nr = cur.x + dr[dir] * k1;
					int nc = cur.y + dc[dir] * k1;

					if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {
						break;
					} else if (map[nr][nc] == 6) {
						break;
					} else if (check[nr][nc] == true) {
						overlap[nr][nc]++;
						continue;
					}
					check[nr][nc] = true;
				}
				for (k2 = 1; k2 < longer; k2++) {
					int nr = cur.x + dr[dir + 2] * k2;
					int nc = cur.y + dc[dir + 2] * k2;

					if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {
						break;
					} else if (map[nr][nc] == 6) {
						break;
					} else if (check[nr][nc] == true) {
						overlap[nr][nc]++;
						continue;
					}
					check[nr][nc] = true;
				}
				backtracking(check, idx + 1);
				for (k1 = 1; k1 < longer; k1++) {
					int nr = cur.x + dr[dir] * k1;
					int nc = cur.y + dc[dir] * k1;

					if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {
						break;
					} else if (map[nr][nc] == 6) {
						break;
					} else if (overlap[nr][nc] > 0) {
						overlap[nr][nc]--;
						continue;
					}
					check[nr][nc] = false;
				}
				for (k2 = 1; k2 < longer; k2++) {
					int nr = cur.x + dr[dir + 2] * k2;
					int nc = cur.y + dc[dir + 2] * k2;

					if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {
						break;
					} else if (map[nr][nc] == 6) {
						break;
					} else if (overlap[nr][nc] > 0) {
						overlap[nr][nc]--;
						continue;
					}
					check[nr][nc] = false;
				}
			}
		} else if (map[cur.x][cur.y] == 3) {
			for (int dir = 0; dir < 4; dir++) {
				int[][] overlap = new int[R][C];
				int k = 1;
				for (int angle = 0; angle <= 1; angle++) {
					for (k = 1; k < longer; k++) {
						int nr = cur.x + dr[(dir + angle) % 4] * k;
						int nc = cur.y + dc[(dir + angle) % 4] * k;

						if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {// 범위 제한
							break;
						} else if (map[nr][nc] == 6) {// 벽이 있거나
							break;
						} else if (check[nr][nc] == true) {// 이미 감시하는 곳이라면
							overlap[nr][nc]++;
							continue;
						}
						check[nr][nc] = true;
					}
				}
				backtracking(check, idx + 1);
				for (int angle = 0; angle <= 1; angle++) {
					for (k = 1; k < longer; k++) {
						int nr = cur.x + dr[(dir + angle) % 4] * k;
						int nc = cur.y + dc[(dir + angle) % 4] * k;

						if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {// 범위 제한
							break;
						} else if (map[nr][nc] == 6) {// 벽이 있거나
							break;
						} else if (overlap[nr][nc] > 0) {// 이미 감시하는 곳이라면
							overlap[nr][nc]--;
							continue;
						}
						check[nr][nc] = false;
					}
				}
			}
		} else if (map[cur.x][cur.y] == 4) {
			for (int dir = 0; dir < 4; dir++) {
				int[][] overlap = new int[R][C];
				int k = 1;
				for (int angle = 0; angle <= 2; angle++) {
					for (k = 1; k < longer; k++) {
						int nr = cur.x + dr[(dir + angle) % 4] * k;
						int nc = cur.y + dc[(dir + angle) % 4] * k;
						if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {// 범위 제한
							break;
						} else if (map[nr][nc] == 6) {// 벽이 있거나
							break;
						} else if (check[nr][nc] == true) {// 이미 감시하는 곳이라면
							overlap[nr][nc]++;
							continue;
						}
						check[nr][nc] = true;
					}
				}
				backtracking(check, idx + 1);
				for (int angle = 0; angle <= 2; angle++) {
					for (k = 1; k < longer; k++) {
						int nr = cur.x + dr[(dir + angle) % 4] * k;
						int nc = cur.y + dc[(dir + angle) % 4] * k;

						if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {// 범위 제한
							break;
						} else if (map[nr][nc] == 6) {// 벽이 있거나
							break;
						} else if (overlap[nr][nc] > 0) {// 이미 감시하는 곳이라면
							overlap[nr][nc]--;
							continue;
						}
						check[nr][nc] = false;
					}
				}
			}
		} else if (map[cur.x][cur.y] == 5) {
			for (int dir = 0; dir < 4; dir++) {
				for (int k = 1; k < longer; k++) {
					int nr = cur.x + dr[dir] * k;
					int nc = cur.y + dc[dir] * k;

					if (!(nr >= 0 && nc >= 0 && nr < R && nc < C)) {
						break;
					} else if (map[nr][nc] == 6) {
						break;
					}
					check[nr][nc] = true;
				}
			}
			backtracking(check, idx + 1);
		}
	}
}
