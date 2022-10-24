package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_LCS_9251 {
	public static void main(String[] args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		int[][] array = new int[s1.length() + 1][s2.length() + 1];
		
		for (int i = 1; i < s1.length() + 1; i++) {
			for (int j = 1; j < s2.length() + 1; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					array[i][j] = array[i - 1][j - 1] + 1;
				} else {
					array[i][j] = Math.max(array[i - 1][j], array[i][j - 1]);
				}
			}
		}
		
		System.out.println(array[array.length - 1][array[0].length - 1]);
	}
}
