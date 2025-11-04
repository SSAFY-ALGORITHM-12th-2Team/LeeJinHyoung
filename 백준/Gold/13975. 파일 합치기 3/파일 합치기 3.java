import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			long[] data = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

			PriorityQueue<Long> pq = new PriorityQueue<>();
			pq.addAll(Arrays.stream(data).boxed().collect(Collectors.toList()));

			long answer = 0;
			while (pq.size() > 1) {
				long left = pq.poll();
				long right = pq.poll();
				long next = left + right;
				answer += next;
				pq.offer(next);
			}
			System.out.println(answer);
		}
	}
}