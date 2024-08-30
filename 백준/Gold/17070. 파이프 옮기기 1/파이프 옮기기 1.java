import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int N;

	static int dr[] = { 0, 1, 1 };
	static int dc[] = { 1, 1, 0 };
	static int caseCnt = 0;
	static int[][] home;

	public static void backtracking(Point head, Point tail, int dir) {
		if (head.x == N - 1 && head.y == N - 1) {
			caseCnt++;
			return;
		}
//		System.out.println(head.x + " " + head.y);
//		System.out.println(tail.x + " " + tail.y);
//		System.out.println(dir);
//		System.out.println("caseCnt" + " " + caseCnt);
		int nr = 0, nc = 0;
		if (dir == 0) {// 가로로 놓였을 때는 2가지만
			for (int headDir = 0; headDir < 2; headDir++) {
				nr = head.x + dr[headDir];
				nc = head.y + dc[headDir];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					if (nr == head.x && home[nr][nc] != 1) {
						backtracking(new Point(nr, nc), head, 0);
					} else {
						if (head.x + 1 < N && head.y + 1 < N && home[head.x + 1][head.y + 1] == 0
								&& home[head.x + 1][head.y] == 0 && home[head.x][head.y + 1] == 0) {
							backtracking(new Point(nr, nc), head, 2);
						}
					}
				}
			}
		} else if (dir == 1) {
			for (int headDir = 1; headDir < 3; headDir++) {
				nr = head.x + dr[headDir];
				nc = head.y + dc[headDir];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					if (nc == head.y && home[nr][nc] != 1) {
						backtracking(new Point(nr, nc), head, 1);
					} else {
						if (head.x + 1 < N && head.y + 1 < N && home[head.x + 1][head.y + 1] == 0
								&& home[head.x + 1][head.y] == 0 && home[head.x][head.y + 1] == 0) {
							backtracking(new Point(nr, nc), head, 2);
						}
					}
				}
			}
		} else if (dir == 2) {// 대각선으로 놓였을 때도 3가지만
			for (int headDir = 0; headDir < 3; headDir++) {
				nr = head.x + dr[headDir];
				nc = head.y + dc[headDir];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					if (nr == head.x && home[nr][nc] != 1) {
						backtracking(new Point(nr, nc), head, 0);
					} else if (nc == head.y && home[nr][nc] != 1) {
						backtracking(new Point(nr, nc), head, 1);
					} else {
						if (head.x + 1 < N && head.y + 1 < N && home[head.x + 1][head.y + 1] == 0
								&& home[head.x + 1][head.y] == 0 && home[head.x][head.y + 1] == 0) {
							backtracking(new Point(nr, nc), head, 2);
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		home = new int[N][N];
		for (int i = 0; i < N; i++) {
			home[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		// 반드시 머리 부분으로 꼬리가 와야한다
		int dir = 0;// 0이면 가로,1이면 세로,2이면 대각선

		// 전략 : 백트래킹
		// 머리 부분에서 갈 수 있는 곳을 확인 후 가지를 뻗고
		// 머리 부분이었던 곳을 꼬리로 옮긴다
		// 깊은 복사로 처리한다
		backtracking(new Point(0, 1), new Point(0, 0), 0);
		System.out.println(caseCnt);
	}
}
