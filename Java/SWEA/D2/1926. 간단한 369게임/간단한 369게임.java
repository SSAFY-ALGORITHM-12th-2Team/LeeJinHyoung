import java.util.*;
import java.util.stream.Stream;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			int[] arr = Stream.of(String.valueOf(i).split("")).mapToInt(Integer::parseInt).toArray();
			String Answer = "";
			int cnt = 0;
			for (int j = 0; j < arr.length; j++) {
				switch (arr[j]) {
				default:
					if(cnt==0)Answer = Answer + arr[j] + "";
					break;
				case 3:
					Answer="";
					cnt++;
					break;
				case 6:
					Answer="";
					cnt++;
					break;
				case 9:
					Answer="";
					cnt++;
					break;
				}
			}
			
			if (cnt == 0)
				System.out.print(Answer + " ");
			else {
				for (int j = 0; j < cnt; j++) {
					Answer += "-";
				}
				System.out.print(Answer + " ");
			}

		}

	}

}
