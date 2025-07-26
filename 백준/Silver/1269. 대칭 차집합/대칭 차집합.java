import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Arrays.sort(A);
		Arrays.sort(B);
		int b = 0;
		Set<Integer> intersect = new HashSet<>();
		for (int a = 0; a < Math.max(A.length, B.length);) {
			if (a >= A.length || b >= B.length)
				break;
			if (A[a] == B[b]) {
				intersect.add(A[a]);
				a++;
				b++;
			} else if (A[a] > B[b]) {
				b++;
			} else if (A[a] < B[b]) {
				a++;
			}
		}
		System.out.println(A.length - intersect.size() + B.length - intersect.size());
	}
}