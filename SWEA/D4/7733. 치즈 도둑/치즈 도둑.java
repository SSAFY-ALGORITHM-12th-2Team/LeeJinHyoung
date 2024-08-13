import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class cheese implements Comparable<cheese> {// cheese 오염시키기 위한 class
	public cheese(int r, int c, int val) {
		super();
		this.r = r;
		this.c = c;
		this.val = val;
	}

	int r;
	int c;
	int val;

	@Override
	public int compareTo(cheese o) {// 하루가 지나면 앞에 있는 값들을 오염시킬 예정
		// TODO Auto-generated method stub
		return this.val - o.val;
	}
}

class pos {// bfs 하기 위한 class
	int r;
	int c;

	public pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Solution {
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());// TC 갯수

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());// 정사각형 변 길이

			int[][] refri = new int[N][];// 냉장고
			ArrayList<cheese> info = new ArrayList<cheese>();// 상한 치즈를 뽑아낼 info ArrayList
			for (int i = 0; i < N; i++) {
				refri[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();// 입력 받고
				for (int j = 0; j < N; j++) {
					info.add(new cheese(i, j, refri[i][j]));// 행,열,refri value값 계산
				}
			}
			Collections.sort(info);// 정렬해야 val(날짜) 순서대로 뽑히므로

			int max_chunk = 0;// 일 별 가장 많은 조각
			for (int day = 0; day <= 100; day++) {// 1일부터 100일을 돌아야 하므로
				// 하루 동안 전체 행을 돌아야 하므로

				while (!info.isEmpty() && info.get(0).val == day) {// 비어 있지 않고 val이 day와 같다면
					cheese today = info.remove(0);// 리스트에서 추출해서
					refri[today.r][today.c] = -1;// 상하게 만들고
				} // 치즈가 상하는 알고리즘 필요

				int day_chunk = 0;// 그날그날 덩어리 양
				boolean[][] vis = new boolean[N][N];// 방문 배열
				Queue<pos> q = new ArrayDeque<>();// bfs를 시행할 q

				for (int row = 0; row < N; row++) {
					for (int col = 0; col < N; col++) {// 2차원 배열을 순회하면서
						if (refri[row][col] != -1 && vis[row][col] == false) {// 상하지 않았고 아직 방문하지 않았다면 q에 넣는다
							day_chunk++;// bfs의 시작점 덩어리의 시작점
							q.add(new pos(row, col));// 시작점 q에 넣고
							vis[row][col] = true;// 방문 배열 체크

							while (!q.isEmpty()) {
								pos cur = q.poll();

								for (int dir = 0; dir < 4; dir++) {
									int nr = cur.r + dr[dir];
									int nc = cur.c + dc[dir];

									if (nr >= 0 && nc >= 0 && nr < N && nc < N) {// 범위 계산
										if (refri[nr][nc] != -1 && vis[nr][nc] == false) {// 4방탐색후 방문하지 않았고 상하지 않았으면 진출
											q.add(new pos(nr, nc));
											vis[nr][nc] = true;
										}
									}
								}
							}
						}
					}
				}
				max_chunk = max_chunk > day_chunk ? max_chunk : day_chunk;// max_chunk와 비교해서 크면 day_chunk로 교체
			}
			System.out.println("#" + tc + " " + max_chunk);// 정답 출력
		}
	}
}