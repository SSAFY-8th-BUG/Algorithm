package Week017;

import java.io.*;
import java.util.*;

public class bj_2866_tableMinPark {
	
	static int R, C, answer;
	static char[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		for (int r = 0; r < R; r++) arr[r] = br.readLine().toCharArray();
		
		StringBuilder[] strArray = new StringBuilder[C];
		
		for (int c = 0; c < C; c++) {
			strArray[c] = new StringBuilder();
			for (int r = 0; r < R; r++) strArray[c].append(arr[r][c]);
		}
		
		while(true) {
			Set<String> set = new HashSet<>();
			for (int c = 0; c < C; c++) {
				strArray[c].deleteCharAt(0);
				set.add(strArray[c].toString());
			}
			if (set.size() < C) break;
			answer++;
		}
		
		System.out.println(answer);		
		br.close();
	}
}
