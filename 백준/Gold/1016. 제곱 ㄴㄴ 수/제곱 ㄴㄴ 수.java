import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		long min = sc.nextLong();
		long max = sc.nextLong();

//		조건이 큰수이긴 하나 실질적으로 계산하는 수는 백만개다.
//		즉, 잘 쳐줘야 O(n^2)미만으로 계산을 끝내야한다.
//		굳이 숫자를 더하지 말고 빼야할 숫자를 계산해서 넣는다.
		boolean[] isRemoved = new boolean[(int) (max - min + 1)];

		for (long div = 2; div * div <= max; div++) {
			long pow = div * div;
			long remainder = (min % pow);

			long start = min % pow == 0 ? min : min + (pow - (min % pow));
			for (long i = start; i <= max; i += pow) {
				if (isRemoved[(int) (i - min)])
					continue;
				isRemoved[(int) (i - min)] = true;
			}
		}

		int answer = 0;
		for (boolean b : isRemoved) {
			if (!b)
				answer++;
		}
		System.out.println(answer);
	}
}