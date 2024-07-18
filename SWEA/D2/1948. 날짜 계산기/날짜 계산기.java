import java.util.*;
import java.io.*;

public class Solution{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int[] months= {31,28,31,30,31,30,31,31,30,31,30,31};
		
		int T=sc.nextInt();
		
		for(int test_case=1;test_case<=T;test_case++) {
			int cur_month=sc.nextInt();
			int cur_day=sc.nextInt();
			int next_month=sc.nextInt();
			int next_day=sc.nextInt();
			
			int sum=0;
			if(next_day>=cur_day)
				sum+=next_day-cur_day+1;
			else
				sum+=-(cur_day-1)+next_day;
			while(cur_month!=next_month) {
				if(cur_month>=12) {
					cur_month=1;
				}
				sum+=months[cur_month-1];
				cur_month++;
			}
			
			System.out.println("#"+test_case+" "+sum);
		}
	}
}