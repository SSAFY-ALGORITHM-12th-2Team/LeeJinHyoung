import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static HashMap<Integer, Integer> ladder;
	private static HashMap<Integer, Integer> snake;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] NM = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = NM[0];
		int M = NM[1];

		ladder = new HashMap<>();
		snake = new HashMap<>();

		// 사다리 입력
		for (int i = 0; i < N; i++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			ladder.put(input[0], input[1]);
		}

		// 뱀 입력
		for (int i = 0; i < M; i++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			snake.put(input[0], input[1]);
		}

		// BFS 시작
		System.out.println(bfs(1));
	}

	private static int bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);

		int[] time = new int[101]; // 1부터 100까지 시간 기록
		Arrays.fill(time, -1); // 방문하지 않은 칸은 -1
		time[start] = 0;

		while (!q.isEmpty()) {
			int cur = q.poll();

			// 주사위 1~6 눈을 던져서 이동
			for (int plus = 1; plus <= 6; plus++) {
				int next = cur + plus;

				// 100을 넘으면 안됨
				if (next > 100) {
					continue;
				}

				// 사다리 칸이라면 그 끝으로 바로 이동
				if (ladder.containsKey(next)) {
					next = ladder.get(next);
				}
				// 뱀 칸이라면 그 끝으로 바로 이동
				if (snake.containsKey(next)) {
					next = snake.get(next);
				}

				// 방문하지 않았다면 그 칸을 큐에 추가
				if (time[next] == -1) {
					time[next] = time[cur] + 1;
					q.offer(next);
				}
			}
		}
		return time[100];
	}
}
