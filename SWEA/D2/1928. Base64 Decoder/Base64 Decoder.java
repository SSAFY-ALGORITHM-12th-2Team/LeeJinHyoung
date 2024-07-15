import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for(int testCase=1; testCase<=T; testCase++) {
            String str = br.readLine();
            String result = new String(Base64.getDecoder().decode(str));
            System.out.println("#" + testCase + " " + result);
        }

	}

}
