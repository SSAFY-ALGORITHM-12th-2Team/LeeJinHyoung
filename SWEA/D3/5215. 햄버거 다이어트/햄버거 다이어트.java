import java.util.Arrays;
import java.util.Scanner;

class Burger {
	int score;
	int calory;
}

public class Solution {
	static int N, L;
	static int max;

	static int[] input;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			L = sc.nextInt();
			Burger[] b = new Burger[N];
			max = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				b[i] = new Burger();
				b[i].score = sc.nextInt();
				b[i].calory = sc.nextInt();
			}

			input = new int[N];
			for (int j = 0; j < N; j++) {
				input[j] = 1;
				Arrays.sort(input);
				do {
					// System.out.println(Arrays.toString(input));
					int score = 0;
					int calory = 0;

					for (int idx = 0; idx < N; idx++) {
						if (input[idx] == 1) {
							score += b[idx].score;
							calory += b[idx].calory;
						}
					}
					if (calory <= L) {
						max = Math.max(score, max);
					}
				} while (np(input));
			}

			System.out.println("#" + tc + " " + max);
		}
	}

	static boolean np(int[] p) {
		int N = p.length;
		// step1) 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			--i;
		if (i == 0)
			return false;

		// step2)꼭대기 앞 교화위치에 교환할 값 자리 찾기(i-1위치 값보다 큰 값 중 가장 작은 값) 자리 찾기
		int j = N - 1;
		while (p[i - 1] >= p[j]) {
			--j;
		}
		// step3) 두 위치의 수 교환
		swap(p, i - 1, j);
		// step4) 꼭대기부터 맨 뒤까지 오름차순의 형태로 만듦
		int k = N - 1;
		while (i < k) {
			swap(p, i++, k--);
		}
		return true;
	}

	static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}
