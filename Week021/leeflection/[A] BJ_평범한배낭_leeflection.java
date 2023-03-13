// 평범한배낭
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static int[] w;
    static int[] v;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        v = new int[N+1];
        w = new int[N+1];
        int[][] map = new int[N+1][K+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            w[i]= Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=N; i++){
            for(int j=1; j<=K; j++){
                if(w[i]<=j){
                    map[i][j] = Math.max(
                            map[i-1][j],
                            map[i-1][j-w[i]]+v[i]
                    );
                }else{
                    map[i][j] = map[i-1][j];
                }
            }
        }
        System.out.println(map[N][K]);
    }
}