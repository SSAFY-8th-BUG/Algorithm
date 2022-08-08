package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9997 {
	
	static int N;
	static String[] str;
	static int[] checked=new int[26];
	static int cnt,ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		str=new String[N];
		
		for (int i=0; i<N; i++) 
			str[i]=br.readLine();
		
		makeSentence (0);
		System.out.println(ret);

	}
	
	private static void makeSentence (int idx) {
		if (cnt==26) {
			ret+=1 << (N-idx);
			return ;
		}
		if (idx==N) return;
		
		for (int i=0; i<str[idx].length(); i++) {
			
			char c=str[idx].charAt(i);
			add(c);

		}
		
		makeSentence (idx+1);
		
		for (int i=0; i<str[idx].length(); i++) {
			char c=str[idx].charAt(i);
			remove(c);

		}
		makeSentence (idx+1);
		
	}
	
	private static void add (char c) {
		if (++checked[c-'a']==1) cnt++;
	}
	private static void remove (char c) {
		if (--checked[c-'a']==0) cnt--;
	}
	
}
