package stude_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class C_boj_2800_bnuri00 {
	static ArrayDeque<Integer> stack = new ArrayDeque<>();
	static ArrayList<int[]> brackets = new ArrayList<>();
	static HashSet<String> result = new HashSet<>();	// 정답 set
	static int N = 0;	// 괄호 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		// brackets에 괄호 인덱스쌍 저장
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(') {
				N++;
				stack.push(i);
			}
			else if(input.charAt(i) == ')') {
				brackets.add(new int[] {stack.pop(), i});
			}
		}
		char[] inputArr = input.toCharArray();
		char[] inputArr2 = inputArr.clone();
		
		rec(N, inputArr, true);
		rec(N, inputArr2, false);
		
		// 정렬해서 차례대로 출력
		result.stream().sorted().forEach(System.out::println);

	}
	
	static void rec(int n, char[] input, boolean remove) {
		if(n == 0) {
			return;
		}

		if(remove) {
			int[] tmp = brackets.get(n-1); // n-1번째 괄호
			int repeat = 0;
			
			// input 배열 돌면서 n-1번째 괄호를 공백으로 대체
			for(int i = 0; i < input.length; i++) {
				if(repeat==2) break;
				if(i == tmp[0] || i == tmp[1]) {
					input[i] = ' ';
					repeat++;
				}
			}
			// 공백 제외하고 sb에 추가
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < input.length; i++) {
				if(input[i] != ' ')
					sb.append(input[i]);
			}
			// result set에 저장
			result.add(sb.toString());

		}
		// remove가 false인 경우에는 이전 rec()와 같은 상태
		// (괄호를 삭제하지 않았으므로)
		// -> 아무것도 하지 않고 다음 rec()를 부른다

		char[] input2 = input.clone();
		
		rec(n-1,input, true);
		rec(n-1,input2, false);
	}
}
