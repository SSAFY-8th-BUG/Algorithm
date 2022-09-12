import java.io.*;
import java.util.*;

// 수 묶기
public class boj_1744_tableMinPark {

    static int N, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        List<Integer> ne = new ArrayList<>();
        List<Integer> po = new ArrayList<>();
        int[] zo = new int[2];

        for (int i = 0; i < N; i++){
            int n = Integer.parseInt(br.readLine());
            if (n < 0) ne.add(n);
            else if (n > 1) po.add(n);
            else zo[n]++;
        }

        Collections.sort(ne);
        Collections.sort(po, Collections.reverseOrder());

        answer = 0;
        
        for (int i = 0; i < ne.size() - 1; i += 2){
            int next = ne.get(i) * ne.get(i + 1);
            answer += next;
        }

        if (ne.size() % 2 == 1 && zo[0] == 0) answer += ne.get(ne.size() - 1);

        answer += zo[1];

        for (int i = 0; i < po.size() - 1; i += 2){
            int next = po.get(i) * po.get(i + 1);
            answer += next;
        }

        if (po.size() % 2 == 1) answer += po.get(po.size() - 1);

        System.out.println(answer);
        br.close();
    }
}
