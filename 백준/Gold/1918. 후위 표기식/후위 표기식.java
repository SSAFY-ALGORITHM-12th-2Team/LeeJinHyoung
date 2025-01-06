import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("");

		Stack<String> oper = new Stack<>();
		List<String> ans = new ArrayList<>();

		for (String s : input) {
			if (s.matches("^[A-Z]*$")) { // 피연산자면 그냥 추가
				ans.add(s);
			} else if (s.equals("(")) { // 여는 괄호는 무조건 스택에 추가
				oper.push(s);
			} else if (s.equals(")")) { // 닫는 괄호는 여는 괄호가 나올 때까지 연산자 꺼내기
				while (!oper.empty() && !oper.peek().equals("(")) {
					ans.add(oper.pop());
				}
				oper.pop(); // 여는 괄호 제거
			} else {
				// 연산자 우선순위를 비교해서 스택에서 꺼낼지 결정
				while (!oper.empty() && compareTo(oper.peek()) >= compareTo(s)) {
					ans.add(oper.pop());
				}
				oper.push(s);
			}
		}

		while (!oper.empty()) {
			ans.add(oper.pop());
		}

		System.out.println(String.join("", ans));
	}

	private static int compareTo(String s) {
		if (s.equals("+") || s.equals("-")) {
			return 1;
		} else if (s.equals("*") || s.equals("/")) {
			return 2;
		} else {
			return 0;
		}
	}
}
