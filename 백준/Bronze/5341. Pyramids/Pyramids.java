import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = -1;
		while (N != 0) {
			N = sc.nextInt();
			if (N == 0)
				break;
			int sum = 0;
			for (int i = N; i >= 0; i--) {
				sum += i;
			}
			System.out.println(sum);
		}
	}
}