import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int N;
	static ArrayList<Integer>[] village;
	static int[] population;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		population = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		// 전략 1
		// 조합으로 마을 나누기
		// 마을 연결 확인 후
		// 인구 확인
		village = new ArrayList[N + 1];
		village[0] = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			village[i] = new ArrayList<>();
			for (int j = 1; j <= input[0]; j++) {
				village[i].add(input[j]);
			}
		}

		for (int size = 1; size <= N / 2; size++) {
			combi(new int[size], new boolean[N + 1], 0, 1);
		}
		if (min == Integer.MAX_VALUE) 
			System.out.println(-1);
		 else
			System.out.println(min);
	}

	private static void combi(int[] sel, boolean[] used, int cnt, int start) {
		// TODO Auto-generated method stub
		if (cnt == sel.length) {
			ArrayList<Integer> vil1 = new ArrayList<Integer>();
			ArrayList<Integer> vil2 = new ArrayList<Integer>();
			for (int i = 1; i < used.length; i++) {
				if (used[i] == true)
					vil1.add(i);
				if (used[i] == false)
					vil2.add(i);
			}
			if (isCon(vil1) && isCon(vil2)) {
				int pop1 = 0;
				int pop2 = 0;

				for (int i = 0; i < vil1.size(); i++) {
					pop1 += population[vil1.get(i) - 1];
				}
				for (int i = 0; i < vil2.size(); i++) {
					pop2 += population[vil2.get(i) - 1];
				}
				min = Math.min(Math.abs(pop1 - pop2), min);
			}
			return;
		}
		for (int i = start; i <= N; i++) {
			if (used[i] == false) {
				used[i] = true;
				sel[cnt] = i;
				combi(sel, used, cnt + 1, start + 1);
				used[i] = false;
			}
		}
	}

	private static boolean isCon(ArrayList<Integer> vil) {
		// TODO Auto-generated method stub

		Queue<Integer> q = new ArrayDeque();
		boolean vis[] = new boolean[N + 1];
		vis[vil.get(0)] = true;
		q.offer(vil.get(0));

		int cnt = 1;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < village[cur].size(); i++) {
				int next = village[cur].get(i);
				if (vil.contains(next) && !vis[next]) {
					q.offer(next);
					vis[next] = true;
					cnt++;
				}
			}
		}
		if (cnt == vil.size())
			return true;
		else
			return false;
	}

}
