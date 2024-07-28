import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int[] A;
	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;

	static void func(int sum, int n, int p, int m, int t, int d) {
		if (Math.abs(sum) > 1000000000) {
			return;
		}
		if (n == A.length) {
			// System.out.println(sum);
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			// System.out.println(min+" "+max);
			return;
		}
		if (p > 0)
			func(sum + A[n], n + 1, p - 1, m, t, d);
		if (m > 0)
			func(sum - A[n], n + 1, p, m - 1, t, d);
		if (t > 0)
			func(sum * A[n], n + 1, p, m, t - 1, d);
		if (d > 0)
			func(sum / A[n], n + 1, p, m, t, d - 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] o = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		func(A[0], 1, o[0], o[1], o[2], o[3]);
		System.out.println(max);
		System.out.println(min);
	}
}