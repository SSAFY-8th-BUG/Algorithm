import java.io.*;

public class boj_1300_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int l = 1;
        int r = K;

        while(l < r){
            int m = (l + r) / 2;
            int c = 0;

            // m 보다 작거나 같은 수의 개수의 합을 구함
            for (int i = 1; i <= N; i++) c += Math.min(m / i, N);

            if (K <= c) r = m;
            else l = m + 1;
        }

        System.out.println(l);
        br.close();
    }
}