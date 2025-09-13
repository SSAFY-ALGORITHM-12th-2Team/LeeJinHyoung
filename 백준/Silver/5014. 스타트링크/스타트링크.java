import java.util.*;
import java.io.*;

public class Main {
	private static int F, S, G, U, D;

	static class floor {
		int prev;
		int cur;

		public floor(int prev, int cur) {
			super();
			this.prev = prev;
			this.cur = cur;
		}
	}

	private static int[] bfs(boolean[] vis, int[] move) {
		Arrays.fill(move, -1);
		vis[S] = true;
		move[S] = 0;
		Queue<floor> q = new ArrayDeque<>();
		if (S + U <= F)
			q.offer(new floor(S, S + U));
		if (S - D >= 1)
			q.offer(new floor(S, S - D));

		while (!q.isEmpty()) {
			floor curFloor = q.poll();

			if (S == G)
				break;
			else if (vis[curFloor.cur])
				continue;
			vis[curFloor.cur] = true;
			move[curFloor.cur] = move[curFloor.prev] + 1;
			int nu = curFloor.cur + U;
			int nd = curFloor.cur - D;
			if (nu <= F && !vis[nu]) {
				q.offer(new floor(curFloor.cur, nu));
			}
			if (nd >= 1 && !vis[nd]) {
				q.offer(new floor(curFloor.cur, nd));
			}
		}
		return move;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		F = input[0];
		S = input[1];
		G = input[2];
		U = input[3];
		D = input[4];

		int[] vis = bfs(new boolean[F + 1], new int[F + 1]);

		System.out.println(vis[G] == -1 ? "use the stairs" : vis[G]);
	}
}