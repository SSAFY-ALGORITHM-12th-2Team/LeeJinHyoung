import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] trees = new int[N];
			int ones = 0, twos = 0;
			
			int max_height = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				trees[i] = sc.nextInt();
				max_height = Math.max(max_height, trees[i]);
			}

			int days = 0;
			for(int i = 0; i < N; i++) {
				ones += (max_height - trees[i]) % 2;
				twos += (max_height - trees[i]) / 2;
			}
			
			int mins = Math.min(ones, twos);
			ones -= mins;
			twos -= mins;
			
			days += mins * 2;
			if(twos == 0)
				days += (ones - 1) * 2 + 1;
			if(ones == 0)
				days += twos + 1 + (twos - 1) / 3;
			
			System.out.println("#" + tc + " " + days);
			
		}
	}
}
