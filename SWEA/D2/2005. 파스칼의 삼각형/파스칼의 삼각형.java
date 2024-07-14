import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();

			int[][] box = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == 0) {
						box[i][j] = 1;
						break;
					} else if (j == 0)
						box[i][j] = 1;
					else if (j == i)
						box[i][j] = 1;
					else if (i >= 2 && j >= 1) {
						box[i][j] = box[i - 1][j - 1] + box[i - 1][j];
					}
				}
			}
			System.out.println("#"+test_case);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(box[i][j]==0)continue;
					else System.out.print(box[i][j]+" ");
				}
				System.out.println();
			}

		}
	}

}
