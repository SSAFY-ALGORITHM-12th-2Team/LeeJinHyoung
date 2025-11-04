import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			String[] input = sc.next().split("");
			for (int j = 0; j < input.length - 1; j++) {
				if (input[j].equals(input[j + 1])) {
					input[j] = "";
				}
			}
			StringBuilder answer = new StringBuilder();
			for (int j = 0; j < input.length; j++) {
				answer.append(input[j]);
			}
			System.out.println(answer.toString().trim());
		}
	}

}
