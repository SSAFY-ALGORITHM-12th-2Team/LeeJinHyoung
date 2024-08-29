import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Solution {
	static int N = 0;
	static int[][] board;
	static int[][] draw;
	static ArrayList<Point> corelist;

	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };
	static Point[] coreChoice;

	static int lineMin;

	public static void backtracking(Point cur, int lineSum, int idx) {// 만약 하나라도 연결 안된다면 return 한다
		int[][] origin = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				origin[i][j] = draw[i][j];
			}
		}
		if (idx == coreChoice.length - 1) {
			for (int dir = 0; dir < 4; dir++) {
				boolean dirpossible = true;
				int line = 0;
				for (int k = 1; k < N; k++) {
					int nr = cur.x + dr[dir] * k;
					int nc = cur.y + dc[dir] * k;

					if (!(nr >= 0 && nc >= 0 && nr < N && nc < N)) {
						break;
					} else if (draw[nr][nc] == 1) {
						dirpossible = false;
						break;
					} else if (draw[nr][nc] == 2) {
						dirpossible = false;
						break;
					} else {
						line++;
						draw[nr][nc] = 2;
					}
				}

				if (dirpossible) {
					lineMin = Math.min(lineMin, lineSum + line);
				}
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						System.out.print(draw[i][j]);
//					}
//					System.out.println();
//				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						draw[i][j] = origin[i][j];
					}
				}
			}
			// System.out.println(lineSum);
			return;
		}
		for (int dir = 0; dir < 4; dir++) {
			boolean dirpossible = true;
			int line = 0;
			for (int k = 1; k < N; k++) {
				int nr = cur.x + dr[dir] * k;
				int nc = cur.y + dc[dir] * k;

				if (!(nr >= 0 && nc >= 0 && nr < N && nc < N)) {
					dirpossible = true;
					break;
				} else if (draw[nr][nc] == 1) {
					dirpossible = false;
					break;
				} else if (draw[nr][nc] == 2) {
					dirpossible = false;
					break;
				} else {
					line++;
					draw[nr][nc] = 2;
				}
			}
			if (dirpossible)
				backtracking(coreChoice[idx + 1], lineSum + line, idx + 1);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					draw[i][j] = origin[i][j];
				}
			}
		}
	}

	public static void combi(Point[] core, boolean[] used, int cnt, int start) {
		if (cnt == core.length) {
			coreChoice = core;
			backtracking(coreChoice[0], 0, 0);
//			for (int i = 0; i < coreChoice.length; i++) {
//				System.out.print(coreChoice[i].x + " " + coreChoice[i].y);
//			}
//			System.out.println();
			return;
		}
		for (int i = start; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				core[cnt] = corelist.get(i);
				combi(core, used, cnt + 1, i + 1);
				used[i] = false;
			}
		}
	}// 이 함수에서는 코어수가 다른 코어 리스트 조합이 있다

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			lineMin = Integer.MAX_VALUE;// 사용한 전선 갯수
			board = new int[N][N];// 최초 입력
			draw = new int[N][N];// 상태 저장 배열
			corelist = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int j = 0; j < N; j++) {
					if (board[i][j] == 1) {
						draw[i][j] = 1;
						if (!(i == 0 || j == 0 || i == N - 1 || j == N - 1) && board[i][j] == 1) {
							corelist.add(new Point(i, j));
						}
					}
				}
			}
			// 1번 최대한 많은 코어를 연결해야 한다
			// 2번 그 코어 중 가장 전선의 길이가 짧은 것을 구한다
			for (int size = corelist.size(); size > 0; size--) {
				//System.out.println(size);
				combi(new Point[size], new boolean[corelist.size()], 0, 0);
				if (lineMin != Integer.MAX_VALUE) {// 값의 변경이 이루어졌다는 의미
					break;
				}
			}
			System.out.println("#" + tc + " " + (lineMin));
		}
	}
}