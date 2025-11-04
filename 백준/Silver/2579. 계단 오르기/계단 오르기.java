import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
//		연속으로 밟지 않는다면 0, 연속으로 밟는다면 1
		int[][] stairMemo = new int[N][2];
		for (int i = 0; i < N; i++)
			input[i] = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.println(input[0]);
			return;
		}
//		점화식 : f(x)=f(x)+max(f(x-1),f(x-2))
		stairMemo[0][0] = input[0];
		stairMemo[0][1] = input[0];
		stairMemo[1][0] = input[1];
		stairMemo[1][1] = input[0] + input[1];
		for (int i = 2; i < N; i++) {
			stairMemo[i][0] = Math.max(stairMemo[i - 2][0], stairMemo[i - 2][1]) + input[i];
			stairMemo[i][1] = stairMemo[i - 1][0] + input[i];
		}
		System.out.println(Math.max(stairMemo[N - 1][0], stairMemo[N - 1][1]));
	}
}
