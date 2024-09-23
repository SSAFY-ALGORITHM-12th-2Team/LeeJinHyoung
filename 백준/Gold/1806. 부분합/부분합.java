import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = input1[0];
		int S = input1[1];

		int left = 0;
		int right = 0;
		int sum = arr[0];
		if (sum >= S) {
			min = Math.min(min, right - left + 1);
		}

		while (right < arr.length - 1) {
			sum += arr[++right];
			while (right + 1 < arr.length && sum < S) {
				sum += arr[++right];
			}
			while (sum - arr[left] >= S) {
				sum -= arr[left++];
			}
			//System.out.println(left + " " + right);
			if (sum >= S) {
				min = Math.min(min, right - left + 1);
			}
		}
		if (min == 1) {// 이미 1개라는 최솟값이 나왔으므로 0은 불가값
			System.out.println(min);
		} else if (min == Integer.MAX_VALUE) {// 이건 왜하는 건가 싶긴 한데 없으면 91퍼까지 안올라옴
			System.out.println(0);
		} else {// 정답일 경우
			System.out.println(min);
		}
	}
}