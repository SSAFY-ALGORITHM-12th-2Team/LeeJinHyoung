import java.io.*;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();// 2MB
		String bomb = br.readLine(); //

		int inputlen = input.length();
		int bomblen = bomb.length();

		Stack<Character> stack = new Stack<>();
		// 입력을 받는다.
		for (int i = 0; i < input.length(); i++) {
			stack.push(input.charAt(i));

			if (stack.size() >= bomblen) {
				int bombIdx = 0;
				while (bombIdx < bomblen && stack.get(stack.size() - bomblen + bombIdx) == bomb.charAt(bombIdx)) {
					bombIdx++;
				}
				if (bombIdx == bomblen) {
					for (int pop = 0; pop < bomblen; pop++) {
						stack.pop();
					}
				}
			}
		}
		StringBuilder answer = new StringBuilder();
		for (char ch : stack) {
			answer.append(ch);
		}
		System.out.println(answer.length() == 0 ? "FRULA" : answer);
	}
}