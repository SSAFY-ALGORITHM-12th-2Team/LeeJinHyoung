import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {
	static int N, M, D;
	static int[][] map;

	static int max;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = input[0];
		M = input[1];
		D = input[2];

		// 전략: 궁수들의 자리 배치가 달라질 수 있다.
		// 이 자리 배치에 따라서 효율적으로 잡을 수 있는 적의 위치가 달라질 수 있고
		// 열의 개수 에서 3개만 선택하자
		// 전투 시작하면서 행 하나씩 내리자
		map = new int[N + 1][M];

		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		}
		max = Integer.MIN_VALUE;
		combi(new int[3], new boolean[M], 0, 0);
		System.out.println(max);
	}

	private static void combi(int[] sel, boolean[] vis, int idx, int start) {
		if (idx == sel.length) {
			// 여기까지 해서 궁수가 배치될 수 있는 경우의 수를 구할 수 있다.
			// 그렇다면 이제 지도를 움직여서 시뮬레이션을 해야한다
			int[][] copyMap = new int[N + 1][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copyMap[i][j] = map[i][j];
				}
			} // 지도 복사본을 하나 만든다

			Point[] archor = new Point[3];
			for (int i = 0; i < 3; i++) {
				archor[i] = new Point(N, sel[i]);
				copyMap[N][sel[i]] = 2;
			} // 궁수를 배치한다.

			// 궁수 배치가 끝난다면 문제 설명에 따라서 궁수 공격을 하고 적을 이동 시킨다.
			int kill = game(copyMap, archor);
			max = Math.max(kill, max);

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(copyMap[i][j]);
//				}
//				System.out.println();
//			}
//			for (Point p : archor) {
//				System.out.println(p.x + " " + p.y);
//			}
			return;
		}
		for (int i = start; i < vis.length; i++) {
			if (!vis[i]) {
				vis[i] = true;
				sel[idx] = i;
				combi(sel, vis, idx + 1, i + 1);
				vis[i] = false;
			}
		}
	}

	public static int game(int[][] copyMap, Point[] archor) {
		int kill = 0;
		while (!end(copyMap)) {
//			for (int i = 0; i < copyMap.length; i++) {
//				for (int j = 0; j < copyMap[i].length; j++) {
//					System.out.print(copyMap[i][j]);
//				}
//				System.out.println();
//			}

			List<Point> enemy = new ArrayList();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copyMap[i][j] == 1)
						enemy.add(new Point(i, j));
				}
			} // enemyList 완성
			boolean[] killList = new boolean[enemy.size()];
			for (int i = 0; i < archor.length; i++) {
				int idx = Integer.MAX_VALUE;
				int dist = Integer.MAX_VALUE;
				for (int j = 0; j < enemy.size(); j++) {
					int entryDist = Math.abs(archor[i].x - enemy.get(j).x) + Math.abs(archor[i].y - enemy.get(j).y);
					if (entryDist <= D && entryDist < dist) {

						idx = j;
						dist = Math.abs(archor[i].x - enemy.get(j).x) + Math.abs(archor[i].y - enemy.get(j).y);
					} else if (entryDist <= D && entryDist == dist) {
						if (enemy.get(j).y < enemy.get(idx).y) {
							idx = j;
						}
					}
				}
				if (idx < enemy.size())
					killList[idx] = true;
			}
			for (int i = 0; i < killList.length; i++) {
				if (killList[i]) {
					kill++;
					copyMap[enemy.get(i).x][enemy.get(i).y] = 0;
				}
			}
			
			copyMap = move(copyMap);

		}
		return kill;
	}

	private static boolean end(int[][] copyMap) {
		// TODO Auto-generated method stub
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	private static int[][] move(int[][] copyMap) {
		// TODO Auto-generated method stub
		for (int c = 0; c < M; c++) {
			for (int r = N - 1; r > 0; r--) {
				copyMap[r][c] = copyMap[r - 1][c];
			}
			copyMap[0][c] = 0;
		}
		return copyMap;
	}
}