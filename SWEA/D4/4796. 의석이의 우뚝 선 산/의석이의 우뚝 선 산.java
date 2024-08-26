import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] mountains = new int[N];

			for (int i = 0; i < N; i++) {
				mountains[i] = sc.nextInt();
			}

			int sum = 0;
			for (int idx = 1; idx < mountains.length - 1; idx++) {
				// 우선 좌우가 자신보다 낮은 산을 찾는다
				int prev = 1, next = 1;

				// 이전 가능한 구간 갯수 구하고
				// 이후 가능한 구간 갯수 구해서 곱하기
				if (mountains[idx] > mountains[idx - prev] && mountains[idx] > mountains[idx + next]) {
					while (idx - prev >= 0 && mountains[idx - prev] < mountains[idx - prev + 1]) {
						prev++;
					}
					while (idx + next < N && mountains[idx + next] < mountains[idx + next - 1]) {
						next++;
					}
				}
				// System.out.println(prev + " " + next);
				sum = sum + ((prev - 1) * (next - 1));
			}
			bw.write("#" + String.valueOf(tc) + " " + String.valueOf(sum) + "\n");
		}
		bw.flush();
	}
}