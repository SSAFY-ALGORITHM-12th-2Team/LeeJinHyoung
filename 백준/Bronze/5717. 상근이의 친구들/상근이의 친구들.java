import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		while (input[0] != 0 && input[1] != 0) {
			System.out.println(input[0] + input[1]);

			input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
	}
}