import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		long remainder = 0;

		for (int i = 0; i < input.length(); i++) {
			remainder = (remainder * 10 + (input.charAt(i) - '0')) % 20000303;
		}
		System.out.println(remainder);
	}
}