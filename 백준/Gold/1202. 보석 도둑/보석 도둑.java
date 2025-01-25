import java.util.*;
import java.io.*;

public class Main {
	// 보석 클래스 정의
	private static class Jewelry implements Comparable<Jewelry> {
		int M; // 무게
		int V; // 가치

		public Jewelry(int M, int V) {
			this.M = M;
			this.V = V;
		}

		@Override
		public int compareTo(Jewelry o) {
			// TODO Auto-generated method stub
			return this.M - o.M;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 보석 정보 저장 (무게, 가치)
		Jewelry[] jewelries = new Jewelry[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			jewelries[i] = new Jewelry(M, V);
		}

		// 가방 무게 저장
		int[] bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}

		// 보석은 무게 기준 오름차순, 가방도 오름차순으로 정렬
		Arrays.sort(jewelries);
		Arrays.sort(bags);

		// 우선순위 큐: 보석의 가치를 내림차순으로 저장
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		long answer = 0;
		int index = 0; // 현재 확인 중인 보석의 인덱스

		// 가방을 순회하며 처리
		for (int bag : bags) {
			// 가방에 들어갈 수 있는 보석 추가
			while (index < N && jewelries[index].M <= bag) {
				pq.offer(jewelries[index].V);
				index++;
			}

			// 가장 가치가 큰 보석을 가방에 넣음
			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		System.out.println(answer);
	}
}