import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		while (!(input[0] == 0 && input[1] == 0)) {
			Set<Integer> cd = new HashSet<>();
			for (int i = 0; i < input[0]; i++) {
				cd.add(Integer.parseInt(br.readLine()));
			}
			int answer = 0;
			for (int i = 0; i < input[1]; i++) {
				if (cd.contains(Integer.parseInt(br.readLine()))) {
					answer++;
				}
			}
			System.out.println(answer);

			input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
	}
}