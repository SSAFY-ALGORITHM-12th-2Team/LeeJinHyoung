import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static class atom {
		int x;
		int y;
		int direction;
		int energy;

		public atom(int x, int y, int direction, int energy) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.energy = energy;
		}

		@Override
		public String toString() {
			return "atom [row=" + x + ", col=" + y + ", direction=" + direction + ", energy=" + energy + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			List<atom> atomList = new ArrayList<atom>();

			atom[][] board = new atom[4001][4001];

			for (int i = 0; i < N; i++) {
				int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				atomList.add(new atom((input[0] + 1000) * 2, (input[1] + 1000) * 2, input[2], input[3]));
			}
			int total = 0;
			while (!atomList.isEmpty()) {
				for (int i = 0; i < atomList.size(); i++) {
					switch (atomList.get(i).direction) {
					case 0:
						atomList.get(i).y++;
						break;
					case 1:
						atomList.get(i).y--;
						break;
					case 2:
						atomList.get(i).x--;
						break;
					case 3:
						atomList.get(i).x++;
						break;
					}
					if (atomList.get(i).x < 0 || atomList.get(i).y < 0 || atomList.get(i).x > 4000
							|| atomList.get(i).y > 4000) {
						atomList.remove(i);
						i--;
					}
				}
				Collections.sort(atomList, new Comparator<atom>() {
					@Override
					public int compare(Solution.atom o1, Solution.atom o2) {
						// TODO Auto-generated method stub
						if (o1.x == o2.x) {
							return o1.y - o2.y;
						}
						return o1.x - o2.x;
					}
				});
				for (int i = 0; i < atomList.size() - 1; i++) {
					boolean flag = false;
					while (atomList.size() > i + 1 && atomList.get(i).x == atomList.get(i + 1).x
							&& atomList.get(i).y == atomList.get(i + 1).y) {
						flag = true;
						total += atomList.get(i + 1).energy;
						atomList.remove(i + 1);
					}
					if (flag) {
						total += atomList.get(i).energy;
						atomList.remove(i);
					}
				}
			}
			System.out.println("#" + tc + " " + total);
		}
	}
}