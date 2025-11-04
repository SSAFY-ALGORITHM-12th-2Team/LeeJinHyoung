import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
			System.out.println(input[0] + input[1]);
		}
	}

}
