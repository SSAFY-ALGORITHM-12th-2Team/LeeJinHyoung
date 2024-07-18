import java.util.*;
import java.util.stream.Stream;
import java.io.*;
import java.lang.reflect.Array;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		int T =Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] origin = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			
			int[] init =new int[origin.length];

			int cnt = 0;
			for (int i = 0; i < origin.length; i++) {
				if (origin[i]!=init[i]) {
					cnt++;
					for(int j=i;j<origin.length;j++) {
						init[j]=origin[i];
					}
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}

}
