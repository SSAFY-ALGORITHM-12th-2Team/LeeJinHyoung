import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] split = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int zero = 0;
		int one = 0;

		switch (split[0]) {
		case 0:
			zero++;
			break;
		case 1:
			one++;
			break;
		}
		for (int i = 1; i < split.length; i++) {
			if (split[i] != split[i - 1] && split[i] == 0) {
				zero++;
			} else if (split[i] != split[i - 1] && split[i] == 1) {
				one++;
			}
		}
		System.out.println(Math.min(zero, one));
	}
}