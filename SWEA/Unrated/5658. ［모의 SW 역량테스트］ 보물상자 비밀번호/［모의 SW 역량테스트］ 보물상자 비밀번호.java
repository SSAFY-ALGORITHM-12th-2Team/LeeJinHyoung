import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int N = input[0];
			int K = input[1];

			String[] input1 = br.readLine().split("");
			ArrayList<String> number = new ArrayList<>();
			for (int i = 0; i < input1.length; i++) {
				number.add(input1[i]);
			}

			HashSet<String> set = new HashSet<>();

			for (int n = 0; n < N / 4; n++) {
				for (int i = 0; i < N; i += N / 4) {
					String hex = new String();
					for (int j = 0; j < N / 4; j++) {
						hex = hex + number.get(i + j);
					}
					set.add(hex);
				}
				number.add(number.remove(0));
			}

			ArrayList<String> arr = new ArrayList<String>(set);
			Collections.sort(arr, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return Integer.parseInt(o2, 16) - Integer.parseInt(o1, 16);
				}
			});
//			for (String e : arr) {
//				System.out.println(e);
//			}
			System.out.println("#" + tc + " " + Integer.parseInt(arr.get(K - 1), 16));
		}
	}
}