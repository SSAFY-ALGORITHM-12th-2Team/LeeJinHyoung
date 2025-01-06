import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int R, C, T;
	private static int[][] room;
	private static List<Point> machine;

	private static int[] dr = { 0, 1, 0, -1 }; // 오른쪽, 아래, 왼쪽, 위쪽
	private static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		R = input[0];
		C = input[1];
		T = input[2];

		room = new int[R][C];
		machine = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < C; j++) {
				room[i][j] = row[j];
				if (room[i][j] == -1) {
					machine.add(new Point(i, j));
				}
			}
		}

		for (int t = 0; t < T; t++) {
			spreadDust();
			activateAirPurifiers();
		}

		System.out.println(calculateTotalDust());
	}

	private static void spreadDust() {
		int[][] newRoom = new int[R][C];
		for (Point p : machine) {
			newRoom[p.x][p.y] = -1;
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (room[r][c] > 0) {
					int spreadAmount = room[r][c] / 5;
					int spreadCount = 0;

					for (int dir = 0; dir < 4; dir++) {
						int nr = r + dr[dir];
						int nc = c + dc[dir];

						if (nr >= 0 && nc >= 0 && nr < R && nc < C && room[nr][nc] != -1) {
							newRoom[nr][nc] += spreadAmount;
							spreadCount++;
						}
					}
					newRoom[r][c] += room[r][c] - (spreadAmount * spreadCount);
				}
			}
		}

		room = newRoom;
	}

	private static void activateAirPurifiers() {
		Point top = machine.get(0);
		Point bottom = machine.get(1);

		// 윗쪽 반시계 방향
		for (int r = top.x - 1; r > 0; r--)
			room[r][0] = room[r - 1][0];
		for (int c = 0; c < C - 1; c++)
			room[0][c] = room[0][c + 1];
		for (int r = 0; r < top.x; r++)
			room[r][C - 1] = room[r + 1][C - 1];
		for (int c = C - 1; c > 1; c--)
			room[top.x][c] = room[top.x][c - 1];
		room[top.x][1] = 0;

		// 아랫쪽 시계방향
		for (int r = bottom.x + 1; r < R - 1; r++)
			room[r][0] = room[r + 1][0];
		for (int c = 0; c < C - 1; c++)
			room[R - 1][c] = room[R - 1][c + 1];
		for (int r = R - 1; r > bottom.x; r--)
			room[r][C - 1] = room[r - 1][C - 1];
		for (int c = C - 1; c > 1; c--)
			room[bottom.x][c] = room[bottom.x][c - 1];
		room[bottom.x][1] = 0;
	}

	private static int calculateTotalDust() {
		int totalDust = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (room[r][c] > 0) {
					totalDust += room[r][c];
				}
			}
		}
		return totalDust;
	}
}
