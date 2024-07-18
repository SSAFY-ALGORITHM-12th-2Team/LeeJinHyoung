import java.io.*;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int sum = 0;
			Stack<String> st = new Stack<>();
			String[] arr = br.readLine().split("");

			st.push(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				if (arr[i].equals("(")) {
					st.push(arr[i]);
				} else if (arr[i].equals(")")) {
					st.pop();
					if (arr[i - 1].equals("(")) {
						sum += st.size();
					} else if (arr[i - 1].equals(")")) {
						sum += 1;
					}
					
				}
			}
			System.out.println("#" + tc + " " + sum);
		}
	}

}
