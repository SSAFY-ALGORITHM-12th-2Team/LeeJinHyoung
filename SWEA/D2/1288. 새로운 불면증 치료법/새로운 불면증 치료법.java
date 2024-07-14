import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			boolean[] check=new boolean[10];
			int N=Integer.parseInt(br.readLine());
			int coeff=1;//계수
			while(true) {
				int num=coeff*N;
				int[] num_arr=Stream.of(String.valueOf(num).split("")).mapToInt(Integer::parseInt).toArray();
				
				for(int a:num_arr) {
					check[a]=true;
				}
				
				boolean flag=true;
				for(boolean isTrue:check) {
					if(isTrue==false) {
						flag=false;
						break;
					}
				}
				if(flag==true) {
					break;
				}
				coeff++;
			}
			System.out.println("#"+test_case+" "+coeff*N);
		}
	}
}