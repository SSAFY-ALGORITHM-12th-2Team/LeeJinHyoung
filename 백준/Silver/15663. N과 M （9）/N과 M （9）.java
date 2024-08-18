import java.util.*;
import java.io.*;

public class Main {
	private static int N, M;
	private static int arr[];
	private static Set<String> set = new LinkedHashSet<String>();

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

		int[] ans = new int[M];
		recursive(ans, new boolean[N], 0);
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String a = iter.next();
			String[] answer = a.split(" ");
			for(String e:answer) {
				System.out.print(e+" ");
			}
			System.out.println();
		}
	}

	private static void recursive(int[] ans, boolean[] vis, int cnt) {
		// TODO Auto-generated method stub
		if (cnt == M) {
//			for (int e : ans) {
//				System.out.print(e + " ");
//			}
//			System.out.println();
			String a = "";
			for (int i = 0; i < M; i++) {
				a += String.valueOf(ans[i]) + " ";
			}
			set.add(a);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (vis[i] == false) {
				vis[i] = true;
				ans[cnt] = arr[i];
				recursive(ans, vis, cnt + 1);
				vis[i] = false;
			}
		}
	}
}