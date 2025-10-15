import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
	private static List<Long> twoSum, threeSum;

	public static void init(long[] dice) {
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				if (i + j != 5) {
					twoSum.add((long) (dice[i] + dice[j]));
				}
			}
		}

		threeSum.add((long) (dice[0] + dice[1] + dice[2]));
		threeSum.add((long) dice[0] + dice[1] + dice[3]);
		threeSum.add((long) dice[0] + dice[2] + dice[4]);
		threeSum.add((long) dice[0] + dice[3] + dice[4]);
		threeSum.add((long) dice[5] + dice[1] + dice[2]);
		threeSum.add((long) dice[5] + dice[1] + dice[3]);
		threeSum.add((long) dice[5] + dice[2] + dice[4]);
		threeSum.add((long) dice[5] + dice[3] + dice[4]);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(br.readLine());
		long[] dice = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

		if (N == 1) {
			long[] arr = new long[6];
			long sum = Arrays.stream(dice).sum();
			for (int i = 0; i < 6; i++) {
				arr[i] = sum - dice[i];
			}
			Arrays.sort(arr);
			System.out.println(arr[0]);
			return;
		}
//		정렬을해서 푸는 것이 아닌 해당 위치의 경우의 수를 따져 해결해야한다. 너무 날먹 해결하려고 했음
		long one = Long.MAX_VALUE;
		for (long i : dice) {
			one = i < one ? i : one;
		}

		twoSum = new ArrayList<Long>();
		threeSum = new ArrayList<Long>();
		init(dice);

		Collections.sort(twoSum);
		Collections.sort(threeSum);

		long two = twoSum.get(0);
		long three = threeSum.get(0);

//		System.out.println(one + " " + two + " " + three);
		long answer = three * 4L + two * (N - 2) * 4L + two * (N - 1) * 4L + one * (N - 2) * (N - 2) * 1L
				+ one * (N - 2) * (N - 1) * 4L;
		System.out.println(answer);
	}
}