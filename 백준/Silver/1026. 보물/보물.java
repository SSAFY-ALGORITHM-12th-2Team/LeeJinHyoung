import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		PriorityQueue<Integer> pq_A = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> pq_B = new PriorityQueue<Integer>();

		int answer = 0;

		pq_A.addAll(Arrays.stream(A).boxed().collect(Collectors.toList()));
		pq_B.addAll(Arrays.stream(B).boxed().collect(Collectors.toList()));

		while (!pq_A.isEmpty()) {
			int a = pq_A.poll();
			int b = pq_B.poll();

			answer = answer + a * b;
		}
		System.out.println(answer);

	}
}