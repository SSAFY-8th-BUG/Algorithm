package Week020.bnuri00;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 보고함 어려우
 * 참고: 블로그 코드
 * */
public class C_boj_12025_bnuri00 {
	static long k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] arr = br.readLine().toCharArray();
		int[] num = new int[arr.length];
		
		int cnt = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] == '1' || arr[i] == '2' || arr[i] == '6' || arr[i] == '7') {
				// 위치 및 개수 저장
				num[cnt++] = i;
				
				// 사전순 맨 앞 숫자 만들기
				if (arr[i] == '6') // 6 -> 1
					arr[i] = '1';
				else if (arr[i] == '7') // 7 -> 2
					arr[i] = '2';
			}
		}
		
		k = Long.parseLong(br.readLine());	// (k ≤ 2^63 – 1)
		k--;
		long max = (long) Math.pow(2, cnt);

		cnt = 0;
		while (k > 0) {
			// K를 2로 나누면서 나머지가 1이면 해당자리의 숫자를 바꿈
			if(k%2==1) {
				if (arr[num[cnt]] == '1') arr[num[cnt]] = '6';
				else if (arr[num[cnt]] == '2') arr[num[cnt]] = '7';
			}
			k/=2;
 			cnt++;
		}
		
		for(char c : arr)
			sb.append(c);
		
		System.out.println(sb);
	}

}