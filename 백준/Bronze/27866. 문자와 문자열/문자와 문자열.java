import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] S = br.readLine().split("");
		int i = Integer.parseInt(br.readLine());
		System.out.println(S[i - 1]);
	}
}