import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int N, r, c;
	private static int square[][] = new int[14][14];

	private static int find(int N, int row, int column) {
		if (N == 0) {
			return 0;
		}
		int half = (int) Math.pow(2, N - 1);

		if (row < half && column < half) {// 1분면
			return find(N - 1, row, column);
		} else if (row < half && column >= half) {// 2분면
			return half * half + find(N - 1, row, column - half);
		} else if (row >= half && column < half) {// 3분면
			return 2 * half * half + find(N - 1, row - half, column);
		} else {
			return 3 * half * half + find(N - 1, row - half, column - half);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		// 15행 전체 배열을 찍는 것이 아닌 r행 C열을 찾아서 2*2 배열로서 풀어낸다.
		N = input[0];
		r = input[1];
		c = input[2];
		System.out.println(find(N, r, c));
	}
}