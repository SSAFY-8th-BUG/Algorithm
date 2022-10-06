package dynamicprogramming;

import java.util.Scanner;

/*
 * 가장 긴 증가하는 부분 수열의 길이 (LIS)를 구한다
 * 수열 arr[] 이 있을 때
 * arr[i]를 기준으로 arr[i] 앞에 있는 숫자들 (j=0~i-1)을 비교해본다
 * 
 * arr[i]>arr[j] 이고, dp[i] < dp[j]+1 ==> 현재 LIS길이가 이전 길이에서 1을 더한 것보다 작다면
 * 현재 LIS 갱신
 * 
 * LIS가 구해지면 N개에서 최대 LIS를 빼주면 된다
 * 
 * 
 */
public class BOJ2631 {

	static int N, max;
	static int[] arr, dp;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		arr=new int[N+1];
		dp=new int[N+1];
		
		for (int i=1; i<=N; i++) 
			arr[i]=sc.nextInt();
		
		for (int i=1; i<=N; i++) {
			dp[i]=1;
			
			for (int j=1; j<=i; j++) {
				if (arr[j]<arr[i] && dp[i]<dp[j]+1) {
					dp[i]=dp[j]+1;
				}
			}
			max=Math.max(max, dp[i]);
		}
		
		
		System.out.println(N-max);
	}
}
