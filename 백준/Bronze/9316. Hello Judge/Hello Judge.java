import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			answer.append("Hello World, Judge " + i + "!\n");
		}
		System.out.println(answer);
	}
}