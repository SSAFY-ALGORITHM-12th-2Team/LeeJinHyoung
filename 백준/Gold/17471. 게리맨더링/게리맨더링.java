import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] district;
	static List<List<Integer>> graph;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		district = new int[N];
		for(int i = 0; i < N; i++)
			district[i] = sc.nextInt();
		
		graph = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int T = sc.nextInt();
			graph.add(new ArrayList<Integer>());
			for(int j = 0; j < T; j++) {
				graph.get(i).add(sc.nextInt() - 1);
			}
		}
		
		mcomb(0, N, new boolean[N]);
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
	}
	
	public static void mcomb(int idx, int n, boolean[] sel) {
		if(idx == n) {
			List<Integer> selA = new ArrayList<>();
			List<Integer> selB = new ArrayList<>();
			
			for(int i = 0; i < n; i++)
				if(sel[i])
					selA.add(i);
				else
					selB.add(i);
			
			if(selA.size() == 0 || selB.size() == 0)
				return;
			if(!bfs(n, selA) || !bfs(n, selB))
				return;
			
			int sumA = 0, sumB = 0;
			for(int e : selA)
				sumA += district[e];
			for(int e : selB)
				sumB += district[e];
			
			ans = Math.min(ans, Math.abs(sumA - sumB));
			return;
		}

		sel[idx] = true;
		mcomb(idx + 1, n, sel);
		sel[idx] = false;
		mcomb(idx + 1, n, sel);
	}
	
	public static boolean bfs(int n, List<Integer> li) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] s = new boolean[n]; // in set check
		boolean[] v = new boolean[n]; // visit check
		
		q.add(li.get(0));
		v[li.get(0)] = true;
		for(int l : li)
			s[l] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int e : graph.get(cur)) {
				if(v[e]) continue;
				if(!s[e]) continue;
				
				v[e] = true;
				q.add(e);
			}
		}

		for(int l : li)
			if(!v[l])
				return false;
		return true;
	}
}