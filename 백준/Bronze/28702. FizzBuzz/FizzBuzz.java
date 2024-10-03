import java.util.*;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		String input[] = new String[3];
		int[] ary = new int[3];
		for (int i = 0; i < 3; i++) {
			input[i] = sc.next();
			if (input[i].equals("FizzBuzz")) {

			} else if (input[i].equals("Fizz")) {

			} else if (input[i].equals("Buzz")) {

			} else {
				ary[i] = Integer.parseInt(input[i]);
			}
		}

		for (int i = 0; i < ary.length; i++) {
			if (ary[i] > 0) {
				switch (i) {
				case 0:
					ary[i + 1] = ary[i] + 1;
					ary[i + 2] = ary[i] + 2;
					break;
				case 1:
					ary[i - 1] = ary[i] - 1;
					ary[i + 1] = ary[i] + 1;
					break;
				case 2:
					ary[i - 1] = ary[i] - 1;
					ary[i - 2] = ary[i] - 2;
					break;
				}
			}
		}
		int ans = ary[2] + 1;
		if (ans % 5 == 0 && ans % 3 == 0) {
			System.out.println("FizzBuzz");
		} else if (ans % 3 == 0 && ans % 5 != 0) {
			System.out.println("Fizz");
		} else if (ans % 3 != 0 && ans % 5 == 0) {
			System.out.println("Buzz");
		} else {
			System.out.println(ary[2] + 1);
		}
	}
}