import java.util.*;
import java.io.*;

public class Main {
//	모든 수를 뒷수가 크게 만들면 된다. 덧셈과 곱셈은 교환 법칙으로 상관 없다.
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split("");
		List<String> numberAndSign = new ArrayList<>();
		StringBuilder number = new StringBuilder();
		for (String s : input) {
			if (s.equals("+") | s.equals("-")) {
				numberAndSign.add(number.toString());
				numberAndSign.add(s);
				number = new StringBuilder();
			} else {
				number.append(s);
			}
		}
		numberAndSign.add(number.toString());
//		Calculate에 숫자와 괄호를 분리
//		이후에는 
		for (int i = 0; i < numberAndSign.size(); i++) {
			if (numberAndSign.get(i).equals("+")) {
				int first = Integer.parseInt(numberAndSign.get(i - 1));
				int second = Integer.parseInt(numberAndSign.get(i + 1));
				numberAndSign.add(i + 2, String.valueOf(first + second));
				numberAndSign.remove(i + 1);
				numberAndSign.remove(i);
				numberAndSign.remove(i - 1);
				i -= 2;
			}
		}
		for (int i = 0; i < numberAndSign.size(); i++) {
			if (numberAndSign.get(i).equals("-")) {
				int first = Integer.parseInt(numberAndSign.get(i - 1));
				int second = Integer.parseInt(numberAndSign.get(i + 1));
				numberAndSign.add(i + 2, String.valueOf(first - second));
				numberAndSign.remove(i + 1);
				numberAndSign.remove(i);
				numberAndSign.remove(i - 1);
				i -= 2;
			}
		}
		for (String i : numberAndSign)
			System.out.println(i);
	}
}