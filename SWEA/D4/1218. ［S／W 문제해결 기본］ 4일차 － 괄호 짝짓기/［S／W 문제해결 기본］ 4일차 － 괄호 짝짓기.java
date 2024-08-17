import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split("");

			Stack<String> str = new Stack<>();

			boolean flag = true;
			for (int i = 0; i < input.length; i++) {
				//System.out.println(input[i]);
				if (input[i].equals("(") || input[i].equals("{") || input[i].equals("[") || input[i].equals("<")) {
					str.push(input[i]);
				} else if (input[i].equals(")") || input[i].equals("}") || input[i].equals("]")
						|| input[i].equals(">")) {
					if (str.peek().equals("(") && input[i].equals(")")) {
						str.pop();
					} else if (str.peek().equals("{") && input[i].equals("}")) {
						str.pop();
					} else if (str.peek().equals("[") && input[i].equals("]")) {
						str.pop();
					} else if (str.peek().equals("<") && input[i].equals(">")) {
						str.pop();
					} else {
						flag = false;
						break;
					}
				}

			}
			if (str.empty() && flag == true) {
				System.out.println("#" + tc + " " + 1);
			} else {
				System.out.println("#"+tc+" "+0);
			}
		}
	}
}