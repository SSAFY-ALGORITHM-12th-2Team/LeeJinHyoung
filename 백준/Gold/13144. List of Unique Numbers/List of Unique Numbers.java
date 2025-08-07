import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

//		전체 횟수는 N+...+1인데 1개인 원소로 이루어진 배열은 모두 포함하므로 최소 N개만큼은 있다.
		long answer = 0;

		int left = 0, right = 0;
		Set<Integer> set = new HashSet<>();
		while (right < N) {
//			System.out.println(left + " " + right);
//			만약 left와 right가 같거나
//			left,right 값이 같지 않고 right가 set에 포함되지 않는다면
			if (!set.contains(array[right])) {
				set.add(array[right++]);
			}
//			left,right 값이 같지 않고 right가 set에 포함됐다면
			else if (set.contains(array[right])) {
				set.remove(array[left++]);
				continue;
			}
			answer += right - left;
//			System.out.println(answer);
		}
//		여기까지 하면 어떤 결과로든 right는 N에 다다르게 된다.
		System.out.println(answer);
	}
}