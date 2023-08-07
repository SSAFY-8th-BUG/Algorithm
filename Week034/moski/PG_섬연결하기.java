package Week034.moski;

import java.util.*;
public class PG_섬연결하기 {
    static int N;
    static int[] parent;

    public int solution(int n, int[][] costs) {

        int answer = 0;
        N = n;

        parent = new int[N];

        for(int i=0; i<N;i++){
            parent[i] = i;
        }

        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < costs.length; i++) {
            if (find(costs[i][0]) != find(costs[i][1])) {
                answer += costs[i][2];
                union(costs[i][0], costs[i][1]);
            }
        }



        return answer;
    }

    static void union(int a, int b){
        int ap = find(a);
        int bp = find(b);

        if(ap < bp) parent[bp] = ap;
        else parent[ap] = bp;
    }

    static int find(int i){
        if(parent[i] == i) return i;
        else return find(parent[i]);
    }
}
