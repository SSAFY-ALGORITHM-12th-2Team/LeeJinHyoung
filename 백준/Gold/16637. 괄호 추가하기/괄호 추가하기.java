import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static String[] regex;
	static int max;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		max = Integer.MIN_VALUE;
		regex = br.readLine().split("");
		// long으로 처리

		if (N == 1) {
			System.out.println(regex[0]);
		} else {
			for (int i = 1; i <= regex.length; i++) {
				combi(new String[i], new boolean[regex.length], 0, 0);
			}
			System.out.println(max);
		}
	}

	private static void combi(String[] arr, boolean[] vis, int cnt, int start) {
		// TODO Auto-generated method stub
		if (cnt == arr.length) {
			for (int i = 0; i < vis.length - 2; i++) {
				if (vis[i] && vis[i + 2])
					return;
			}
			ArrayList<String> list = new ArrayList<String>();

			String[] reg = Arrays.copyOf(regex, regex.length);

			for (int i = 0; i < arr.length; i++) {
				int idx = Integer.parseInt(arr[i]);
				int prev = Integer.parseInt(reg[idx - 1]);
				int next = Integer.parseInt(reg[idx + 1]);
				if (reg[idx].equals("+")) {
					int num = prev + next;
					reg[idx - 1] = String.valueOf(num);
				} else if (reg[idx].equals("-")) {
					int num = prev - next;
					reg[idx - 1] = String.valueOf(num);
				} else if (reg[idx].equals("*")) {
					int num = prev * next;
					reg[idx - 1] = String.valueOf(num);
				} else if (reg[Integer.parseInt(arr[i])].equals("/")) {
					int num = prev / next;
					reg[idx - 1] = String.valueOf(num);
				}
				reg[idx] = "＃";
				reg[idx + 1] = "＃";
			} // 괄호처리
			for (int i = 0; i < reg.length; i++) {
				if (reg[i].equals("#"))
					continue;
				list.add(reg[i]);
			}
//			for (String s : list) {
//				System.out.print(s);
//			}
//			System.out.println();
			int sum = Integer.parseInt(list.get(0));
			for (int i = 1; i < list.size() - 1; i += 2) {
				if (list.get(i).equals("+")) {
					sum += Integer.parseInt(list.get(i + 1));
				} else if (list.get(i).equals("-")) {
					sum -= Integer.parseInt(list.get(i + 1));
				} else if (list.get(i).equals("*")) {
					sum *= Integer.parseInt(list.get(i + 1));
				} else if (list.get(i).equals("/")) {
					sum /= Integer.parseInt(list.get(i + 1));
				}
//				System.out.println(sum);
			}
			max = Math.max(sum, max);
			return;
		}

		for (int i = start; i < vis.length; i++) {
			if (!vis[i]
					&& (regex[i].equals("+") || regex[i].equals("-") || regex[i].equals("*") || regex[i].equals("/"))) {
				vis[i] = true;
				arr[cnt] = String.valueOf(i);
				combi(arr, vis, cnt + 1, i + 1);
				vis[i] = false;
			}
		}
	}
}