import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class Day {
	int pi;
	int ti;
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int[] ti = new int[T];
		int[] pi = new int[T];
		for (int i = 0; i < T; i++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			ti[i] = input[0];
			pi[i] = input[1];
		}

//		int[][] max = new int[T + 1][T];
//
//		for (int s = 0; s < T; s++) {
//			if (s + days[s].ti <= T) {
//				max[s][s] = days[s].pi;
//			}
//			for (int cur = s; cur < T; cur++) {
//				if (cur + days[cur].ti < T) {
//					max[s][cur + days[cur].ti]+=max[s][cur]+days[cur].pi;
//			}
//		} //왜 굳이 이중 배열 쓰지..?
		int[] Start_day = new int[T + 1];
		for (int i = 0; i < T; i++) {
			if (i + ti[i] <= T) {
				Start_day[i + ti[i]] = Math.max(Start_day[i + ti[i]], Start_day[i] + pi[i]);
			}
			Start_day[i + 1] = Math.max(Start_day[i + 1], Start_day[i]);
		}
		System.out.println(Start_day[T]);

	}
}