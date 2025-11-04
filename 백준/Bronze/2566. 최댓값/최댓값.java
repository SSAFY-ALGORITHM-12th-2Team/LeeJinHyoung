import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] board = new int[9][9];
		int maxValue = -1;
		int row = -1;
		int col = -1;
		for (int i = 0; i < 9; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < 9; j++) {
				if (board[i][j] > maxValue) {
					maxValue = board[i][j];
					row = i;
					col = j;
				}
			}
		}
		System.out.println(maxValue);
		System.out.println((row + 1) + " " + (col + 1));
	}

}
