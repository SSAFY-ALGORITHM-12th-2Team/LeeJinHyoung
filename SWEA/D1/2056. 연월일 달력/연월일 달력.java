import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		int[] valid= {31,28,31,30,31,30,31,31,30,31,30,31};
		
		for(int test_case=1;test_case<=T;test_case++) {
			int[] arr=Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

			StringBuffer year=new StringBuffer();
			StringBuffer month=new StringBuffer();
			StringBuffer day=new StringBuffer();
			for(int j=0;j<4;j++) {
				year.append(arr[j]);
			}
			
			for(int j=4;j<6;j++) {
				month.append(arr[j]);
			}
			if (Integer.valueOf(month.toString()) < 1 || Integer.valueOf(month.toString()) > 12) {
				System.out.println("#"+test_case+" "+"-1");
				continue;
			}
			for(int j=6;j<8;j++) {
				day.append(arr[j]);
			}
			if (valid[Integer.valueOf(month.toString())-1] < 1 || valid[Integer.valueOf(month.toString())-1] < Integer.parseInt(day.toString())) {
				System.out.println("#"+test_case+" "+"-1");
				continue;
			}
			System.out.println("#"+test_case+" "+year+"/"+month+"/"+day);
		}

	}

}
