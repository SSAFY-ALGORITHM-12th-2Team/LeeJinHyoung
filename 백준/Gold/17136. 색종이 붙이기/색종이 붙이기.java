import java.util.Scanner;

public class Main{
	static int ans = Integer.MAX_VALUE;
	static boolean[][] dat = new boolean[10][10];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] paper = {5, 5, 5, 5, 5};
		
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				if(sc.nextInt() == 1)
					dat[i][j] = true;
		
		solve(0, paper);
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
	}
	
	public static void solve(int idx, int[] paper) {
		if(idx == 100) {
			int used = 25;
			for(int i = 0; i < paper.length; i++)
				used -= paper[i];
			ans = Math.min(ans, used);
			return;
		}
		if(!dat[idx/10][idx%10])
			solve(idx + 1, paper);
		
		for(int i = 0; i < 5; i++) {
			if(paper[i] == 0) continue;
			if(oob(idx, i)) continue;
			if(!check(idx, i)) continue;
			
			paper[i]--;
			paste(idx, i, false);
			solve(idx + 1, paper);
			paste(idx, i, true);
			paper[i]++;	
		}
	}
	
	public static void paste(int idx, int i, boolean q) {
		int r = idx / 10, c = idx % 10;
		for(int rr = r; rr <= r + i; rr++)
			for(int cc = c; cc <= c + i; cc++)
				dat[rr][cc] = q;
	}
	
	public static boolean check(int idx, int i) {
		int r = idx / 10, c = idx % 10;
		for(int rr = r; rr <= r + i; rr++)
			for(int cc = c; cc <= c + i; cc++)
				if(!dat[rr][cc])
					return false;
		return true;
	}
	
	public static boolean oob(int idx, int i) {
		int r = idx / 10 + i, c = idx % 10 + i;
		return (r >= 10 || c >= 10);
	}
}