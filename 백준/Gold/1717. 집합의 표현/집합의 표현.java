import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int[] parent;

	public static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	public static boolean union(int x, int y) {
		int aRoot = find(x);
		int bRoot = find(y);

		if (aRoot == bRoot) {
			return false;
		}
		if (aRoot <= bRoot) {
			parent[bRoot] = aRoot;
		} else {
			parent[aRoot] = bRoot;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int n = input[0];
		int m = input[1];
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			int[] oper = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (oper[0] == 0) {
				union(oper[1], oper[2]);
			} else if (oper[0] == 1) {
				if (find(oper[1]) == find(oper[2])) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}
}
