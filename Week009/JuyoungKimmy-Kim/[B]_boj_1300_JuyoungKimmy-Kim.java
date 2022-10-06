package binarySearch;

import java.util.Scanner;

public class BOJ1300 {
	
	static int N,K, ans;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		K=sc.nextInt();
		
		// N-> 최대 10^5
		// K-> 최대 10^2
		// B[K]-> 최대 10^10=1000_000_000
		
		int low=1, high=K;
		
		while (low<high) {
			int mid=(low+high)/2;
			int cnt=0;		
			/*
			 * B[K]에 있는 수가 6일 때,
			 * 1x1=1, 1x2=2, 1x3=3, 1x4=4, 1x5=5, 1x6=6 ==> 6개
			 * 2x1=2, 2x2=2, 2x3=6 						==> 3개
			 * 3x1=3, 3x2=6								==> 2개
			 * 4x1=4									==> 1개
			 * 5x1										==> 1개
			 * 6x1										==> 1개
			 * 
			 *  14번 째 숫자임을 알 수 있다 (즉 K=14)
			 * 
			 */
			
			for (int i=1; i<=N; i++) {
				/*
				 *  N=4인데, B[K]=5라고 가정했을 경우
				 *  1X1=1, 1x2=2, 1x3=3, 1x4=4 까지만 가능 (N개까지!)
				 */
				cnt+=Math.min(mid/i, N);
			}
			
			if (K<=cnt) {
				high=mid;
			} else {
				low=mid+1;
			}
			
		}
		System.out.println(low);
		
	}
}
