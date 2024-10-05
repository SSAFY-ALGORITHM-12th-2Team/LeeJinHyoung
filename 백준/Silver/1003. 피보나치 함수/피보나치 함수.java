import java.util.*;
import java.io.*;
public class Main {

	public static int[] fibo(int N)
	{
		int Answer[]=new int[2];
			
			ArrayList<Integer> Fibo=new ArrayList<Integer>();
			Fibo.add(0);
			Fibo.add(1);
			
			int zerocount=0;
			int onecount=0;
			
			if(N==0)
			{
				zerocount++;
				Answer[0]=zerocount;
				Answer[1]=onecount;
				return Answer;			
			}
			else if(N==1)
			{
				onecount++;
				Answer[0]=zerocount;
				Answer[1]=onecount;
				return Answer;
			}
			else 
			{
				for(int i=2;i<N+1;i++)
				{
					int next=Fibo.get(i-1)+Fibo.get(i-2);
					Fibo.add(next);
				}
				Answer[0]=Fibo.get(Fibo.size()-2);
				Answer[1]=Fibo.get(Fibo.size()-1);
				
				return Answer;
			}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());	
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<T;i++)
		{
			int N=Integer.parseInt(br.readLine());			
			sb.append(fibo(N)[0]+" "+fibo(N)[1]+"\n");					
		}
		System.out.println(sb);
		br.close();
	}
}
