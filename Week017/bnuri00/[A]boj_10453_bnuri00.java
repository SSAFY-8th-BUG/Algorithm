package week017;

import java.io.*;
import java.util.StringTokenizer;

public class A_boj_bnuri00  {
	static int result = 0;
	static String startStr, endStr;
	static int strLen;
	static char[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			startStr = st.nextToken();
			endStr = st.nextToken();		
			strLen = startStr.length();
			arr = startStr.toCharArray();
			
			System.out.println(logic()? result: -1);
		}
	}
	private static boolean logic() {
		for (int i = 0; i < strLen; i++) {
			if(arr[i] != endStr.charAt(i)) {
				// arr[i]와 다른것 중(arr[i]=='a'이면 'b' 찾기 
				// 현재 index보다 뒤에 있는 것 찾음
				int idx = findIndex(i+1, arr[i]);	
				
				if(idx==-1) return false;
				result += idx-i;
				
				// swap
				char c = arr[i];
				arr[i] = arr[idx];
				arr[idx] = c;
			}
		}
		return true;
	}
	private static int findIndex(int startIdx, char c) {
		for (int i = startIdx; i < strLen; i++) 
			if(arr[i] != c) return i;

		return -1;
	}

}
