import java.util.*;
import java.io.*;

public class Main {
	private static final int X = 1000000007;

	// 우선 N의 1000000007승을 만들어야 한다
	public static long power(long base, int pow) {
		long result = 1;
		while (pow > 0) {
			// 만약 n이 홀수라면 x를 결과에 곱합니다.
			if (pow % 2 == 1) {
				result = (result % X) * (base % X) % X;
			}
			// n을 절반으로 줄이고 x를 제곱합니다.
			base = (base % X) * (base % X) % X;
			pow /= 2;
		}
		return result;
	}

	private static long getGCD(long num1, long num2) {// 기약 분수 만들기 위한 최대공약수 함수
		if (num1 % num2 == 0) {
			return num2;
		}
		return getGCD(num2, num1 % num2);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int M = Integer.parseInt(br.readLine());

		int[] ary = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		long denom = ary[0] % X;// 분모
		long numer = ary[1] % X;// 분자

		for (int i = 2; i <= M; i++) {// i번째 줄의 Ni,Si 정보가 나온다.
			ary = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = ary[0];// 분모가 됨
			int S = ary[1];// 분자가 됨
			// 문제 이해 : S1/N1+S2/N2...의 합을 구하는 문제다
			// 단 여기서 N1,N2를 페르마의 소정리??? 그걸로 푼다

			// 통분해서 덧셈
			long tempDenom = denom * N;
			long tempNumer = numer * N + S * denom;
			denom = tempDenom;
			numer = tempNumer;
			denom %= X;
			numer %= X;
			// 기약 분수로 만들어 줘야 하나?
			long div = getGCD(denom, numer);
			denom /= div;
			numer /= div;

		}
		System.out.println((numer % X) * (power(denom, X - 2) % X) % X);
	}
}