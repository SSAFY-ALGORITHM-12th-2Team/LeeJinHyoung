import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		String A = sc.next();
		String B = sc.next();
		String C = sc.next();

		int numA = Integer.parseInt(A);
		int numB = Integer.parseInt(B);
		int numC = Integer.parseInt(C);

		String concat = A + B;
		System.out.println(numA + numB - numC);
		System.out.println(Integer.parseInt(concat) - Integer.parseInt(C));
	}
}