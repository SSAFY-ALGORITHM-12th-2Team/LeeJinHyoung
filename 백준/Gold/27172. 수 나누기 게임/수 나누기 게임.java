import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] x = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Set<Integer> set = new HashSet<>();
		for (int i : x) {
			set.add(i);
		}
		// 입력 끝

		int[] score = new int[1000001];
		// 그냥 해당 순서에 나눠 버리고 그 점수를 기록해 두면 더 나을 거 같다

		for (int len = 0; len < x.length; len++) {
			// x 길이만큼 돈다
			int multiply = 2;
			while (x[len] * multiply <= 1000000) {
				if (set.contains(x[len] * multiply)) {
					score[x[len]]++;
					score[x[len] * multiply]--;
				}
				multiply++;
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < x.length; i++) {
			answer.append(score[x[i]] + " ");
		}

		System.out.println(answer);
	}
}