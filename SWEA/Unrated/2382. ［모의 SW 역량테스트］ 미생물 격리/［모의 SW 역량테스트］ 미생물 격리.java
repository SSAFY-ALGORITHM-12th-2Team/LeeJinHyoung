import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	public static class info {
		int row;
		int col;
		int num;
		int direction;

		public info(int row, int col, int num, int direction) {
			super();
			this.row = row;
			this.col = col;
			this.num = num;
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "info [row=" + row + ", col=" + col + ", num=" + num + ", direction=" + direction + "]";
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int[] cond = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int N = cond[0];
			int M = cond[1];
			int K = cond[2];

			List<info> virus = new ArrayList();

			for (int i = 0; i < K; i++) {
				int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				virus.add(new info(input[0], input[1], input[2], input[3]));
			}

			info[][] pos = new info[N][N];
			for (int time = 0; time < M; time++) {
				for (int i = 0; i < virus.size(); i++) {// 바이러스 이동과 약품 만나면 방향전환까지 시행
					switch (virus.get(i).direction) {
					case 1:
						virus.get(i).row--;
						break;
					case 2:
						virus.get(i).row++;
						break;
					case 3:
						virus.get(i).col--;
						break;
					case 4:
						virus.get(i).col++;
						break;
					}
					if (virus.get(i).row == 0 || virus.get(i).row == N - 1) {
						virus.get(i).num /= 2;
						if (virus.get(i).direction == 1) {
							virus.get(i).direction = 2;
						} else if (virus.get(i).direction == 2) {
							virus.get(i).direction = 1;
						}
					} else if (virus.get(i).col == 0 || virus.get(i).col == N - 1) {
						virus.get(i).num /= 2;
						if (virus.get(i).direction == 3) {
							virus.get(i).direction = 4;
						} else if (virus.get(i).direction == 4) {
							virus.get(i).direction = 3;
						}
					}
				}
				Collections.sort(virus, new Comparator<info>() {
					@Override
					public int compare(Solution.info o1, Solution.info o2) {
						// TODO Auto-generated method stub
						return o2.num - o1.num;
					}
				});
				for (int i = 0; i < N; i++) {
					Arrays.fill(pos[i], null);
				}

				for (int i = 0; i < virus.size(); i++) {
					info cur = virus.get(i);
					if (pos[cur.row][cur.col] == null) {
						pos[cur.row][cur.col] = cur;
					} else {
						virus.get(virus.indexOf(pos[cur.row][cur.col])).num += cur.num;
						virus.remove(i);
						i--;
					}
				}
//				for (info i : virus) {
//					System.out.println(i.toString());
//				}
//				System.out.println();

			}
			int total = 0;
			for (info i : virus) {
				total += i.num;
			}
			System.out.println("#" + tc + " " + total);
		}
	}
}