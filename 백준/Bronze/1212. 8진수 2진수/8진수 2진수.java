import java.io.*;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		BigInteger oct = new BigInteger(input, 8); // 8진수 문자열 → BigInteger
	    System.out.println(oct.toString(2));    
	}
}