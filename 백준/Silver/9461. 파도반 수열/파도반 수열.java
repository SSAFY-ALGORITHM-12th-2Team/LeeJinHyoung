import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		long[] p = new long[101];
		p[0] = 0;
		p[1] = 1;
		p[2] = 1;
		p[3] = 1;
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());

			if (p[N] == 0) {
				for (int idx = 4; idx <= N; idx++) {
					p[idx] = p[idx - 2] + p[idx - 3];
				}
			}
			System.out.println(p[N]);
		}
	}
}
