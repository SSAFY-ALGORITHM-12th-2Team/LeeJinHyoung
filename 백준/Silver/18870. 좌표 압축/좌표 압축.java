import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder answer = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int[] arr = input.clone();
		int[] rank = new int[arr.length];

		Arrays.sort(arr);
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1]) {
				rank[i] = rank[i - 1];
			} else if (arr[i] > arr[i - 1]) {
				rank[i] = rank[i - 1] + 1;
			}
		}

		for (int i : input) {
			answer.append(rank[Arrays.binarySearch(arr, i)] + " ");
		}
		System.out.println(answer.toString());
	}
}