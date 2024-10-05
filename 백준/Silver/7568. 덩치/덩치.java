import java.util.*;
import java.util.stream.Stream;
import java.io.*;
import java.awt.*;

public class Main {
	static class man {
		int height;
		int weight;
		int sum = 1;

		public man(int height, int weight) {
			super();
			this.height = height;
			this.weight = weight;
		}
	}

	private static boolean allvis(boolean[] vis) {
		for (boolean b : vis) {
			if (!b) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		man[] arr = new man[N];
		for (int i = 0; i < N; i++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			arr[i] = new man(input[0], input[1]);
		}
		man[] sort = Arrays.copyOf(arr, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				else if (arr[i].height < arr[j].height && arr[i].weight < arr[j].weight) {
					sort[i].sum++;
				}
			}
		}
		for (man m : sort) {
			System.out.print(m.sum + " ");
		}

	}
}