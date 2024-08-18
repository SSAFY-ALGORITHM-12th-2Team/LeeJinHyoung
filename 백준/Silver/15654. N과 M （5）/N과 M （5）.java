import java.util.*;
import java.io.*;

public class Main {
	private static int N, M;;
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

		recursive(new int[M], new boolean[N], 0);
	}

	private static void recursive(int[] ans, boolean[] sel, int cnt) {
		// TODO Auto-generated method stub
		if (cnt == M) {
			for (int e : ans) {
				System.out.print(e + " ");
			}
			System.out.println();

			return;
		}
		for (int i = 0; i < N; i++) {
			if (sel[i] == false) {
				sel[i] = true;
				ans[cnt] = arr[i];
				recursive(ans, sel, cnt + 1);
				sel[i] = false;
			}
		}
	}
}
