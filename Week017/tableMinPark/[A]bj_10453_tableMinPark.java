package Week017;

import java.io.*;
import java.util.*;

// aa__a_a_ -> aaaa____
// 자리이동하는 횟수만 계산하면됨

public class bj_10453_tableMinPark {
	
	static int T, answer;
	static char[] A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			A = st.nextToken().toCharArray();
			B = st.nextToken().toCharArray();
					
			answer = 0;
			
			Queue<Integer> aq = new ArrayDeque<>();
			Queue<Integer> bq = new ArrayDeque<>();
			
			for (int i = 0; i < A.length; i++) {
				if (A[i] == 'a') aq.offer(i);
				if (B[i] == 'a') bq.offer(i);
			}
			
			while(!aq.isEmpty()) answer += Math.abs(aq.poll() - bq.poll());
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
