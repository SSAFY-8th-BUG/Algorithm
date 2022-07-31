package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/* 
 * 다시 정리하기!
 * 
 * 시간복잡도 O(n^2)
 * 공간복잡도 O(n)
 */
public class boj17298 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		Stack<Integer> stack = new Stack<>();
		
		//배열 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			while(!stack.empty() && arr[stack.peek()] < arr[i]) {
				arr[stack.pop()] = arr[i];
			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			arr[stack.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		
		
		for(int i = 0; i < N; i++) {
			sb.append(arr[i] + " ");
		}
		System.out.println(sb.toString());
		
	}

}
