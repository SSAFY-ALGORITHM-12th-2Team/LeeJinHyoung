import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		char[][] input = new char[N][];
		for (int i = 0; i < N; i++) {
			input[i] = br.readLine().toCharArray();
		}
		StringBuilder answer = new StringBuilder();
		int len = input[0].length;
		for (int i = 0; i < len; i++) {
			boolean flag = true;
			for (int j = 1; j < N; j++) {
				if (input[j - 1][i] != input[j][i]) {
					flag = false;
				}
			}
			if (flag)
				answer.append(input[0][i]);
			else {
				answer.append("?");
			}
		}
		System.out.println(answer);
	}
}