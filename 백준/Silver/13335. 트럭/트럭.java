import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int n, w, l;
	static int[] bridge;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = input[0];
		w = input[1];
		l = input[2];

		int[] truckInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		ArrayList<Integer> truck = new ArrayList();
		for (int i = 0; i < truckInput.length; i++) {
			truck.add(truckInput[i]);
		} // 트럭 입력 받았고
		int completeTruck = truck.size();
		bridge = new int[w];
		int time = 0;
		int truckCnt = 0;
		while (!truck.isEmpty() || truckWeight() > 0) {// 다리에 싣는다
			for (int i = 0; i < w - 1; i++) {
				bridge[i] = bridge[i + 1];
			}
			bridge[w - 1] = 0;// 한칸씩 옮기고 다시 칸을 비운다
			if (truck.size() > 0) {
				if (truckWeight() + truck.get(0) <= l) {
					bridge[w - 1] = truck.remove(0);
				} // 차례가 온 트럭이 와도 무게 초과 안나면 다리에 올린다
			}
//			for (int i = 0; i < bridge.length; i++) {
//				System.out.print(bridge[i]);
//			}
//			System.out.println();

			time++;
		}
		System.out.println(time);
	}

	public static int truckWeight() {
		int sum = 0;
		for (int e : bridge) {
			sum += e;
		}
		return sum;
	}
}