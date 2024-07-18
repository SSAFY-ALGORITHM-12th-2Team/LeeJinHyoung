import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {
	//1차시도 39/55
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int[] arr=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int cnt=0;
			boolean impossible=false;
			for(int i=1;i>=0;i--) {
				if(arr[i]>arr[i+1]&&arr[i+1]==1)
				{
					impossible=true;
					
					break;
				}
				while(arr[i]>=arr[i+1]) {
					cnt++;
					arr[i]--;
				}
			}
			for(int i:arr) {
				if(i==0) {
					impossible=true;
				}
			}
			if(impossible==true)
				System.out.println("#"+tc+" "+-1);
			if(impossible==false)
				System.out.println("#"+tc+" "+cnt);
		}
	}
}