import java.util.*;
import java.io.*;
public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			String[] s=br.readLine().split("");
			boolean flag=true;
			for(int i=0;i<s.length/2;i++) {
				if(s[i].equals(s[s.length-1-i])) {
					flag=false;
					break;
				}
			}
			if(flag==true) System.out.println("#"+test_case+" "+0);
			else System.out.println("#"+test_case+" "+1);
		}
	}

}
