import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int[] prime_number = { 2, 3, 5, 7 };

	public static void perm(int ans, int cnt) throws IOException {
		if (cnt == N) {
			System.out.println(ans);
			return;
		}
		for (int i = 1; i <= 9; i += 2) {
			boolean flag = true;
			for (int div = 2; div <= Math.pow(ans * 10 + i, 0.5); div++) {
				if ((ans * 10 + i) % div == 0) {
					flag = false;
					break;
				}
			}
			if (flag == false)
				continue;
			perm(ans * 10 + i, cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		for (int i = 0; i < prime_number.length; i++) {
			perm(prime_number[i], 1);
		}
	}
}