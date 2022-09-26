package boj;

import java.util.Scanner;
 
public class BOJ_K번째수_1300 {
 
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
 
		int N = sc.nextInt();
		int K = sc.nextInt();
 
		long lo = 1;
		long hi = K;
		
		while(lo < hi) {
			
			long mid = (lo + hi) / 2;
			long count = 0;
			
			for(int i = 1; i <= N; i++) {
				count += Math.min(mid / i, N);
			}
			
			if(K <= count) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		
		System.out.println(lo);
	}
}