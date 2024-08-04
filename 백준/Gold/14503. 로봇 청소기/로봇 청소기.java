import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int R, C; //board size
	static int y, x; //로봇 좌표
	static int d;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int[][] board;
	
	public static void main(String[] args) throws Exception{
		//입력 정보
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		
		//board 채워주기
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//결과가 될 res
		int res = 0;
		
		//종료조건을 만나면 멈춰주기 위한 run 입니다.
		boolean run = true;
		while(run) {
			
			//1.청소되지 않은칸이면 청소하기 (현재 도착한 칸이 0이라면 청소한 칸수를 1 증가시키고 청소된 뜻으로 2라고 지정해줍니다. )
			if(board[y][x] == 0) {
				res++;
				board[y][x] = 2;
			}
			
			//그리고 4방향을 한번씩 바라보며 하나라도 청소되지 않은 빈칸이 있는지 확인하여
			//check에 표시해줍니다.
			boolean check = false;
			for (int i = 0; i < 4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(0<=ny && ny<R && 0<=nx && nx<C) {
					if(board[ny][nx] == 0) check = true;
				}
			}
			
			//3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			if(check) {
				//반시계방향으로 로봇의 머리를 회전시켜 준 뒤
				if(0<d) d -= 1;
				else d = 3;
				
				//해당의 앞 방향이 청소되지 않은 빈 칸인지 판단합니다.
				int ny = y+dy[d];
				int nx = x+dx[d];
				if(0<=ny && ny<R && 0<=nx && nx<C) {
					
					//만약 해당 방향이 청소되지 않은 빈 칸인경우 해당 위치로 로봇의 위치를 이동시킵니다.
					if(board[ny][nx] == 0) {
						y = ny;
						x = nx;
					}
				}
				
			//2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			} else {
				
				//현재 로봇 머리방향의 반대 방향을 찾아 val에 담아줍니다.
				int val = -1;
				if(d<2) val = d+2;
				else val = d%2;
				
				//로봇의 후진 방향으로 한칸 이동해본 뒤 1(벽)이 아닌경우 이동해주고
				//만약 1이라면 run을 false로 바꾸어 이동을 멈춰줍니다.
				int ny = y+dy[val];
				int nx = x+dx[val];
				if(0<=ny && ny<R && 0<=nx && nx<C) {
					if(board[ny][nx] != 1) {
						y = ny;
						x = nx;
					} else {
						run = false;
					}
				}
			}
		}
		
		//위의 while이 끝나면 나온 결과를 출력해줍니다.
		System.out.println(res);
	}
}