import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int R, C;
	static char[][] table;
	static int max;

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void dfs(boolean[] alphabet, int row, int column, int road) {
		max = Math.max(max, road);
		for (int dir = 0; dir < 4; dir++) {
			int nr = row + dr[dir];
			int nc = column + dc[dir];

			if (nr >= 0 && nc >= 0 && nr < R && nc < C && !alphabet[table[nr][nc] - 65]) {
				alphabet[table[nr][nc] - 65] = true;
				dfs(alphabet, nr, nc, road + 1);
				alphabet[table[nr][nc] - 65] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		R = input[0];
		C = input[1];

		table = new char[R][C];

		for (int i = 0; i < R; i++) {
			String[] strinput = br.readLine().split("");
			for (int j = 0; j < strinput.length; j++) {
				table[i][j] = strinput[j].charAt(0);
			}
		}
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(table[i][j]);
//			}
//			System.out.println();
//		}

//		System.out.println(alpha - 65);
		boolean[] alphabet = new boolean[26];
		alphabet[table[0][0] - 65] = true;
		dfs(alphabet, 0, 0, 0);
		System.out.println(max+1);
	}
}