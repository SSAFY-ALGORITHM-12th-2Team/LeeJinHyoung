
import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int R, C;
	private static String[][] board;
	private static Point moscow, zagrev;

	private static int[][] dr = { { -1, 1 }, { 0, 0 }, { 0, -1, 0, 1 }, { 1, 0 }, { -1, 0 }, { -1, 0 }, { 0, 1 } };
	private static int[][] dc = { { 0, 0 }, { -1, 1 }, { -1, 0, 1, 0 }, { 0, 1 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		R = input[0];
		C = input[1];

		board = new String[R][C];
		moscow = new Point();
		zagrev = new Point();
		for (int i = 0; i < R; i++) {
			String[] row = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				board[i][j] = row[j];
				if (board[i][j].equals("M")) {
					moscow.x = i;
					moscow.y = j;
				} else if (board[i][j].equals("Z")) {
					zagrev.x = i;
					zagrev.y = j;
				}
			}
		}
		// BFS로 가자
		bfs();
	}

	private static void bfs() {
		// TODO Auto-generated method stub
		Queue<Point> q = new ArrayDeque();
		boolean[][] vis = new boolean[R][C];

		q.offer(moscow);
		vis[moscow.x][moscow.y] = true;

		Point cur = null;
		while (!q.isEmpty()) {
			cur = q.poll();

			if (board[cur.x][cur.y].equals("M")) {
				if (board[cur.x][cur.y].equals("."))// .일 경우는 버린다.
					continue;

				int order = direction(board[cur.x][cur.y]);

				if (order != -1) {
					for (int dir = 0; dir < dr[order].length; dir++) {
						int nr = cur.x + dr[order][dir];
						int nc = cur.y + dc[order][dir];
						if (nr >= 0 && nc >= 0 & nr < R && nc < C && !board[nr][nc].equals(".") && !vis[nr][nc]) {
							vis[nr][nc] = true;
							q.offer(new Point(nr, nc));
						}
					}
				}
			} else {
				if (board[cur.x][cur.y].equals("."))// .일 경우는 버린다.
					break;

				int order = direction(board[cur.x][cur.y]);

				if (order != -1) {
					for (int dir = 0; dir < dr[order].length; dir++) {
						int nr = cur.x + dr[order][dir];
						int nc = cur.y + dc[order][dir];
						if (nr >= 0 && nc >= 0 & nr < R && nc < C && !vis[nr][nc]) {
							vis[nr][nc] = true;
							q.offer(new Point(nr, nc));
						}
					}
				}
			}
		} // 여기까지 끝나면 지워진 도착한 Point가 지워진 부분이다
		System.out.println((cur.x + 1) + " " + (cur.y + 1) + " " + judgement(cur));
		// 해당 셀에서 어떻게 해야 다른 칸으로 갈 수 있나?

		// 상하좌우 점이 아닌 블록 판별해서 맞는 블록을 넣는다.
	}

	private static String judgement(Point point) {
		// TODO Auto-generated method stub
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		// 상하좌우 순
		boolean[] line = new boolean[4];
		for (int dir = 0; dir < 4; dir++) {
			int nr = point.x + dr[dir];
			int nc = point.y + dc[dir];

			if (nr >= 0 && nc >= 0 && nr < R && nc < C /* 해당 셀에서 이 셀로 올 수 있는지 판단 */) {
				if (dir == 0) {
					if (board[nr][nc].equals("+") || board[nr][nc].equals("|") || board[nr][nc].equals("1")
							|| board[nr][nc].equals("4")) {
						line[dir] = true;
					}
				} else if (dir == 1) {
					if (board[nr][nc].equals("+") || board[nr][nc].equals("|") || board[nr][nc].equals("2")
							|| board[nr][nc].equals("3")) {
						line[dir] = true;
					}
				} else if (dir == 2) {
					if (board[nr][nc].equals("+") || board[nr][nc].equals("-") || board[nr][nc].equals("1")
							|| board[nr][nc].equals("2")) {
						line[dir] = true;
					}
				} else if (dir == 3) {
					if (board[nr][nc].equals("+") || board[nr][nc].equals("-") || board[nr][nc].equals("3")
							|| board[nr][nc].equals("4")) {
						line[dir] = true;
					}
				}
			}
		} // 귀찮네 여기서부터는 내일할까
		if (line[0] && line[1] && line[2] && line[3]) {
			return "+";
		} else if (line[0] && line[1]) {
			return "|";
		} else if (line[2] && line[3]) {
			return "-";
		} else if (line[1] && line[3]) {
			return "1";
		} else if (line[0] && line[3]) {
			return "2";
		} else if (line[0] && line[2]) {
			return "3";
		} else if (line[1] && line[2]) {
			return "4";
		}
		return null;
	}

	private static int direction(String input) {// 어떤 블록인지에 따라 방향을 결정
		switch (input) {
		case "|":
			return 0;
		case "-":
			return 1;
		case "+", "M":
			return 2;
		case "1":
			return 3;
		case "2":
			return 4;
		case "3":
			return 5;
		case "4":
			return 6;
		default:
			return -1;
		}
	}
}