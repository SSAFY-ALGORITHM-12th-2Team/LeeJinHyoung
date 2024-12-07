import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	public static int paste(int param) {
		String str = String.valueOf(param) + 1;
		return Integer.parseInt(str);
	}

	public static int multiply(int param) {
		return param * 2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();
		int B = sc.nextInt();

		int cnt = 0;
		boolean flag = true;
		// B를 2로 나눌수 있다면 나누고 만약 끝자리가 1이 있다면 가능 그 외에는 전부 불가

		while (B != A) {
			int[] arr = Stream.of(String.valueOf(B).split("")).mapToInt(Integer::parseInt).toArray();
			if (B % 2 == 0) {
				B /= 2;
			} else if (B % 2 == 1 && arr[arr.length - 1] == 1 && arr.length - 1 > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < arr.length - 1; i++) {
					sb.append(arr[i]);
				}
				B = Integer.parseInt(sb.toString());
			} else {
				flag = false;
				break;
			}
			cnt++;
		}
		if (flag) {
			System.out.println(cnt + 1);
		} else {
			System.out.println(-1);
		}
	}
}
