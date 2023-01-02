package Week018;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class C_boj_6137_bnuri00 {
	static StringBuilder originStr, resultStr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		originStr = new StringBuilder();
		for (int i = 0; i < N; i++) {
			originStr.append(br.readLine().charAt(0));
		}
		
		resultStr = new StringBuilder();
		while(originStr.length() > 1) {
			char c1 = originStr.charAt(0);
			char c2 = originStr.charAt(originStr.length()-1);
			if(c1==c2) {
				resultStr.append(c1);
				
				int index = findIndexToRemove();
				originStr.deleteCharAt(index);
				
			}else {
				if(c1 < c2) {
					resultStr.append(c1);
					originStr.deleteCharAt(0);
				} else {
					resultStr.append(c2);
					originStr.deleteCharAt(originStr.length()-1);
				}
			}
		}
		resultStr.append(originStr.charAt(0));
		while(resultStr.length() > 80) {
			System.out.println(resultStr.substring(0, 80));
			resultStr.delete(0, 80);
		}
		System.out.print(resultStr.toString());
	}
	/*
	 * 만약 양 끝의 글자가 같을 경우
	 * 그 안쪽 확인해서 다를때까지 확인
	 * 가운데 기준 왼쪽이 사전순 앞서면 0, 반대이면 맨 끝 인덱스 리턴
	 * */
	private static int findIndexToRemove() {
		int len = originStr.length();
		for (int i = 1; i < len / 2; i++) {
			char c1 = originStr.charAt(i);
			char c2 = originStr.charAt(len-i-1);
			
			if(c1 < c2) return 0;
			else if(c1 > c2) return len-1;
		}
		return 0;
	}
}
