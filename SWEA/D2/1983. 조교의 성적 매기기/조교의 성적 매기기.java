import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		String[] ranking = { "D0","C-","C0","C+","B-","B0","B+","A-","A0","A+"};
		for (int test_case = 1; test_case <= T; test_case++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int N = input[0];
			int K = input[1];

			double[] total = new double[N];
			double[] rank;

			int idx = 0;
			for (int i = 0; i < N; i++) {
				int[] score = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

				total[i] = score[0] * 0.35 + score[1] * 0.45 + score[2] * 0.2;
			}
			double find=total[K-1];
			rank=total;
			Arrays.sort(rank);
			
			for(int i=0;i<N;i++) {
				if(find==rank[i]) {
					System.out.println("#"+test_case+" "+ranking[(i)/(N/10)]);
					break;
				}
			}
		}
	}
}
