package week004;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 풀이 시간: 4시간
 * 참고: 부분집합(교수님 코드ㅠ), boj 질문-> 반례 (수식길이 3인 경우)
 * 
 * 14224 KB		128 ms
 * 
 * <풀이방식>
 * - 연산자는 index 배열에 홀수번째로 있음
 * - 수식 길이 1 or 3 인 경우, 수식 마지막에 *0이 있는 경우 검사할 필요 없이 값을 구할 수 있어 처리 후 종료함
 * 
 * - 일반적인 경우 (수식 길이가 5 이상인 경우)
 * - subset 시작 파라미터: 현재 연산자 -> 두번째 연산자(input[3]), 이전까지 연산된 값 -> 맨 처음 숫자(input[0]-'0')
 * - 현재 연산자가 괄호에 묶이지 않을 경우 현재 연산자는 연산에 활용x, 이전까지 연산된 값과 현재 연산자 앞쪽을 연산
 * 		ex. 1+2*3 에서 현재연산자 *, 괄호x 이면 -> 이전까지 연산된 값에 +2함, 1+2!!!  
 * - 현재 연산자가 괄호에 묶일 경우 괄호부분 연산 후 이전까지 연산된 값과 연산 (연산 두번!)
 * 
 * 
 * <삽질 목록>
 * - char 형으로 받은 input에서 숫자 연산할 때 -'0' 해주는거 까먹은 곳 있엇ㅅ음
 * - 괄호 있는 경우 subset(srcIdx+4, calc1) 이전에서 lastSelect를 무조건 true로 바꿔줘서 
 *		마지막 연산자 이전 연산자가 선택(N-4번째) ->  lastSelect가  true인 상태로 N이 되어 마지막 수가 연산되지 않음 (if 조건 추가해주었다)
 * - 그리디로 풀려고 함 (시간낭비ㅜ)
 * 
 * */
public class A_boj_괄호추가 {
	static int N, result = Integer.MIN_VALUE;
	static char[] input;	// 입력받은 전체 수식 저장, 연산자 위치는 홀수번째 index
	
	static boolean lastSelect = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = br.readLine().toCharArray();
		
		if(N == 1) {	// 수식 길이 1인 경우 계산x
			System.out.println(input[0]);
			return;
		}
		else if(N == 3) {	// 수식 길이 3인 경우 묶든말든 똑같음
			System.out.println(calcOper(input[1], input[0]-'0', input[2]-'0'));
			return;
		}
		else if(input[N-2]=='*'&&input[N-1]=='0') {	// 수식 마지막에 *0이 있는 경우 (무조건 답이 0이 된다!)
			System.out.println(0);
			return;
		}
		
		// 일반적인 경우
		subset(3, input[0]-'0');

		System.out.println(result);
	
	}
	
	static void subset(int srcIdx, int calcResult) {
		// 기저조건
		if(srcIdx >= N) {
			if(!lastSelect) calcResult = calcOper(input[N-2], calcResult, input[N-1]-'0');
			result = Math.max(result, calcResult);
			return;
		}
		
		// srcIdx의 연산자 중심으로 괄호에 묶임
		int calc1 = calcSelect(srcIdx, calcResult);
		if(srcIdx==N-2) // 마지막 연산자가 선택된 경우 체크
			lastSelect = true;
		subset(srcIdx+4, calc1);	// 바로 다음 선택불가 
		
		// 괄호 x
		int calc2 = calcNotSelect(srcIdx, calcResult);
		if(srcIdx==N-2) // 마지막 연산자가 선택되지 않은 경우 체크
			lastSelect = false;	
		subset(srcIdx+2, calc2);
		
	}
	// 현재 연산자 선택된 경우 연산
	static int calcSelect(int operIdx, int calcResult) {
		return calcOper(input[operIdx-2],calcResult, 
				calcOper(input[operIdx],input[operIdx-1]-'0',input[operIdx+1]-'0'));
	}
	
	// 현재 연산자 선택되지 않은 경우 연산
	static int calcNotSelect(int operIdx, int calcResult) {
		return calcOper(input[operIdx-2],calcResult,input[operIdx-1]-'0');
	}
	
	// char 자료형의 연산자, 숫자 2개 받아 계산함
	static int calcOper(char oper, int n1, int n2) {
		int num = 0;
		switch(oper) {
		case '+': num = n1+n2; break;
		case '-': num = n1-n2; break;
		case '*': num = n1*n2; break;
		}
		return num;
	}

}
