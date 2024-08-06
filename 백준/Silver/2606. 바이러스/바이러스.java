import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt(), E = sc.nextInt();
		List<List<Integer>> graph = new ArrayList<>();
		for(int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < E; i++) {
			int t1 = sc.nextInt() - 1, t2 = sc.nextInt() - 1;
			graph.get(t1).add(t2);
			graph.get(t2).add(t1);
		}
		
		boolean[] v = new boolean[V];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(0);
		v[0] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int e : graph.get(cur)) {
				if(v[e]) continue;
				q.add(e);
				v[e] = true;
			}
		}
		
		int ans = 0;
		for(int i = 1; i < V; i++)
			ans += v[i] ? 1 : 0;
		System.out.println(ans);
	}
}
