import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<String> input = Arrays.stream(sc.next().split("")).collect(Collectors.toList());
		for (int i = 0; i < input.size() - 1; i++) {
			if (input.get(i).equals(input.get(i + 1))) {
				input.remove(i--);
			}
		}
		StringBuilder answer = new StringBuilder();
		for (String i : input)
			answer.append(i);
		System.out.println(answer);
	}
}