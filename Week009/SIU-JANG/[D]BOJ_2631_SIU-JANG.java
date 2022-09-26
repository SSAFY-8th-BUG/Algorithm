package boj;

import java.io.IOException;
import java.util.Scanner;

public class BOJ_줄세우기_2631 {
	
	static int N;
	static int[] input, dp;

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        input = new int[N];
        for(int i = 0; i < N; i++) {
        	input[i] = sc.nextInt();
        }

        dp = new int[N];
        dp[0] = 1;

        int ans = 0;
        for(int i = 1; i < N; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(input[i] > input[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(N-ans);

    }
}