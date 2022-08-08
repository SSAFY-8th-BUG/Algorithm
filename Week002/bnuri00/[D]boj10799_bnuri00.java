package stude_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D_boj10799_bnuri00 {
	static int index = 0;

	static void logic(String input) {
		int n = input.length();
		int answer = 0;
		int size = 0;
		
		for(int i = 0; i < n; i++) {
			char c = input.charAt(i);
			
			
			if(c=='(') {	// 새로운 막대기 시작
				size++;
			}else {	// ')' 인 경우
				size--;
				if(input.charAt(i-1) == '(') {	// 이전 값이 '(', ()인 경우
					answer += size;		// 현재까지 막대기 수 더해주기
				}else answer++;
			}
		}
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();	
		logic(input);

	}

}
