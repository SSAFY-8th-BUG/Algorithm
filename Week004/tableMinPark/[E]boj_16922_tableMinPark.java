import java.io.*;

// 시간복잡도 -> O(2^n)
// 공간복잡도 -> O(2^n)
public class boj_16922_tableMinPark{

    static int N, answer;
    static int[] src;
    static int[] tgt;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        src = new int[]{1, 5, 10, 50};
        tgt = new int[N];
        v = new boolean[1001];

        answer = 0;
        comb(0, 0);

        System.out.println(answer);
        br.close();
    }

    static void comb(int tgtIdx, int srcIdx){
        if (tgtIdx == N){
            int sum = 0;
            for (int t : tgt) sum += t;
            if (!v[sum]){
                v[sum] = true;
                answer++;
            }
            return;
        }

        for (int i = srcIdx; i < src.length; i++){
            tgt[tgtIdx] = src[i];
            comb(tgtIdx + 1, i);
        }
    }
    
}