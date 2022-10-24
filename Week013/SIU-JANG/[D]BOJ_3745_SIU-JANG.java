package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_오름세_3745 {
	
	static int N, len;
	static int[] input, memoi;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputNm;
		
		while ((inputNm = br.readLine()) != null) {		
			inputNm = inputNm.trim();
			
			if (inputNm == "" || inputNm.length() == 0) break;
			
			N = Integer.parseInt(inputNm);
			
			input = new int[N];
			memoi = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			len = 0;
			
			for (int i = 0; i < N; i++) {
				int pos = Arrays.binarySearch(memoi, 0, len, input[i]);
				if (pos >= 0) continue;
				pos = Math.abs(pos) - 1;
				memoi[pos] = input[i];
				
				if (len == pos) len++;
			}
			
			System.out.println(len);
		}
	}
}
