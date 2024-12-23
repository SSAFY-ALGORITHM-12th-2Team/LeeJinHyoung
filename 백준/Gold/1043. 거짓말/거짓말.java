import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int N, M;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 요컨대 진실을 아는사람과 연결이 되면 안된다.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = input[0];
		M = input[1];

		// 사람은 1번부터 진실을 아는 사람
		int[] people = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Set<Integer>[] partySet = new Set[M + 1];
		List<Integer>[] network = new ArrayList[N + 1];

		for (int i = 1; i <= M; i++) {
			partySet[i] = new TreeSet<Integer>();
		} // 동적 공간 할당
		for (int i = 1; i <= N; i++) {
			network[i] = new ArrayList<Integer>();
		} // 동적 공간 할당

		for (int i = 0; i < M; i++) {
			int[] party = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			for (int j = 1; j < party.length; j++) {
				partySet[i + 1].add(party[j]);
				for (int k = j + 1; k < party.length; k++) {
					network[party[j]].add(party[k]);
					network[party[k]].add(party[j]);
				}
			} // 연결된 사람 간 네트워크
		}
		boolean[] truth = bfs(people, network);// 어떤 사람이 진실을 아는가?

		int answer = M;
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < truth.length; j++) {
				if (truth[j] && partySet[i].contains(j)) {
					answer--;
					break;
				}
			}
		}
		System.out.println(answer);
	}

	private static boolean[] bfs(int[] people, List<Integer>[] network) {
		// TODO Auto-generated method stub
		boolean[] truth = new boolean[N + 1];
		for (int i = 1; i < people.length; i++) {
			truth[people[i]] = true;
			Queue<Integer> q = new ArrayDeque();
			q.offer(people[i]);

			while (!q.isEmpty()) {
				int cur = q.poll();

				for (int j = 0; j < network[cur].size(); j++) {
					if (!truth[network[cur].get(j)]) {
						truth[network[cur].get(j)] = true;
						q.offer(network[cur].get(j));
					}
				}
			}
		}
		return truth;
	}
}