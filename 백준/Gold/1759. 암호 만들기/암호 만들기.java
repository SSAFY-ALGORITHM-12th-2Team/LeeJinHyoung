import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
	private static int L, C;
	private static String[] alphabet;
	private static List<String> answer;
	private static int jaeum, moeum;

	public static void permutation(String[] sel, int idx, int start, boolean[] vis) {
		if (idx == sel.length) {
			// 자모 검증
			jaeum = 0;
			moeum = 0;
			for (String s : sel) {
				if (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u")) {
					moeum++;
				} else {
					jaeum++;
				}
			}
			if (jaeum >= 2 && moeum >= 1) {
				answer.add(String.join("", sel));
			}
			return;
		}
		for (int i = start; i < alphabet.length; i++) {
			if (!vis[i]) {
				vis[i] = true;
				sel[idx] = alphabet[i];
				permutation(sel, idx + 1, i, vis);
				vis[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		L = input[0];
		C = input[1];

		alphabet = br.readLine().split(" ");

		Arrays.sort(alphabet);
		answer = new ArrayList<>();
		permutation(new String[L], 0, 0, new boolean[C]);
		for (String s : answer) {
			System.out.println(s);
		}
	}
}
