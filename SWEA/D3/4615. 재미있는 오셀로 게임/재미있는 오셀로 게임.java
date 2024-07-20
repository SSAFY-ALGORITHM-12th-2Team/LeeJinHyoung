import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int[] dx = { 1, 0, -1, 0, 1, -1, -1, 1 };
		int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1 };

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = input[0];
			int M = input[1];

			String[][] board = new String[N][N];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					board[i][j]=".";
				}
			}	

			board[N / 2 - 1][N / 2 - 1] = "W";
			board[N / 2][N / 2] = "W";
			board[N / 2 - 1][N / 2] = "B";
			board[N / 2][N / 2 - 1] = "B";

			int t=1;
			for (int i = 0; i < M; i++) {// 모든 경우의 수 나온다
				int[] o = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

				int col = o[0] - 1;
				int row = o[1] - 1;

				if (o[2] == 1) {// 흑돌일 때
					board[row][col] = "B";
					for (int j = 0; j < 8; j++) {
						int k = 1;
						while (true) {
							int ny = row + dy[j] * k;
							int nx = col + dx[j] * k;

							if (nx < 0||ny<0||nx>=N||ny>=N)
								break;
							k++;
							if (board[ny][nx].equals("."))
								break;
							else if (board[ny][nx].equals("B")) {
								for(int mul=1;mul<k;mul++) {
									board[row+dy[j]*mul][col+dx[j]*mul]="B";
								}
								break;
							}
						} // 그럼 반복문이 끝나면 다음 돌 위치가 나온다
					}
				} else if (o[2] == 2) {//백돌일 때
					board[row][col] = "W";
					for (int j = 0; j < 8; j++) {
						int k = 1;
						while (true) {
							int ny = row + dy[j] * k;
							int nx = col + dx[j] * k;

							if (nx < 0||ny<0||nx>=N||ny>=N)
								break;
							k++;
							if (board[ny][nx].equals("."))
								break;
							else if (board[ny][nx].equals("W")) {
								for(int mul=1;mul<k;mul++) {
									board[row+dy[j]*mul][col+dx[j]*mul]="W";
								}
								break;
							} 
						} // 그럼 반복문이 끝나면 다음 돌 위치가 나온다
					}
				}				
			}
			int w = 0;
			int b = 0;
			for (String[] i : board) {
				for (String j : i) {
					if(j.equals("B"))b++;
					else if(j.equals("W"))w++;
				}
			}
			System.out.println("#" + tc + " " + b + " " + w);
		}
	}
}