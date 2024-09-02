import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	int r;
	int c;

	public pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Main {
	private static int N, M, D;
	private static int castle[][];
	static ArrayList<pos> enemy = new ArrayList<>();

	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = input[0];
		M = input[1];
		D = input[2];

		castle = new int[N + 1][M];

		for (int i = 0; i < N; i++) {
			castle[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < M; j++) {
				if (castle[i][j] == 1) {
					enemy.add(new pos(i, j));
				}
			}
		} // 입력 끝

		// 1. 궁수들을 배치할 수 있는 방법을 조합으로 뽑아내자
		combi(new int[3], new boolean[M], 0, 0);

		System.out.println(max);
	}

	private static void combi(int[] arr, boolean[] vis, int cnt, int start) {
		// TODO Auto-generated method stub
		if (cnt == 3) {
			/* 이 부분이 자료 복사하기 */
			int[][] copyCastle = new int[N + 1][M];
			ArrayList<pos> copyEnemy = new ArrayList<>();
			for (pos e : enemy) {
				copyEnemy.add(e);
			}
			for (int i = 0; i < copyCastle.length; i++) {
				for (int j = 0; j < copyCastle[i].length; j++) {
					copyCastle[i][j] = castle[i][j];
				}
			}
			/* 자료 복사하기 */
			for (int e : arr) {
				copyCastle[N][e] = 2;
			} // 궁수의 위치를 2로 표현
				// 이제 턴을 진행해야 한다
			int kill = 0;
			while (remainEnemy(copyCastle)) {// 적이 남아있을 때는
				// D 이하의 적을 제거가능한지 확인후 죽일 때는 한번에 죽인다
				// 하나씩 죽이면 오류남

				// 적은 가장 가까운 적을 죽이고 가장 가까운 적이 여럿이라면 왼쪽이다
				pos[] target = new pos[3];
				int idx = 0;
				for (int i = 0; i < M; i++) {
					if (copyCastle[N][i] == 2) {// 궁수라면
						int enemyDist = Integer.MAX_VALUE;
						for (int j = 0; j < copyEnemy.size(); j++) {
							int dist = dist(new pos(N, i), copyEnemy.get(j));
							if (D >= dist && enemyDist >= dist) {
								if (enemyDist == dist) {// 만약 기존 적과 거리가 같다면 더왼쪽 것을 선택
									target[idx] = target[idx].c < copyEnemy.get(j).c ? target[idx] : copyEnemy.get(j);
								} else {
									enemyDist = dist(new pos(N, i), copyEnemy.get(j));
									target[idx] = copyEnemy.get(j);
								}
							}
						}
						idx++;
					}
				}
				for (int i = 0; i < 3; i++) {
					if (target[i] == null)
						continue;
					if (copyCastle[target[i].r][target[i].c] == 1) {
						kill++;
						copyCastle[target[i].r][target[i].c] = 0;
					}
				}

				for (int i = N - 1; i > 0; i--) {
					for (int j = 0; j < M; j++) {
						copyCastle[i][j] = copyCastle[i - 1][j];
					}
				}
				for (int j = 0; j < M; j++) {
					copyCastle[0][j] = 0;
				}
				copyEnemy.clear();
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (copyCastle[i][j] == 1) {
							copyEnemy.add(new pos(i, j));
						}

					}
				}

//				for (int i = 0; i <= N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(copyCastle[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println(kill);
			}

			max = Math.max(kill, max);
			return;
		}
		for (int i = start; i < M; i++) {
			if (vis[i] == false) {
				vis[i] = true;
				arr[cnt] = i;
				combi(arr, vis, cnt + 1, i + 1);
				vis[i] = false;
			}
		}
	}

	private static int dist(pos archor, pos enemy) {
		return Math.abs(archor.r - enemy.r) + Math.abs(archor.c - enemy.c);
	}

	private static boolean remainEnemy(int[][] copyCastle) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyCastle[i][j] == 1)
					return true;
			}
		}
		return false;
	}
}