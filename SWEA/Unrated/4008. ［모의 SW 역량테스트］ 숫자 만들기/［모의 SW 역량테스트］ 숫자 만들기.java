import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int N;
	static int[] order;
	static int[] number;
	static int min, max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			order = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			number = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			perm(new int[number.length - 1], order, 0);
			System.out.println("#" + tc + " " + (max - min));
		}
	}

	private static void perm(int[] sel, int[] order, int idx) {
		// TODO Auto-generated method stub
		if (idx == sel.length) {
			int sum = number[0];
			for (int i = 1; i < number.length; i++) {
				if (sel[i - 1] == 0) {
					sum += number[i];
				} else if (sel[i - 1] == 1) {
					sum -= number[i];
				} else if (sel[i - 1] == 2) {
					sum *= number[i];
				} else if (sel[i - 1] == 3) {
					sum /= number[i];
				}
			}
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (order[i] > 0) {
				order[i]--;
				sel[idx] = i;
				perm(sel, order, idx + 1);
				order[i]++;
			}
		}
	}
}