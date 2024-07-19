import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int N = sc.nextInt();

			int[] river = new int[N];

			for (int i = 0; i < N; i++) {
				river[i] = sc.nextInt();
			} // 입력 끝
			int Answer=0;
			int max=0;
			
			for(int i=2;i<N-2;i++) {
				max=0;
				if(max<river[i-2]) max=river[i-2];
				if(max<river[i-1]) max=river[i-1];
				if(max<river[i+1]) max=river[i+1];
				if(max<river[i+2]) max=river[i+2];
				
				if(river[i]>max)
					Answer+=(river[i]-max);
			}
			System.out.println("#"+tc+" "+Answer);
			
		}
	}
}