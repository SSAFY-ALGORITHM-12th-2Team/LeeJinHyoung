import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int T=Integer.parseInt(br.readLine());
		
		Pattern StarVega= Pattern.compile("^(100+1+|01)+");
		
		for(int i=0;i<T;i++)
		{
			String input=br.readLine();	
			
			if(input.matches(StarVega.pattern())) {
				bw.write("YES\n");
			}
			else {
				bw.write("NO\n");
			}
			
		}
		br.close();
		bw.flush();
		bw.close();
	}
}