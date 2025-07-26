import java.util.*;
import java.io.*;

public class Main {
	private static List<Integer> primeNumber;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		/*
		 * 1. 우선 소수를 구해야 한다. O(nlogn)
		 * 2. 소수를 구한 후 해당 숫자들 이하로 조합을 해 해당 합이 구할 수 있는지 확인한다. 재귀
		 */
		primeNumber = new ArrayList<>();
		boolean[] primeNumberchecked = new boolean[N + 1];

//		O(nlogn)
		for (int multiple = 2; multiple <= N; multiple++) {
			for (int idx = multiple; idx <= N; idx += multiple) {
				if (idx == multiple)
					continue;
				primeNumberchecked[idx] = true;
			}
		}

//		소수를 구해 primeNumber에 넣었다 : O(n)
		for (int i = 2; i <= N; i++) {
			if (!primeNumberchecked[i])
				primeNumber.add(i);
		}

		/*
		 * 좌 우측 인덱스와 그 합을 모두 구해서
		 * 합이 부족하면 오른쪽을 늘려 합을 증가
		 * 부족하면 left를 올려 합을 줄인다.
		 */
		int left = 0;
		int right = 0;
		long sum = 2;

		int answer = 0;

		while (left < primeNumber.size()) {
			if (sum < N) {
				if (right + 1 == primeNumber.size()) {
					left++;
					continue;
				}
				right++;
				sum += primeNumber.get(right);
			} else if (sum == N) {
				answer++;
				if (right + 1 == primeNumber.size()) {
					left++;
					continue;
				}
				right++;
				sum += primeNumber.get(right);
			} else if (sum > N) {
				sum -= primeNumber.get(left);
				left++;
				if (sum > N)
					continue;
			}
		}
		System.out.println(answer);
	}
}