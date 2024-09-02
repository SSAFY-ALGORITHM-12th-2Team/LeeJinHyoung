import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	int r;
	int c;
	int val;
	public pos(int r, int c, int val) {
		super();
		this.r = r;
		this.c = c;
		this.val = val;
	}
}

public class Main {
	static int[][] map;
	static List<int[]> inputList;
	static int N, M, K;
	static int min;

	public static void perm(int[] arr, boolean[] used, int cnt) {
		if (cnt == arr.length) {
			int[][] copyMap = new int[N][M];
			for (int i = 0; i < map.length; i++) {
				copyMap[i] = map[i].clone();
			} // 복제 맵 만들기			

			for (int idx = 0; idx < inputList.size(); idx++) {
				int[] input = inputList.get(arr[idx]);
				boolean[][] vis = new boolean[N][M];
				int r = input[0];
				int c = input[1];
				int s = input[2];

				int rStart = r - s - 1;
				int cStart = c - s - 1;
				int rEnd = r + s - 1;
				int cEnd = c + s - 1;

				int rIdx;
				int cIdx;
				//System.out.println("idx" + idx);
				while (vis[rStart][cStart] == false) {
					ArrayList<pos> list = new ArrayList();
					cIdx = cStart;
					rIdx = rStart;
					for (cIdx = cStart; cIdx < cEnd; cIdx++) {
						vis[rIdx][cIdx] = true;
						list.add(new pos(rIdx, cIdx, copyMap[rIdx][cIdx]));
					}
					for (rIdx = rStart; rIdx < rEnd; rIdx++) {
						vis[rIdx][cIdx] = true;
						list.add(new pos(rIdx, cIdx, copyMap[rIdx][cIdx]));
					}
					for (; cIdx > cStart; cIdx--) {
						vis[rIdx][cIdx] = true;
						list.add(new pos(rIdx, cIdx, copyMap[rIdx][cIdx]));
					}
					for (; rIdx > rStart; rIdx--) {
						vis[rIdx][cIdx] = true;
						list.add(new pos(rIdx, cIdx, copyMap[rIdx][cIdx]));
					}
					// 아직 리스트를 copyMap에 갱신 안함
					if (!list.isEmpty()) {
						int tailval = list.get(list.size() - 1).val;
						for (int i = list.size() - 1; i > 0; i--) {
							list.get(i).val = list.get(i - 1).val;
						}
						list.get(0).val = tailval;
					}
					for (int i = 0; i < list.size(); i++) {
						copyMap[list.get(i).r][list.get(i).c] = list.get(i).val;
					}
//					for (int i = 0; i < N; i++) {
//						for (int j = 0; j < M; j++) {
//							System.out.print(vis[i][j]);
//						}
//						System.out.println();
//					}
//
//					for (pos e : list) {
//						System.out.print(e.val);
//					}
//					System.out.println();

					rStart++;
					cStart++;
					rEnd--;
					cEnd--;
				} // 돌린다
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(copyMap[i][j]);
//					}
//					System.out.println();
//				}
			}

			int[] rowSum = new int[N];
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					rowSum[i] += copyMap[i][j];
				}
			}
			for (int i = 0; i < N; i++) {
				min = Math.min(min, rowSum[i]);
			}
			//System.out.println("min" + min);
			Main.min = Math.min(Main.min, min);
			return;
		}
		for (int i = 0; i < used.length; i++) {
			if (!used[i]) {
				used[i] = true;
				arr[cnt] = i;
				perm(arr, used, cnt + 1);
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = input[0];
		M = input[1];
		K = input[2];

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		min = Integer.MAX_VALUE;
		inputList = new ArrayList();
		for (int i = 0; i < K; i++) {
			int[] rotateInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			inputList.add(rotateInput);
		}
		perm(new int[inputList.size()], new boolean[inputList.size()], 0);
		System.out.println(min);
	}
}