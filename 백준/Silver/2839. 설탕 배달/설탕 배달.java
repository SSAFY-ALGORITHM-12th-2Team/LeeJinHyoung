import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int N=sc.nextInt();
		int Five=N/5;
		int Three;
		//Five를 초기 값으로 한다
		for(int i=Five;i>=0;i--)
		{
			int remainder=N-(i*5);
			if(remainder%3==0)
			{
				System.out.println(remainder/3+i);
				System.exit(0);
			}
		}
		System.out.println("-1");
	}

}
