import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (i % 6 == 0 || i == N) {
				answer.append(i + " Go! ");
			} else {
				answer.append(i + " ");
			}
		}
		System.out.println(answer);
	}
}