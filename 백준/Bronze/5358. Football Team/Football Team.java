import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input;
		while ((input = br.readLine()) != null) {
			String inputSplit[] = input.split("");
			for (int i = 0; i < inputSplit.length; i++) {
				switch (inputSplit[i]) {
				case "i":
					inputSplit[i] = "e";
					break;
				case "e":
					inputSplit[i] = "i";
					break;
				case "I":
					inputSplit[i] = "E";
					break;
				case "E":
					inputSplit[i] = "I";
					break;
				}
			}
			String answer = String.join("", inputSplit);
			System.out.println(answer);
		}
	}

}
