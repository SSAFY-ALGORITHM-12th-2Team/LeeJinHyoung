import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());

			int[] Table = new int[11];

			Table[1] = 1;
			Table[2] = 2;
			Table[3] = 4;

			for (int j = 4; j <= n; j++) {
				Table[j] = Table[j - 1] + Table[j - 2] + Table[j - 3];
			}
			System.out.println(Table[n]);

		}
	}
}
