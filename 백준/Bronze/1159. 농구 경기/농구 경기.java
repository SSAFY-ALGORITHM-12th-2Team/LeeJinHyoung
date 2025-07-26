import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Map<String, Integer> firstAlphabet = new TreeMap<String, Integer>();

		for (int i = 0; i < N; i++) {
			String[] name = br.readLine().split("");
			if (firstAlphabet.containsKey(name[0])) {
				firstAlphabet.replace(name[0], firstAlphabet.get(name[0]) + 1);
			} else {
				firstAlphabet.put(name[0], 1);
			}
		}
		Iterator it = firstAlphabet.keySet().iterator();
		StringBuilder answer = new StringBuilder();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (firstAlphabet.get(key) >= 5)
				answer.append(key);
		}
		System.out.println(answer.length() == 0 ? "PREDAJA" : answer);
	}
}