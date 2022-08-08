package regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1013 {

	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		
		String regex="(100+1+|01)+";
		
		for (int i=0; i<N; i++) {
			String rg=br.readLine();
			
			if (rg.matches(regex))
				System.out.println("YES");
			else System.out.println("NO");
		}
	}

}
