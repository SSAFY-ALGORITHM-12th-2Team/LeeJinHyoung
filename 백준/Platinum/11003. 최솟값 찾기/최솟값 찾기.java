import java.util.*;
import java.io.*;

public class Main {
	static class node implements Comparable<node> {
		int index;
		int val;

		public node(int index, int val) {
			super();
			this.index = index;
			this.val = val;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			if (this.val == o.val)
				return this.index - o.index;
			return this.val - o.val;
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] NL = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = NL[0];
		int L = NL[1];

		Deque<node> deque = new ArrayDeque<>();

		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < N; i++) {
			node cur = new node(i, A[i]);

			while (!deque.isEmpty() && deque.peekLast().val >= A[i]) {
				deque.pollLast();
			}

			deque.offerLast(cur);

			while (deque.peek().index <= i - L) {
				deque.pollFirst();
			}
			answer.append(deque.peekFirst().val + " ");
		}
		System.out.println(answer);
	}
}