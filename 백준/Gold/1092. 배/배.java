import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] crain = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = Integer.parseInt(br.readLine());
		int[] box = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		List<Integer> sortedCrain = new ArrayList<>(Arrays.stream(crain.clone()).boxed().collect(Collectors.toList()));
		List<Integer> sortedBox = new ArrayList<>(Arrays.stream(box.clone()).boxed().collect(Collectors.toList()));
		Collections.sort(sortedCrain);
		Collections.sort(sortedBox);

//		만약 가장 큰 크레인으로도 옮길 수 없는 화물이 있다면?
		if (sortedCrain.get(N - 1) < sortedBox.get(M - 1)) {
			System.out.println(-1);
			return;
		}

		int time = 0;
		while (!sortedBox.isEmpty()) {
			for (int i = 0; i < sortedCrain.size(); i++) {

				int index = Collections.binarySearch(sortedBox, sortedCrain.get(i));
				if (index < 0) {
					index = -index - 2;
				} else if (index > 0) {
					index--;
				}
				if (index < 0)
					continue;
				sortedBox.remove(index);
			}
			time++;
		}
		System.out.println(time);
	}
}