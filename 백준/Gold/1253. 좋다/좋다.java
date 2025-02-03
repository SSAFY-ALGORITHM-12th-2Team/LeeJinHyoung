import java.util.*;
import java.util.stream.Stream;
import java.awt.Point;
import java.io.*;

public class Main {

	// 해당 숫자 갯수를 세기 위해서 map 사용

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] ary = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		// 입력 끝
		// 문제의 핵심 조건 : 수의 위치가 다르면 값이 같아도 다른 수다..
		Arrays.sort(ary);

		int result = 0;
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N - 1;
			while (true) {
				if (left == i) {
					left++;
				} else if (right == i) {
					right--;
				}
				if (left >= right)
					break;

				if (ary[left] + ary[right] > ary[i])
					right--;
				else if (ary[left] + ary[right] < ary[i])
					left++;
				else { // 좋다!
					result++;
					break;
				}
			}
		}
		System.out.println(result);
	}
}