import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int minIdx = 0, maxIdx = input.length - 1;
		int minAns = minIdx, maxAns = maxIdx;

		int zeroDist = Integer.MAX_VALUE;

		while (minIdx != maxIdx) {
			int sum = input[minIdx] + input[maxIdx];
			if (zeroDist >= Math.abs(sum)) {
				zeroDist = Math.abs(sum);
				minAns = minIdx;
				maxAns = maxIdx;
			}
			if (sum < 0) {
				minIdx++;
			} else if (sum == 0) {
				break;
			} else if (sum > 0) {
				maxIdx--;
			}
		}
		System.out.println(input[minAns] + " " + input[maxAns]);
	}
}