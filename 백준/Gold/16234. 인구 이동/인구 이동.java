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

public class Main {
	public static int[][] map;
	public static int N, L, R;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = input[0];
		L = input[1];
		R = input[2];

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		} // 입력 완료

		int Answer = 0;
		// 모든 일자의 방문 배열
		// BFS와 방문 배열를 동시에 쓰면 해결 가능
		boolean day_move = true;
		while (true) {
			day_move = false;// while문 한바퀴 돌면 하루
			boolean[][] vis = new boolean[N][N];
			for (int r = 0; r < N; r++) {// vis을 탐지할 행
				for (int c = 0; c < N; c++) {// vis을 탐지할 열
					if (vis[r][c] == false) {
						Queue<pos> val_change = new LinkedList<pos>();

						Queue<pos> q = new LinkedList<>();// bfs q
						q.add(new pos(r, c));// 초기 시작점
						vis[r][c] = true;
						int population = 0;// 각 일자에서 연합의 인구
						int country = 0;// 각 일자에서 국가의 갯수
						while (!q.isEmpty()) { // BFS 시작
							pos cur = q.poll();
							for (int dir = 0; dir < 4; dir++) {
								int nr = cur.r + dr[dir];
								int nc = cur.c + dc[dir];

								if (nr >= 0 && nc >= 0 && nr < N && nc < N
										&& vis[nr][nc] == false) {/*
																	 * * bfs 기본 조건 : 여기까지 하면 모든 칸의 합이 주어진다
																	 */
									if (Math.abs(map[cur.r][cur.c] - map[nr][nc]) >= L
											&& Math.abs(map[cur.r][cur.c] - map[nr][nc]) <= R) {
										q.offer(new pos(nr, nc));
										vis[nr][nc] = true;
										val_change.add(new pos(nr, nc));
										day_move = true;
									}
								}
							}
							population += map[cur.r][cur.c];
							country++;
						}
						if (!val_change.isEmpty())
							val_change.add(new pos(r, c));
						
						int avg = population / country;// 평균
						while (!val_change.isEmpty()) {
							pos cur = val_change.poll();
							map[cur.r][cur.c] = avg;
						} // 인구 이동 시작
					}
				}
			}
			
			if (day_move == false) {
				break;
			}
			Answer++;
		}
		System.out.println(Answer);// 문제는 여전히 모든 배열을 전체 방문하고 있음
		// 최종 검사를 할 지말지는 이따가
	}
}