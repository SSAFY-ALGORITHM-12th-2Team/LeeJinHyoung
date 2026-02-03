import java.util.*;
import java.io.*;

public class Main {
	private static String makeWindow(int N) {
		StringBuilder window = new StringBuilder();

		while (N > 0) {
			window.append("IO");
			N--;
		}
		window.append("I");
		return window.toString();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String S = br.readLine();
		String window = makeWindow(N);
		int windowlength = window.length();

		int answer = 0;
		for (int i = 0; i <= M - windowlength; i++) {
			String subString = S.substring(i, i + windowlength);
//			System.out.println(subString);
			if (subString.equals(window))
				answer++;
		}
		System.out.println(answer);
	}
}