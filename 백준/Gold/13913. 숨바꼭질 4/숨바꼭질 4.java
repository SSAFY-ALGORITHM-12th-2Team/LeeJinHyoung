import java.util.*;

public class Main {
	private static int[] time;
	private static List<Integer> path;

	private static class Info {
		int time;
		int position;

		public Info(int time, int position) {
			super();
			this.time = time;
			this.position = position;
		}
	}

	private static int bfs(int N, int K) {
		if (N == K) {
			return 0;
		}

		Queue<Info> q = new ArrayDeque<Info>();
		q.offer(new Info(0, N));
		boolean[] vis = new boolean[100001];
		time = new int[100001];

		while (!q.isEmpty()) {
			Info cur = q.poll();

			if (vis[cur.position])
				continue;
			vis[cur.position] = true;
			time[cur.position] = cur.time;

			if (cur.position - 1 >= 0 && !vis[cur.position - 1])
				q.offer(new Info(cur.time + 1, cur.position - 1));
			if (cur.position + 1 < 100001 && !vis[cur.position + 1])
				q.offer(new Info(cur.time + 1, cur.position + 1));
			if (cur.position * 2 < 100001 && !vis[cur.position * 2])
				q.offer(new Info(cur.time + 1, cur.position * 2));
		}
		return time[K];
	}

	private static void backtracking(int N, int cur, List<Integer> p) {
		if (N == cur) {
			p.add(0, cur);
			path = p;
			return;
		}
		p.add(0, cur);
		if (cur + 1 < 100001 && time[cur + 1] == time[cur] - 1) {
			backtracking(N, cur + 1, p);
		} else if (cur - 1 >= 0 && time[cur - 1] == time[cur] - 1) {
			backtracking(N, cur - 1, p);
		} else if (cur / 2 >= 0 && time[cur / 2] == time[cur] - 1) {
			backtracking(N, cur / 2, p);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		int time = bfs(N, K);
		backtracking(N, K, new ArrayList<>());
		StringBuilder answer = new StringBuilder();
		for (int p : path) {
			answer.append(p + " ");
		}
		System.out.println(time + "\n" + answer);
	}
}