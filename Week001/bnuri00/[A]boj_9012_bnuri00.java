package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 시간복잡도: O(n)
 * 공간복잡도: O(1) */
public class boj9012 {
	static String no = "NO";
	static String yes = "YES";
	
	public static String logic(String input) {
		int size = 0;
		
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == ')') {	//닫는 괄호
				if(size == 0) return no;
				size--;
			} else {	//여는 괄호
				size++;
			}
		}

		return size==0 ? yes : no ;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 0; testCase < T; testCase++) {
			String input = br.readLine();
			System.out.println(logic(input));	
		}

	}

}
