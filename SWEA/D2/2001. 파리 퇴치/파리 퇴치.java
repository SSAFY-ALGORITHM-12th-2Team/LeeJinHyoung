import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			int[] input=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N=input[0];
			int M=input[1];
			
			int[][] board=new int[N][];
			int[][] sum=new int[N-M+1][N-M+1];
			for(int i=0;i<N;i++) {
				board[i]=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}//입력 끝
			
			for(int i=0;i<=N-M;i++) {
				for(int j=0;j<=N-M;j++) {
					for(int row=i;row<i+M;row++) {
						for(int col=j;col<j+M;col++) {
							sum[i][j]+=board[row][col];
						}
					}
				}
			}//합 확인
			int max=0;
			for(int i=0;i<=N-M;i++) {
				for(int j=0;j<=N-M;j++) {
					if(max<sum[i][j])max=sum[i][j];
				}	
			}
			System.out.println("#"+test_case+" "+max);
		}
	}
}