import java.io.*;

// DP 알고리즘에 대해 설명 잘되있는 블로그 거의 뭐 사전 (냅색, LCS, LIS ...)
// https://os94.tistory.com/95

// 딱 LCS 문제
/*

LCS VS LIS

LCS 
* 두 개의 배열 중에서 공통되고 가장 긴 부분수열을 구함
* 0 dummy 
* 같으면 대각선의 값 + 1, 다르면 왼쪽의  값 or 위의 값 중 큰 수를 저장

LIS
* 하나의 배열에서 가장 긴 부분수열을 구함
* 0 dummy
* 이진탐색을 이용한 방법 or 브루트포스를 이용한 방법

 */
public class boj_9251_tableMinPark {
    
    static char[] src, part;
    static int[][] dp;
    static int ss, ps;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        src = br.readLine().toCharArray();
        part = br.readLine().toCharArray();

        ss = src.length;
        ps = part.length;

        dp = new int[ss + 1][ps + 1];   // 0 dummy
        
        for (int i = 1; i <= ss; i++){
            for (int j = 1; j <= ps; j++){
                // 같으면 대각선 + 1
                if (src[i - 1] == part[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                // 다르면 위 - 왼 중 큰 수를 넣음
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        System.out.println(dp[ss][ps]);
        br.close();        
    }
}
