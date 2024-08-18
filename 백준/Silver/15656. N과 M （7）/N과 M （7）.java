import java.util.*;
import java.io.*;

public class Main {
	private static int N, M;
	private static int[] arr;
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		recursive(new int[M], new boolean[M], 0);
		bw.flush();
	}

	private static void recursive(int[] ans, boolean[] vis, int cnt) throws IOException {
		// TODO Auto-generated method stub
		if (cnt == M) {
			for (int e : ans) {
				bw.write(e + " ");
			}
			bw.write("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			ans[cnt] = arr[i];
			recursive(ans, vis, cnt + 1);
		}
	}
}