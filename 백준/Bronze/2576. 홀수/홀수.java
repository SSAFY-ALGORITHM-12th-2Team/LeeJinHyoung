import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int min = Integer.MAX_VALUE;
		int sum = 0;
		boolean exist = false;
		for (int i = 0; i < 7; i++) {
			int input = sc.nextInt();
			if (input % 2 == 1) {
				exist = true;
				min = Math.min(min, input);
				sum += input;
			}
		}
		if (!exist)
			System.out.println(-1);
		else {
			System.out.println(sum);
			System.out.println(min);
		}
	}
}