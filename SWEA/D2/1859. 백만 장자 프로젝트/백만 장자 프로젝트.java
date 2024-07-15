import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int N=Integer.parseInt(br.readLine());
			long money=0;
			
			int[] arr=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int max=arr[arr.length-1];
			for(int i=arr.length-1;i>=0;i--) {
				if(max<arr[i]) {
					max=arr[i];
				}
				else if(max>arr[i]) {
					money+=max-arr[i];
				}
			}
			System.out.println("#"+tc+" "+money);
		}
	}
}
