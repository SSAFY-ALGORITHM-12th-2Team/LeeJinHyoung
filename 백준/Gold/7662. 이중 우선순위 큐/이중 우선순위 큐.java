import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			TreeMap<Long,Long>pq=new TreeMap<Long, Long>();
			// 높은 것부터 나오는 우선순위큐
			int k = Integer.parseInt(br.readLine());

			for (int i = 0; i < k; i++) {
				String[] input = br.readLine().split(" ");

				if (input[0].equals("I")) {
					long num=Long.parseLong(input[1]);
					if(pq.containsKey(num)) {
						pq.replace(num, pq.get(num)+1);
					} else {
						pq.put(num, (long)1);
					}
					
				} else if (input[0].equals("D")) {
					if (!pq.isEmpty() && input[1].equals("-1")) {
						if(pq.get(pq.firstKey())==1) {
							pq.remove(pq.firstKey());
						} else if(pq.get(pq.firstKey())>1) {
							pq.replace(pq.firstKey(), pq.get(pq.firstKey())-1);
						}
					} else if (!pq.isEmpty() && input[1].equals("1")) {
						if(pq.get(pq.lastKey())==1) {
							pq.remove(pq.lastKey());
						} else if(pq.get(pq.lastKey())>1) {
							pq.replace(pq.lastKey(), pq.get(pq.lastKey())-1);
						}
					}
				}
			}
			// System.out.println(minq.size() + " " + maxq.size());
			if (pq.isEmpty()) {
				bw.write("EMPTY\n");
			} else {
				bw.write(String.valueOf(pq.lastKey()) + " " + String.valueOf(pq.firstKey())+"\n");
			}
		}
		bw.flush();
	}
}