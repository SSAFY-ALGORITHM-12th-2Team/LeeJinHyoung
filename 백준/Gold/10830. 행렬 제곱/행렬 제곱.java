import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static long B;
	private static HashMap<Long, int[][]> powerStore;

	public static int[][] multiply(int[][] A, int[][] B) {
		int n = A.length; // A의 행
		int m = A[0].length; // A의 열 = B의 행
		int p = B[0].length; // B의 열

		// 크기 검사
		if (B.length != m) {
			throw new IllegalArgumentException("행렬 크기가 맞지 않습니다: A는 " + n + "x" + m + ", B는 " + B.length + "x" + p);
		}

		int[][] C = new int[n][p];

		// C[i][j] = sum_k A[i][k] * B[k][j]
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				int sum = 0;
				for (int k = 0; k < m; k++) {
					sum += A[i][k] * B[k][j];
				}
				C[i][j] = sum % 1000;
			}
		}
		return C;
	}

	private static int[][] power(long powerNum) {
		if (powerStore.containsKey(powerNum)) {
			return powerStore.get(powerNum);
		}

		if (powerNum % 2 == 0) {
			int[][] front = power(powerNum / 2);
			int[][] result = multiply(front, front);
			if (!powerStore.containsKey(powerNum / 2))
				powerStore.put(powerNum / 2, front);

			powerStore.put(powerNum, result);
			return result;
		} else {
			int[][] front = power(powerNum / 2);
			int[][] back = multiply(front, powerStore.get((long) 1));
			int[][] result = multiply(front, back);
			if (!powerStore.containsKey(powerNum / 2))
				powerStore.put(powerNum / 2, front);
			if (!powerStore.containsKey(powerNum / 2 + 1))
				powerStore.put(powerNum / 2 + 1, back);
			return result;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[] condInput = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		N = (int) condInput[0];
		B = condInput[1];

		int[][] matrix = new int[N][N];
		powerStore = new HashMap<>();
		for (int i = 0; i < N; i++) {
			matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		powerStore.put((long) 1, matrix);
		int[][] answer = power(B);
		StringBuilder ans = new StringBuilder();
		for (int[] row : answer) {
			for (int col : row) {
				ans.append(col % 1000 + " ");
			}
			ans.append("\n");
		}
		System.out.println(ans);
	}
}