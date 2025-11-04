import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		long low = 1;
		long high = K;

		while (low < high) {
			long mid = (low + high) / 2;
			long count = 0;

			for (int i = 1; i <= N; i++) {
				count += Math.min(mid / i, N);
			}

			// count가 많다는 것은 임의의 x(mid)보다 작은 수가 B[K]보다 많다는 뜻
			if (K <= count) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		System.out.println(low);
	}
}