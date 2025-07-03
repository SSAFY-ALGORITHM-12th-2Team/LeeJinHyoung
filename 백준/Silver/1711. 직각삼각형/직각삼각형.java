
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int N, answer;
	private static int[][] points;

	private static boolean dotZero(int[] a, int[] b, int[] c) {
		long abx = b[0] - a[0];
		long aby = b[1] - a[1];
		long acx = c[0] - a[0];
		long acy = c[1] - a[1];

		long dot = abx * acx + aby * acy;
		return dot == 0;
	}

	public static void combi(int[][] triangle, int start, int idx) {
		if (idx == 3) {
			if (dotZero(triangle[0], triangle[1], triangle[2]) || dotZero(triangle[1], triangle[2], triangle[0])
					|| dotZero(triangle[2], triangle[0], triangle[1])) {
				answer++;
			}
			return;
		}
		for (int i = start; i < N; i++) {
			triangle[idx] = points[i];
			combi(triangle, i + 1, idx + 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		points = new int[N][2];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			points[i][0] = Integer.parseInt(input[0]);
			points[i][1] = Integer.parseInt(input[1]);
		}

		answer = 0;
		combi(new int[3][2], 0, 0);
		System.out.println(answer);
	}
}
