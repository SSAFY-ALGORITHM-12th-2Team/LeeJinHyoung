import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] border = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int N = border[0];
		int M = border[1];

		int[][] board = new int[N][N];
		int[][] sumBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			sumBoard[i][0] = board[i][0];
			for (int j = 1; j < N; j++) {
				sumBoard[i][j] = board[i][j] + sumBoard[i][j - 1];
			}
		}
		int[][] order = new int[M][4];

		for (int i = 0; i < M; i++) {
			order[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int x1 = order[i][0] - 1;
			int y1 = order[i][1] - 1;
			int x2 = order[i][2] - 1;
			int y2 = order[i][3] - 1;
			int sum = 0;
			for (int row = x1; row <= x2; row++) {
				if (y1 > 0)
					sum += sumBoard[row][y2] - sumBoard[row][y1 - 1];
				else
					sum += sumBoard[row][y2];
			}
			System.out.println(sum);
		}

	}
}