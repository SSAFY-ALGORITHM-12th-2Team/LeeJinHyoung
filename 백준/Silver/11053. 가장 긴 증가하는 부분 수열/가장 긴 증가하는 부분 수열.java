import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int[] memo = new int[N];
		Arrays.fill(memo, 1);

		int answer = 0;
		for (int end = 0; end < N; end++) {
			for (int start = 0; start < end; start++) {
				if (A[start] < A[end])
					memo[end] = Math.max(memo[end], memo[start] + 1);
			}
			answer = Math.max(answer, memo[end]);
		}
		System.out.println(answer);
	}
}