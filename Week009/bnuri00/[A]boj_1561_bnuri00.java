package week009;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 진짜 진짜 다시풀어봐야할 것
 * */
public class Aboj_1561_bnuri00 {
	static long N;
	static int M;
	static int[] time;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		time = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N<=M) {
			System.out.println(N);
			return;
		}
		
		long left = 0;
		long right = N*30;
		while(left <= right) {
			long mid = (left+right)/2;
			long childNum = getChildNum(mid);
			
			if(childNum < N) {
				left = mid+1;
			} else {
				right = mid-1;
			}
		}
		
		long result = left;
		
		long child = getChildNum(result-1);
		
		for (int i = 0; i < M; i++) {
			if(result%time[i] == 0) {
				child++;
			}
			if(child == N) {
				System.out.println(i+1);
				return;
			}
		}
		
	}
	static long getChildNum(long t) {
		long result = M;
		for (int i = 0; i < M; i++) {
			result += t/time[i];
		}
		return result;
	}

}
