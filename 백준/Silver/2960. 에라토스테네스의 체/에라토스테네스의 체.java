import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		boolean[] removed = new boolean[N + 1];

		int cnt = 0;
		for (int div = 2; div <= N; div++) {
			for (int multiple = 1; div * multiple <= N; multiple++) {
				if (removed[div * multiple])
					continue;
				removed[div * multiple] = true;
				cnt++;
				if (cnt == K) {
					System.out.println(div * multiple);
					return;
				}
			}
		}
	}
}