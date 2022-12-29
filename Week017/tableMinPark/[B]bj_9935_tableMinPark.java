package Week017;

import java.io.*;
import java.util.*;

public class bj_9935_tableMinPark {
	
	static char[] str, sp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine().toCharArray();
		sp = br.readLine().toCharArray();
		
		Stack<Character> stack = new Stack<>();
		
		for (char c : str) {
			// 폭발문자열의 마지막 문자이면
			stack.add(c);
			if (stack.peek() == sp[sp.length - 1]) {
				// 하나씩 꺼내면서 폭발문자열이랑 비교				
				Stack<Character> temp = new Stack<>();		
				for (int i = sp.length - 1; i >= 0; i--) {
					if (stack.isEmpty()) break;
					// 같으면 꺼내서 temp에 넣음
					if (stack.peek() == sp[i]) {
						temp.add(stack.pop());
					} 
					// 다르면 폭발문자열이 아니므로 중지
					else break;
					// 마지막까지 왔으면
					if (i == 0) temp.clear();
				}				
				while(!temp.isEmpty()) stack.add(temp.pop());				
			}
		}
		
		StringBuffer sb = new StringBuffer();
		while(!stack.isEmpty()) sb.append(stack.pop());
		sb = sb.reverse();
		
		System.out.println(sb.toString().equals("") ? "FRULA" : sb.toString());		
		br.close();
	}
}
