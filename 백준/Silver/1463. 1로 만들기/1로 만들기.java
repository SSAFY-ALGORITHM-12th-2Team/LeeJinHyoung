import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int X = Integer.parseInt(br.readLine());
		
		int[] dist=new int[(int) Math.pow(10, 6)+1];
		
		Queue<Integer>q=new LinkedList<>();
		q.offer(X);
		dist[X]=0;
		if(X==1) {
			System.out.println(0);
		}
		else {
		while(dist[1]==0) {
			int cur=q.poll();
			
			for(int i=1;i<=3;i++) {
				int pos;
				if(i==1) pos=cur-i;
				else if(cur%i==0) pos=cur/i;
				else continue;
				
				if(pos<0) continue;
				if(dist[pos]>0) continue;
				
				q.offer(pos);
				dist[pos]=dist[cur]+1;				
			}
		}
		System.out.println(dist[1]);
		}
	}
}