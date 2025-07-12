import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		String[] ISBN = sc.next().split("");

		int sum = 0;

		int index = 0;
		for (int i = 0; i < ISBN.length; i++) {
			if (ISBN[i].equals("*"))
				index = i;
			else if (i % 2 == 0)
				sum += Integer.parseInt(ISBN[i]);
			else if (i % 2 == 1)
				sum += 3 * Integer.parseInt(ISBN[i]);
		}

//		System.out.println(sum + " " + index);
		int answer = 0;
		for (answer = 0;; answer++) {
			if (index % 2 == 0 && (sum + answer) % 10 == 0)
				break;
			else if (index % 2 == 1 && (sum + (3 * answer)) % 10 == 0)
				break;
		}
		System.out.println(answer);
	}
}
