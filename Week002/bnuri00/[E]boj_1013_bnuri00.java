package stude_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class E_boj_1013_bnuri00 {
	
	static ArrayDeque<Character> stack = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String vega = "(100+1+|01)+";
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			String wave = br.readLine();
			System.out.println(wave.matches(vega)? "YES" : "NO");
		}

	}
}
