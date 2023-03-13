import java.io.*;

public class BJ_LCS_tableMinPark {
    static char[] s, p;
    static int[][] dp;
    static int ss, ps;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine().toCharArray();
        p = br.readLine().toCharArray();

        ss = s.length;
        ps = p.length;

        dp = new int[ss + 1][ps + 1];

        for (int i = 1; i <= ss; i++){
            for (int j = 1; j <= ps; j++){
                if (s[i - 1] == p[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        System.out.println(dp[ss][ps]);
        br.close();
    }
}