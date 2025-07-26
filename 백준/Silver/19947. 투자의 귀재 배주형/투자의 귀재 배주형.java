import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int H = sc.nextInt();
		int Y = sc.nextInt();

		int[] memo = new int[Y + 1];
		memo[0] = H;

		for (int i = 1; i <= Y; i++) {
			int A = (int) (memo[i - 1] + memo[i - 1] * 0.05);
			int B = i >= 3 ? (int) (memo[i - 3] + memo[i - 3] * 0.2) : 0;
			int C = i >= 5 ? (int) (memo[i - 5] + memo[i - 5] * 0.35) : 0;
			memo[i] = Math.max(A, Math.max(B, C));
		}
		System.out.println(memo[Y]);
	}
}