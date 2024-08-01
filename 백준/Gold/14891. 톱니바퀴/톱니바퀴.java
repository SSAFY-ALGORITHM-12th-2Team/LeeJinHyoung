import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	public static ArrayList<Integer> cw(ArrayList<Integer> a) {
		a.add(0, a.remove(7));
		return a;
	}

	public static ArrayList<Integer> ccw(ArrayList<Integer> a) {
		a.add(a.remove(0));
		return a;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer>[] Gear = new ArrayList[4];

		for (int i = 0; i < 4; i++) {
			Gear[i] = new ArrayList<>();
			int[] input = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < 8; j++) {
				Gear[i].add(input[j]);
			}
		}

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			int input[] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int num = input[0] - 1;
			int dir = input[1];
			int[] total_dir = new int[4];

			total_dir[num] = dir;

			// 2번과 6번이 검사해야하는 부분
			// 동시에 값을 넣지 않으면 변이된 값이 들어갈 수 있다는 말이 무슨 의미지..?

			// 아아 확인 기어를 값을 변화해두고 나중에 값을 확인하면 변한 값이 있기에 문제가 된다 이거구나

			// 그럼 기어가 일치하는지 전체 확인 후 돌아갈 기어만 정한다

			for (int ch = num + 1; ch < 4; ch++) {
				if (Gear[ch].get(6) != Gear[ch - 1].get(2)) {
					total_dir[ch] = total_dir[ch-1] * -1;
				}
			}
			for (int ch = num - 1; ch >= 0; ch--) {
				if(Gear[ch].get(2)!=Gear[ch+1].get(6)) {
					total_dir[ch]=total_dir[ch+1]*-1;
				}
			}
			for (int ch = 0; ch < 4; ch++) {
				if (total_dir[ch] == 1) {
					cw(Gear[ch]);
				} else if (total_dir[ch] == -1) {
					ccw(Gear[ch]);
				}
			}
			
		}
		// 정답 출력
		int Answer = 0;
		for (int i = 0; i < 4; i++) {
			if (Gear[i].get(0) == 1)
				Answer += Math.pow(2, i);
		}

		System.out.println(Answer);
	}
}