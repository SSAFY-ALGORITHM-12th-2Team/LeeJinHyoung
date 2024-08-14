import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] ans;// 정답 배열
	static int[] arr;// 재료 배열
	static boolean[] isused;

	public static void recursive(int cnt) {
		if (cnt == M) {
			for (int e : ans) {
				System.out.print(e + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < N; i++) {
			if (isused[arr[i] - 1] == false && ans[cnt - 1]<arr[i]) {
				isused[arr[i] - 1] = true;
				ans[cnt] = arr[i];
				recursive(cnt + 1);
				isused[arr[i] - 1] = false;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();// 이게 1부터 N까지 수
		M = sc.nextInt();// 이게 길이가 M인 수열

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		ans = new int[M];
		isused = new boolean[N];

		for (int i = 0; i < N; i++) {
			if (isused[arr[i] - 1] == false) {
				isused[arr[i] - 1] = true;
				ans[0] = arr[i];
				recursive(1);
				isused[arr[i] - 1] = false;
			}
		}
	}
}