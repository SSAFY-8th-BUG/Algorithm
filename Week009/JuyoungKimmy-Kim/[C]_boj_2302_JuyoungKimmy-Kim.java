package dynamicprogramming;

import java.util.Scanner;

/*
 * VIP 석을 제외하고 각 경우의 수
 * N=1 (1)
 * N=2 (1,2) (2,1)
 * N=3 (1,2,3) (2,1,3) (1,3,2)
 * N=4 (1,2,3,4) (2,1,3,4) (1,3,2,4) (1,2,4,3) (2,1,4,3)
 * 
 * dp[N]=dp[N-2] + dp[N-1];
 * 
 * 가운데 VIP석이 추가 되었을 때, 그 점을 기준으로 반으로 나누어서
 * 자리가 N개 있을 때 앉을 수 있는 자리의 수를 구한다
 * 
 */
public class BOJ2302 {

	static int N,M, ans=1;
	static int[] dp=new int[41];
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		
		// #1. 먼저 자리가 N개일 때 앉을 수 있는 경우의 수를 dp에 모두 저장
		dp[0]=1; dp[1]=1; dp[2]=2;
		for (int i=3; i<=N; i++) {
			dp[i]=dp[i-1]+dp[i-2];
		}
		
		int vip=0;
		int start=0;
		for (int i=0; i<M; i++) {
			vip=sc.nextInt();
			
			// #2. vip 위치를 입력 받으면 (vip-start-1)은 vip를 기준으로 앞 자리에 있는 사람들의 수
			ans*=dp[vip-start-1];
			
			// #3. 시작 지점을 현재 vip 위치로 바꾸어 줌
			start=vip;
		}
		
		// #4. vip 입력을 다 받고 마지막에 남은 자리 수가 N-vip개 일 때 횟수도 곱해줌
		ans*=dp[N-vip];
		System.out.println(ans);
	}

}
