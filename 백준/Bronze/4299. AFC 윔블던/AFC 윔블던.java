import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int sum = input[0];
		int sub = input[1];

		int a = (sum + sub) / 2;
		int b = sum - a;
		if (a < 0 || b < 0 || a + b != sum || a - b != sub) {
			System.out.println(-1);
		} else if (a >= b) {
			System.out.println(a + " " + b);
		} else
			System.out.println(b + " " + a);
	}
}