package week017;

import java.io.*;
import java.util.ArrayDeque;

// 문자열 폭발
public class B_boj_9935_bnuri00 {
	static String str, bomb;
	static int bombLength;
	static ArrayDeque<Character> dq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		bomb = br.readLine();
		bombLength = bomb.length();
		dq = new ArrayDeque<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			dq.add(str.charAt(i));
			
			if(dq.size() >= bombLength) {
				boolean flag = true;
				
				for (int j = bombLength-1; j >= 0; j--) {
					if(dq.peekLast()!= bomb.charAt(j)) {
						rollback(j+1);
						flag = false;
						break;
					}
					dq.pollLast();
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!dq.isEmpty()) sb.append(dq.pollFirst());
		if(sb.length()==0) System.out.print("FRULA");
		else System.out.print(sb.toString());
	}
	static void rollback(int index) {
		for (int i = index; i < bombLength; i++) {
			dq.addLast(bomb.charAt(i));
		}
	}

}
