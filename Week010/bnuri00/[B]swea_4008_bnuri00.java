package week010;

import java.util.*;
import java.io.*;

/*
 * swea 숫자만들기
 * 
 * 
 * */
public class Bswea_4008_bnuri00 {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	
	static int[] operCard = new int[4];	// 연산자 카드의 수 ( +, -, *, / 순서)
	static int[] numCard;
	
	static int[] used = new int[4];;
	static int[] tgt;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea4008.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 초기화
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			for (int i = 0; i < 4; i++) {
				used[i] = 0;
			}
			
			
			// 입력받기
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 4; i++) {
				operCard[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			
			numCard = new int[N];
			for (int i = 0; i < N; i++) {
				numCard[i] = Integer.parseInt(st.nextToken());
			}
			

			tgt = new int[N-1];
			perm(0);
			
			System.out.println("#"+t+" "+(max-min));
			
		}
	}
	
	static void calcExpression() {
		/*
		 * 문제 조건에서 숫자의 개수가 항상 3 이상 보장
		 * 앞의 숫자 2개 계산 후 3번쨰 숫자부터는 반복문
		 * */
		
		int num = calcOper(tgt[0], numCard[0], numCard[1]);
		
		for (int i = 2; i < numCard.length; i++) {
			num = calcOper(tgt[i-1], num, numCard[i]);
		}
		
		min = Math.min(min, num);
		max = Math.max(max, num);
		
	}
	
	static int calcOper(int oper, int num1, int num2) {
		switch(oper) {
		case 0 : return num1+num2;
		case 1 : return num1-num2;
		case 2 : return num1*num2;
		case 3 : return num1/num2;
		}
		
		// 잘못된 연산자인 경우
		return Integer.MIN_VALUE;
		
	}

	private static void perm(int index) {
		
		// complete code
		if(index == N-1) {
			//System.out.println(Arrays.toString(tgt));
			calcExpression();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(used[i] == operCard[i]) continue;
			
			used[i]++;
			tgt[index] = i;
			perm(index+1);
			used[i]--;
		}

	}

}
