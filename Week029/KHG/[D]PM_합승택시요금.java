import java.util.*;

class Solution {
    static int[][] mat;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 999999999;
        mat = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i==j)continue;
                mat[i][j]=9999999;
            }
        }
        for(int[] info : fares){
            int n1 = info[0];
            int n2 = info[1];
            int w = info[2];
            mat[n1][n2]=w;
            mat[n2][n1]=w;
        }
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
        for(int i=1; i<=n; i++){
            answer = Math.min(answer , mat[s][i]+mat[a][i]+mat[b][i]);
        }
        return answer;
    }
}