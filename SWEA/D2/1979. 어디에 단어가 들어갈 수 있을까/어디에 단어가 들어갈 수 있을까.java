import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int[] input=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int N=input[0];
			int K=input[1];
			int Answer=0;
			
			int[][] board=new int[N][N];
			for(int i=0;i<N;i++) {
				board[i]=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			for(int i=0;i<N;i++) {
				int str_cnt=0;
				for(int j=0;j<N;j++) {
					if(board[i][j]==1) {
						str_cnt++;
					}
					else if(board[i][j]==0) {
						if(str_cnt==K) Answer++;
						str_cnt=0;
					}
				}
				if(str_cnt==K) Answer++;
			}//행 검사
			for(int i=0;i<N;i++) {
				int str_cnt=0;
				for(int j=0;j<N;j++) {
					if(board[j][i]==1) {
						str_cnt++;
					}
					else if(board[j][i]==0) {
						if(str_cnt==K) Answer++;
						str_cnt=0;
					}
				}
				if(str_cnt==K) Answer++;
			}//행 검사
			System.out.println("#"+tc+" "+Answer);
		}
	}
}