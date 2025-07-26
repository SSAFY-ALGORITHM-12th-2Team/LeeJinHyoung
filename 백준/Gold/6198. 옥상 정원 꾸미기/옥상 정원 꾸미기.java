import java.util.*;
import java.io.*;

public class Main {
	static class building {
		int index;
		int height;

		public building(int index, int height) {
			super();
			this.index = index;
			this.height = height;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Deque<building> stack = new ArrayDeque<>();

		long[] answer = new long[N];
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());

			if (stack.isEmpty())
				stack.addFirst(new building(i, input));
			else {
				if (stack.peekFirst().height <= input) {
					while (!stack.isEmpty() && stack.peekFirst().height <= input) {
						building cur = stack.pollFirst();

//						System.out.println("index " + cur.index + "height" + cur.height);
						answer[cur.index] = i - cur.index - 1;
					}
					stack.addFirst(new building(i, input));
				} else if (stack.peekFirst().height > input) {
					stack.addFirst(new building(i, input));
				}
			}
		}

		while (!stack.isEmpty()) {
			building cur = stack.pollFirst();

			answer[cur.index] = N - cur.index - 1;
		}

		System.out.println(Arrays.stream(answer).sum());
	}
}