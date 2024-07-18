import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int dump = Integer.parseInt(br.readLine());
			int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			Arrays.sort(arr);

			while (dump > 0) {
				arr[0]++;
				arr[arr.length - 1]--;
				if (arr[0] == arr[arr.length - 1] - 1 || arr[0] == arr[arr.length - 1]) {
					break;
				}
				Arrays.sort(arr);
				dump--;
			}
			System.out.println("#"+tc+" "+(arr[arr.length-1]-arr[0]));
		}
	}

}
