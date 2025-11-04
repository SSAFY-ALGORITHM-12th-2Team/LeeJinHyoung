import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//		입력 끝

		List<Integer> liquid = new ArrayList<>(Arrays.stream(input).boxed().collect(Collectors.toList()));
		Collections.sort(liquid);

		int[] answer = new int[2];
		answer[0] = liquid.get(0);
		answer[1] = liquid.get(liquid.size() - 1);

		int left = 0;
		int right = liquid.size() - 1;
		while (left < right - 1) {
			int sum = liquid.get(left) + liquid.get(right);
			if (sum > 0) {
				right--;
			} else if (sum < 0) {
				left++;
			} else if (sum == 0) {
				System.out.println(liquid.get(left) + " " + liquid.get(right));
				return;
			}

			if (Math.abs(answer[0] + answer[1]) > Math.abs(liquid.get(left) + liquid.get(right))) {
				answer[0] = liquid.get(left);
				answer[1] = liquid.get(right);
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	}
}