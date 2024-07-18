import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			String[] input=br.readLine().split("");
			
			String loop="";
			
			for(int i=0;i<input.length;i++) {
				loop=loop+input[i];
				String compare="";
				for(int j=i+1;j<i+1+loop.length();j++) {
					compare=compare+input[j];
				}
				if(loop.equals(compare)) {
					System.out.println("#"+test_case+" "+loop.length());
					break;
				}
			}
		}

	}

}
