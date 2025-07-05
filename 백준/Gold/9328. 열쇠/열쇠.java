import java.util.*;
import java.util.regex.Pattern;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int answer = 0;
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int h = input[0], w = input[1];

			String[][] building = new String[h][w];
			HashMap<String, ArrayList<Point>> doors = new HashMap<>();
			Set<String> key = new HashSet<>();

			for (int row = 0; row < h; row++) {
				building[row] = br.readLine().split("");
				for (int col = 0; col < w; col++) {
					String cell = building[row][col];
					if (Pattern.matches("[A-Z]", cell)) {
						doors.computeIfAbsent(cell, k -> new ArrayList<>()).add(new Point(row, col));
					}
				}
			}

			String[] keyInput = br.readLine().split("");
			if (!keyInput[0].equals("0")) {
				Collections.addAll(key, keyInput);
			}

			boolean[][] visited;
			Deque<Point> q = new ArrayDeque<>();
			boolean changed;

			do {
				changed = false;
				visited = new boolean[h][w];
				q.clear();
				addInitialEntrances(building, visited, key, q);

				while (!q.isEmpty()) {
					Point cur = q.poll();
					int r = cur.x, c = cur.y;

					String cell = building[r][c];
					if (cell.equals("$")) {
						answer++;
						building[r][c] = ".";
					} else if (Pattern.matches("[a-z]", cell)) {
						if (!key.contains(cell)) {
							key.add(cell);
							changed = true;
							String door = cell.toUpperCase();
							if (doors.containsKey(door)) {
								for (Point d : doors.get(door)) {
									building[d.x][d.y] = ".";
								}
							}
						}
						building[r][c] = ".";
					} else if (Pattern.matches("[A-Z]", cell)) {
						if (!key.contains(cell.toLowerCase())) {
							continue;
						}
						building[r][c] = ".";
					}

					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
						if (visited[nr][nc]) continue;
						if (building[nr][nc].equals("*")) continue;

						q.offer(new Point(nr, nc));
						visited[nr][nc] = true;
					}
				}
			} while (changed);

			System.out.println(answer);
		}
	}

	private static void addInitialEntrances(String[][] building, boolean[][] visited, Set<String> key, Deque<Point> q) {
		int h = building.length;
		int w = building[0].length;
		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				if ((row == 0 || col == 0 || row == h - 1 || col == w - 1)) {
					if (visited[row][col]) continue;
					String cell = building[row][col];
					if (cell.equals(".") || cell.equals("$") || Pattern.matches("[a-z]", cell)
							|| (Pattern.matches("[A-Z]", cell) && key.contains(cell.toLowerCase()))) {
						visited[row][col] = true;
						q.offer(new Point(row, col));
					}
				}
			}
		}
	}
}
