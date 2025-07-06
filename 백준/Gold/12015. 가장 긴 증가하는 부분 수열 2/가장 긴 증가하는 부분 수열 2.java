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
			return Integer.compare(this.val, o.val);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		List<node> memo = new ArrayList<node>();

		for (int i = 0; i < A.length; i++) {
			node current = new node(i, A[i]);
			if (memo.isEmpty() || memo.get(memo.size() - 1).val < A[i])
				memo.add(current);
			else {
				int index = Collections.binarySearch(memo, current);
				if (index < 0)
					index = -index - 1;
				memo.set(index, current);
			}
		}
		System.out.println(memo.size());
	}
}