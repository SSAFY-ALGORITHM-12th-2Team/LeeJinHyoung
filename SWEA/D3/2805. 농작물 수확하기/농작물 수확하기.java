import java.util.*;
import java.util.stream.Stream;
import java.awt.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] farm = new int[N][N];

			for (int i = 0; i < N; i++) {
				farm[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			}

			int sum = 0;

			for (int r = 0; r <= N / 2; r++) {
				for (int c = N / 2 - r; c <= (N / 2 - r) + 2 * r; c++) {
					sum += farm[r][c];
				}
			}
			for (int r = N / 2 + 1; r < N; r++) {
				for (int c = r - N / 2; c < N - (r - N / 2); c++) {
					sum += farm[r][c];
				}
			}
			System.out.println("#" + tc + " " + sum);
		}
	}
}