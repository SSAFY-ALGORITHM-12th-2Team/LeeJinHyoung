import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int up=0;
			int down=0;
			for(int i=0;i<arr.length-1;i++) {
				if(arr[i]<arr[i+1]) {
					if(up<Math.abs(arr[i+1]-arr[i])) {
						up=Math.abs(arr[i+1]-arr[i]);
					}
				} else if(arr[i]>arr[i+1]) {
					if(down<Math.abs(arr[i+1]-arr[i])) {
						down=Math.abs(arr[i+1]-arr[i]);
					}
				}
			}
			System.out.println("#"+tc+" "+up+" "+down);
		}
	}

}
