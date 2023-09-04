import java.util.*;
public class PG_혼자놀기의달인 {
    static int[] parent;
    static int[] sum;

    public int solution(int[] cards) {
        int answer = 0;
        parent = new int[cards.length + 1];
        sum = new int[cards.length + 1];

        for(int i=0;i<=cards.length;i++){
            parent[i] = i;
        }

        for(int i=0; i<cards.length;i++){
            union(i+1, card[i]);
        }

        for(int i=1;i<parent.length;i++){
            sum[find(i)]++;
        }

        Arrays.sort(sum, Collections.reverseOrder());

        answer = sum[0] * sum[1];
        return answer;
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(x <= y) parent[y] = x;
        else parent[x] = y;
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return find(parent[x]);
    }
}
