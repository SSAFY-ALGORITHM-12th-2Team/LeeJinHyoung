import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static class move implements Comparable<move> {
		int time;
		int position;

		public move(int time, int position) {
			super();
			this.time = time;
			this.position = position;
		}

		@Override
		public int compareTo(move o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = input[0];
		int K = input[1];

		int[] position = new int[100001];
		Arrays.fill(position, Integer.MAX_VALUE);
		position[N] = 0;
		// 경우의 수는 총 3개 +1 ,-1 *2
//		결국은 순간이동으로 절댓갑스이 차가 가장 적은 곳으로 이동하는 게 핵심

		Queue<move> q = new ArrayDeque<move>();
		q.offer(new move(0, N));
		while (!q.isEmpty()) {
			move cur = q.poll();

			if (cur.position < 0 || cur.position >= position.length)
				continue;

			if (cur.position + 1 < position.length && position[cur.position + 1] > cur.time + 1) {
				q.offer(new move(cur.time + 1, cur.position + 1));
				position[cur.position + 1] = Math.min(position[cur.position] + 1, position[cur.position + 1]);
			}
			if (cur.position - 1 >= 0 && position[cur.position - 1] > cur.time + 1) {
				q.offer(new move(cur.time + 1, cur.position - 1));
				position[cur.position - 1] = Math.min(position[cur.position] + 1, position[cur.position - 1]);
			}
			if (cur.position * 2 < position.length && position[cur.position * 2] > cur.time) {
				q.offer(new move(cur.time, cur.position * 2));
				position[cur.position * 2] = Math.min(position[cur.position], position[cur.position * 2]);
			}
		}
		System.out.println(position[K]);
	}
}
