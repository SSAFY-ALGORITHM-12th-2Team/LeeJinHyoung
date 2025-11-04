import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int A = input[0];
		int B = input[1];
		int N = input[2];

		int result = 0;
		for (int i = 1; i <= N; i++) {
			A = (A % B) * 10;
			result = A / B;
		}
		System.out.println(result);
	}
}