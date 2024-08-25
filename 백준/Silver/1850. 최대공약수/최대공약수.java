import java.util.*;
import java.io.*;

public class Main {
	public static long getGCD(long num1, long num2) {
		if (num1 % num2 == 0) {
			return num2;
		}
		return getGCD(num2, num1 % num2);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

		long N = sc.nextLong();
		long M = sc.nextLong();

		for (long l = 0; l < getGCD(N, M); l++) {
			bw.write("1");
		}
		bw.flush();
	}
}