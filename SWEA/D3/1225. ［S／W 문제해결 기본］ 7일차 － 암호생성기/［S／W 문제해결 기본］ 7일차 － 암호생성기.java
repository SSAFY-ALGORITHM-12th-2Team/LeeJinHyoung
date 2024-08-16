import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	private static ArrayList<Integer> cycle(ArrayList<Integer> list) {
		while (true) {
			for (int i = 1; i <= 5; i++) {
				list.add(list.remove(0) - i);
				if (list.get(7) <= 0) {
					return list;
				}
			}
			//System.out.println(Arrays.toString(list.toArray()));
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			ArrayList<Integer> encrypted = new ArrayList<>();
			for (int i = 0; i < arr.length; i++) {
				encrypted.add(arr[i]);
			}
			encrypted = cycle(encrypted);

			encrypted.remove(7);
			encrypted.add(0);
			System.out.print("#" + tc + " ");
			for (int i : encrypted) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

}