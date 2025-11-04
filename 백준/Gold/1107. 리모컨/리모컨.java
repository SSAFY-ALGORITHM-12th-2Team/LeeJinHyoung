import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {
	private static Set<Integer> error;
	private static int N, M, answer;

	private static void dfs(int[] remoteControl, int click, int idx) {
		if (idx == remoteControl.length) {
			int number = 0;
			for (int i = 0; i < remoteControl.length; i++) {
				number += remoteControl[i] * Math.pow(10, remoteControl.length - i - 1);
			}
			answer = Math.min(answer, Math.abs(N - number) + click);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (error.contains(i))
				continue;
			remoteControl[idx] = i;
			dfs(remoteControl, click + 1, idx + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int errorInput[] = new int[M];
		if (M > 0) {
			errorInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		error = new HashSet<Integer>(Arrays.stream(errorInput).boxed().collect(Collectors.toList()));

		answer = Integer.MAX_VALUE;
		int[] Nsplit = Arrays.stream(String.valueOf(N).split("")).mapToInt(Integer::parseInt).toArray();

		int subtraction = Math.abs(N - 100);

		for (int length = 1; length <= 6; length++) {
			dfs(new int[length], 0, 0);
		}
		System.out.println(answer > subtraction ? subtraction : answer);
	}
}