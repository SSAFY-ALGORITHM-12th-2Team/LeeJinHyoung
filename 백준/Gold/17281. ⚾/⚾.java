import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int score[][], N, max;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		max = Integer.MIN_VALUE;

		// 무조건 경기전 순서를 정해야 한다 단 1번 선수를 4번 타자이다
		score = new int[N][];
		for (int i = 0; i < N; i++) {
			score[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		} // 이닝 별 타자 스코어 입력

		// 각 가짓수를 순열로 뽑고 순열로 뽑았다면 이닝을 돌린다
		int[] order = new int[9];
		boolean used[] = new boolean[9];
		order[3] = 0;// 1번 선수는 4번 타자
		used[0] = true;// 1번 선수는 이미 가용

		perm(order, used, 0);
		System.out.println(max);
	}

	private static void perm(int[] order, boolean[] used, int cnt) {
		// TODO Auto-generated method stub
		if (cnt == 9) {
			// 해당 수열이 나온다면 이닝을 돌린다
//			for (int e : order)
//				System.out.print(e + " ");
//			System.out.println();
			int inning = 0;
			int numbering = 0;
			int scoreSum = 0;
			while (inning < N) {
				int out = 0;// 각 이닝이 시작될 때 아웃 수는 초기화되어야 하므로
				boolean[] basement = new boolean[4];
				while (out < 3) {
					if (score[inning][order[numbering % 9]] == 0) {
						out++;
					} else if (score[inning][order[numbering % 9]] != 0) {
						basement[0] = true;
						if (score[inning][order[numbering % 9]] == 1) {// 1루타일 때
							if (basement[3] == true)
								scoreSum++;

							basement[3] = basement[2];
							basement[2] = basement[1];
							basement[1] = true;
						} else if (score[inning][order[numbering % 9]] == 2) {// 2루타일 때
							for (int i = 2; i < 4; i++) {
								if (basement[i] == true) {
									scoreSum++;
									basement[i] = false;
								}
							}
							basement[3] = basement[1];
							basement[2] = true;
							for (int i = 0; i < 2; i++) {
								basement[i] = false;
							}
						} else if (score[inning][order[numbering % 9]] == 3) {// 3루타일 때
							for (int i = 1; i < 4; i++) {
								if (basement[i] == true) {
									scoreSum++;
									basement[i] = false;
								}
							}
							basement[3] = true;
						} else if (score[inning][order[numbering % 9]] == 4) {// 홈런일 때
							for (int i = 0; i < 4; i++) {
								if (basement[i] == true) {
									scoreSum++;
									basement[i] = false;
								}
							}
						}
						basement[0] = false;
					}
					numbering++;
				}
				inning++;
			}
			max = Math.max(scoreSum, max);
			return;
		}
		for (int i = 1; i < 9; i++) {
			if (used[i] == false) {
				used[i] = true;
				if (cnt == 3)
					cnt++;
				order[cnt] = i;
				perm(order, used, cnt + 1);
				used[i] = false;
			}
		}
	}
}