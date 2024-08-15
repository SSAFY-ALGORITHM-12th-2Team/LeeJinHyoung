import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	int r, c;

	public pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Solution {
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int R, C;
	static String[][] map;
	static int max_spot;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			max_spot = Integer.MIN_VALUE;
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			R = input[0];
			C = input[1];
			map = new String[R][C];

			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().split("");
			}

			boolean[] alphabet = new boolean[26];
			boolean[][] vis = new boolean[R][C];

			// 0,0 시작
			// 4방탐색후 들어가지 않은 곳이면 들어가기

			int spot = 1;// 가볼 명물 수
			vis[0][0] = true;
			alphabet[map[0][0].charAt(0) - 65] = true;
			dfs(new pos(0, 0), alphabet, vis, spot);// 매개 변수로 쓰는 거는 들어갈 때마다 값이 변하는 것들
			System.out.println("#" + tc + " " + max_spot);
		}
	}

	private static void dfs(pos cur, boolean[] alphabet, boolean[][] vis, int spot) {
		// TODO Auto-generated method stub
		max_spot = spot > max_spot ? spot : max_spot;
		for (int dir = 0; dir < 4; dir++) {
			int nr = cur.r + dr[dir];
			int nc = cur.c + dc[dir];

			if (nr >= 0 && nc >= 0 && nr < R && nc < C) {// 4방 탐색의 범위 제한
				if (alphabet[map[nr][nc].charAt(0) - 65] == false && vis[nr][nc] == false) {
					// 명물을 보지 않았고 방문도 하지 않았으면
					alphabet[map[nr][nc].charAt(0) - 65] = true;// 보고
					vis[nr][nc] = true;// 방문 체크
					dfs(new pos(nr, nc), alphabet, vis, spot + 1);// 들어가고
					vis[nr][nc] = false;// 방문 해제
					alphabet[map[nr][nc].charAt(0) - 65] = false;// 나왔을 때는 false 처리(안 그러면 이전 방문이 영향을 미친다)
				}
			}
		}
	}
}