import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int M = input[1];
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int left = 0;
		int right = 0;
		int sum = A[left];
		int answer = 0;
		while (left < A.length) {
		    if (sum == M) {
		        answer++;
		        sum -= A[left++];
		    } else if (sum < M && right < A.length - 1) {
		        sum += A[++right];
		    } else {
		        sum -= A[left++];
		    }
		}

		System.out.println(answer);
	}
}