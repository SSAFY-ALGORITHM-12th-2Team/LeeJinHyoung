import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] cw = new int[2000000];
		int[] ccw = new int[2000000];
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]), M = Integer.parseInt(nm[1]);
		
		String[] ns = br.readLine().split(" ");
		for(int i = 0; i < ns.length; i++) {
			int cur = Integer.parseInt(ns[i]);
			int prv = Integer.parseInt(ns[(i - 1 + ns.length) % ns.length]);
			int nxt = Integer.parseInt(ns[(i + 1) % ns.length]);
			cw[cur] = nxt;
			ccw[cur] = prv;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			String[] ops = br.readLine().split(" ");
			int cur = Integer.parseInt(ops[1]);
			int inp, nxt = cw[cur], prv = ccw[cur];
			switch(ops[0]) {
			case "BN" :
				inp = Integer.parseInt(ops[2]);
				sb.append(nxt);
				cw[cur] = inp;
				cw[inp] = nxt;
				ccw[nxt] = inp;
				ccw[inp] = cur;
				break;
			case "BP" :
				inp = Integer.parseInt(ops[2]);
				sb.append(prv);
				cw[prv] = inp;
				cw[inp] = cur;
				ccw[cur] = inp;
				ccw[inp] = prv;
				break;
			case "CN" :
				sb.append(nxt);
				cw[cur] = cw[nxt];
				ccw[cw[cur]] = cur;
				break;
			case "CP" :
				sb.append(prv);
				ccw[cur] = ccw[prv];
				cw[ccw[cur]] = cur;
				break;
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
	