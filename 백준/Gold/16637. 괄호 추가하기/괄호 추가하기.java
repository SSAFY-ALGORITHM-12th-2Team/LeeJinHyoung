import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main{
	static long ans = Long.MIN_VALUE;
	static String form;
	static long[] nums;
	static char[] ops;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		form = sc.next();
		
		nums = new long[N/2 + 1];
		ops = new char[N/2];
		
		nums[0] = form.charAt(0) - '0';
		for(int i = 1; i <= N / 2; i++) {
			nums[i] = form.charAt(i*2) - '0';
			ops[i-1] = form.charAt(i*2 - 1);
		}
		
		comb(0, N/2, new boolean[N/2]);
		System.out.println(ans);
	}
	
	public static void comb(int idx, int N, boolean first[]) {
		if(idx >= N) {
			List<Long> lnums = new LinkedList<>();
			List<Character> lops = new LinkedList<>();
			
			lnums.add(nums[0]);
			for(int i = 0; i < first.length; i++) {
				if(!first[i]) {
					lops.add(ops[i]);
					lnums.add(nums[i+1]);
				}
				else {
					lnums.remove(lnums.size() - 1);
					lnums.add(calc(nums[i], nums[i+1], ops[i]));
				}
			}
			
			long res = lnums.get(0);
			for(int i = 0; i < lops.size(); i++)
				res = calc(res, lnums.get(i + 1), lops.get(i));
			
			ans = Math.max(ans, res);
			return;
		}
		first[idx] = true;
		comb(idx + 2, N, first);
		first[idx] = false;
		
		comb(idx + 1, N, first);
	}
	
	public static long calc(long o1, long o2, char op) {
		switch(op) {
		case '+' : return o1 + o2;
		case '-' : return o1 - o2;
		default : return o1 * o2;
		}
	}
}