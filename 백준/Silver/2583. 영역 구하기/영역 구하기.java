import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	private static int answer, M, N;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static boolean[][] visited;

	private static int bfs(Point start, boolean[][] visited, boolean[][] isFilled) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		answer++;
		int square = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (visited[cur.x][cur.y])
				continue;
			square++;
			visited[cur.x][cur.y] = true;
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];

				if (nr < 0 || nc < 0 || nr >= M || nc >= N || visited[nr][nc] || isFilled[nr][nc])
					continue;
				q.offer(new Point(nr, nc));
			}

		}
		return square;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		M = input[0];
		N = input[1];
		int K = input[2];

		boolean[][] isFilled = new boolean[M][N];
		for (int i = 0; i < K; i++) {
			int[] edgeInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int startRow = edgeInput[1];
			int startCol = edgeInput[0];

			int endRow = edgeInput[3];
			int endCol = edgeInput[2];
			for (int row = startRow; row < endRow; row++) {
				for (int col = startCol; col < endCol; col++) {
					isFilled[row][col] = true;
				}
			}
		}
		answer = 0;
		visited = new boolean[M][N];
		List<Integer> square = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (isFilled[i][j] || visited[i][j])
					continue;
				square.add(bfs(new Point(i, j), visited, isFilled));
			}
		}
		Collections.sort(square);
		StringBuilder answerString = new StringBuilder();
		answerString.append(answer + "\n");
		for (int i : square) {
			answerString.append(i + " ");
		}
		System.out.println(answerString);
	}
}
