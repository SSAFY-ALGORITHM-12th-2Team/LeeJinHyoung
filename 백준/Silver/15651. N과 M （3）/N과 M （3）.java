import java.util.*;
import java.io.*;

public class Main {
	static int[] ans;
	static int[] arr;
	static boolean[] isused;
	static int N, M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void recursive(int cnt) throws IOException {
		if (cnt == M) {
			for (int e : ans) {
				bw.write(String.valueOf(e)+" ");
			}
			bw.write("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			ans[cnt] = arr[i];
			recursive(cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		ans = new int[M];
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		isused = new boolean[N];
		recursive(0);
		bw.flush();
	}
}