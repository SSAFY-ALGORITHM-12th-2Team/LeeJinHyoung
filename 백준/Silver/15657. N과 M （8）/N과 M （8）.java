import java.util.*;
import java.io.*;

public class Main {
	private static int N, M;
	private static int[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		recursive(new int[M], new boolean[N], 0, 0);
	}

	private static void recursive(int[] ans, boolean[] vis, int cnt, int idx) {
		// TODO Auto-generated method stub
		if (cnt == M) {
			for (int e : ans) {
				System.out.print(e + " ");
			}
			System.out.println();
			return;
		}
		for (int i = idx; i < N; i++) {
			ans[cnt] = arr[i];
			recursive(ans, vis, cnt + 1, i);
		}
	}
}
