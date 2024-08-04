import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// int long 생각하기
		int N = Integer.parseInt(br.readLine());
		int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] BC = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int B = BC[0];
		int C = BC[1];// 입력 완료

		long Answer = 0;
		for (int i = 0; i < N; i++) {
			A[i] = A[i] - B;
			Answer++;
			if(A[i]<=0)
				continue;
			// 각 시험장에 총 감독관의 감독가능 학생 수 빼고 답에 시험장 수 추가
			if (A[i] % C == 0) {
				Answer += (long) (A[i] / C);
			} else {
				Answer += (long) ((A[i] / C) + 1);
			}
		}
		System.out.println(Answer);
	}
}