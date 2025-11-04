import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		for (int i = 0; i < 5; i++) {
			int e = sc.nextInt();
			answer += e = e < 40 ? 40 : e;
		}
		System.out.println(answer / 5);
	}
}