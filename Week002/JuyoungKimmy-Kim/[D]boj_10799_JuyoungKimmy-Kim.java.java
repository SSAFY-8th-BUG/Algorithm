package stack.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10799 {
	
	static int cnt=0;
	static int total_cnt=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		String line=br.readLine();
		

		for (int i=0; i<line.length(); i++) {
			if (line.charAt(i)=='(')
				cnt++;
			else if (line.charAt(i)==')'){
				cnt--;
				
				if (line.charAt(i-1)==')')
					total_cnt++;
				else total_cnt+=cnt;
			}
		}
		System.out.println(total_cnt);
		

	}

}
