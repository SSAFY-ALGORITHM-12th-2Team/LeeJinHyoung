import java.util.*;
import java.util.stream.Stream;
import java.io.*;

class pos {
	int x;
	int y;
	public pos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int R = input[0];
		int C = input[1];
		int N = input[2];

		String[][] board = new String[R][C];
		int[][] time = new int[R][C];

		for (int row = 0; row < R; row++) {
			board[row] = br.readLine().split("");
		} // 문자 입력
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (board[row][col].equals("."))
					time[row][col] = 0;
				else if (board[row][col].equals("O"))
					time[row][col] = 1;
			}
		} // 폭탄 타이머 입력

		int[] dx = { 1, 0, -1, 0 };// row
		int[] dy = { 0, 1, 0, -1 };// col
		Queue<pos> q = new LinkedList();

		for (int t = 1; t < N; t++) {
			if (t % 2 == 1) {
				for (int row = 0; row < R; row++) {
					for (int col = 0; col < C; col++) {
						if (time[row][col] == 0 && board[row][col].equals(".")) {
							time[row][col] = 2;
							board[row][col] = "O";
						}
					}
				} // 폭탄 설치
			} else {
				for (int row = 0; row < R; row++) {// 행 순회
					for (int col = 0; col < C; col++) {// 열 순회
						if (time[row][col] > 0) {
							time[row][col]--;
						}
						if (time[row][col] == 0) {
							q.add(new pos(row, col));
						}
					}
				} // 폭탄 폭발 위치를 q에 넣는다
				while (!q.isEmpty()) {
					pos pos = q.poll();
					int row = pos.x;
					int col = pos.y;
					for (int dir = 0; dir < 4; dir++) {
						int nx = row + dx[dir];
						int ny = col + dy[dir];
						if (nx >= 0 & ny >= 0 & nx < R & ny < C) {
							time[nx][ny]=0;
						}
					}
				}//해당 위치의 4방 터뜨리기
			}
			for(int row=0;row<R;row++) {
				for(int col=0;col<C;col++) {
					if(time[row][col]==0) {
						board[row][col]=".";
					} else {
						board[row][col]="O";
					}
				}
			}
			
//			for (int[] arr : time) {
//				for (int a : arr) {
//					System.out.print(a);
//				}
//				System.out.println();
//			} // 상태 확인
		}
		for (String[] str : board) {
			for (String s : str) {
				System.out.print(s);
			}
			System.out.println();
		}
	}
}