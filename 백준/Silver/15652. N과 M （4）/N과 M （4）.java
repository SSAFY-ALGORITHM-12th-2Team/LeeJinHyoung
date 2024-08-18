import java.util.*;
import java.io.*;

public class Main {

	private static int N, M;

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		boolean[] sel = new boolean[M];
		int[] ans = new int[M];

		recursive(ans, sel, 0, 1);

	}

	private static void recursive(int[] ans, boolean[] sel, int cnt, int val) {
		// TODO Auto-generated method stub
		if (cnt == M) {
			for (int e : ans) {
				System.out.print(e + " ");
			}
			System.out.println();
			return;
		}
		for (int i = val; i <= N; i++) {
			sel[cnt] = true;
			ans[cnt] = i;
			recursive(ans, sel, cnt + 1, i);
			sel[cnt] = false;
		}

	}
}