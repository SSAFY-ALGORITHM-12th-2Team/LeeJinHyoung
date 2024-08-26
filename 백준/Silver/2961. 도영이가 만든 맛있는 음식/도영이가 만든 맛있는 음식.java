import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int N;
	private static int min = Integer.MAX_VALUE;

	public static void power_set(int[][] source, boolean[] vis, int cnt) {
		if (cnt == N) {
			boolean flag = false;
			for (boolean b : vis) {
				if (b == true)
					flag = true;
			}
			if(flag==false) return;

			int sour = 1;
			int bitter = 0;

			for (int i = 0; i < N; i++) {
				if (vis[i] == true) {
					sour *= source[i][0];
					bitter += source[i][1];
				}
			}
			min = Math.min(Math.abs(sour - bitter), min);
			return;
		}
		vis[cnt] = true;
		power_set(source, vis, cnt + 1);
		vis[cnt] = false;
		power_set(source, vis, cnt + 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int source[][] = new int[N][2];
		boolean[] vis = new boolean[N];
		for (int i = 0; i < N; i++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			source[i][0] = input[0];
			source[i][1] = input[1];
		}

		for (int i = 0; i < N; i++) {
			vis[i] = true;
			power_set(source, vis, 1);
			vis[i] = false;
		}

		System.out.println(min);
	}
}