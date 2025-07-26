import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		PriorityQueue<Long> pq = new PriorityQueue<>();

//		총 비교 횟수
		long answer = 0;
		for (int i = 0; i < N; i++) {
			pq.offer(sc.nextLong());
		}
		while (pq.size() > 1) {
			long first = pq.poll();
			long second = pq.poll();
			long sum = first + second;
			answer += sum;
			pq.offer(sum);  // 다시 큐에 넣어야 다음 비교에 포함됨
		}
		System.out.println(answer);
	}
}