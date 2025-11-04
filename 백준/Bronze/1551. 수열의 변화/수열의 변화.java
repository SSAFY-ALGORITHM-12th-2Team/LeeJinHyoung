import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int K = input[1];
		int[] arrayInput = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
		List<Integer> array = new ArrayList<>(Arrays.stream(arrayInput).boxed().collect(Collectors.toList()));
		for (int i = 0; i < K; i++) {
			List<Integer> temp = new ArrayList<>();
			for (int len = 0; len < array.size() - 1; len++) {
				temp.add(array.get(len + 1) - array.get(len));
			}
			array.clear();
			array.addAll(temp);
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < array.size(); i++) {
			if (i == array.size() - 1) {
				answer.append(array.get(i));
			} else {
				answer.append(array.get(i) + ",");
			}
		}
		System.out.println(answer);
	}

}
