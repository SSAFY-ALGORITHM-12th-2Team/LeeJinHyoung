import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int num = Integer.parseInt(br.readLine());

		int[] cond = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int student = Integer.parseInt(br.readLine());

		for (int i = 0; i < student; i++) {
			int[] s_cond = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			if (s_cond[0] == 1) {// 남학생이라면
				for (int j = 0; j < cond.length; j++) {
					if ((j + 1) % s_cond[1] == 0) {
						if (cond[j] == 0)
							cond[j] = 1;
						else if (cond[j] == 1)
							cond[j] = 0;
					}
				}
			} else if (s_cond[0] == 2) {
				int small = s_cond[1] - 1;
				int big = s_cond[1] - 1;
				while (small >= 0 && big < cond.length && cond[small] == cond[big]) {
					if (cond[small] == 1 && cond[big] == 1) {
						cond[small] = 0;
						cond[big] = 0;
					} else if (cond[small] == 0 && cond[big] == 0) {
						cond[small] = 1;
						cond[big] = 1;
					}
					small--;
					big++;
				}
			}
		}
		for (int j=0;j<cond.length;j++) {
			System.out.print(cond[j] + " ");
			if((j+1)%20==0)
				System.out.println();
		}

	}
}