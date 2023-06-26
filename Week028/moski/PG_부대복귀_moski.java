package Week028.moski;

import java.util.*;
public class PG_부대복귀_moski {
    static List<Integer>[] map;
    static int[] dis;
    static int N;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        N = n;
        map = new List[n+1];
        dis = new int[n+1];

        for(int i=0; i<=n;i++){
            dis[i] = 1000000;
            map[i] = new ArrayList<>();
        }

        for(int i=0; i<roads.length;i++){
            map[roads[i][0]].add(roads[i][1]);
            map[roads[i][1]].add(roads[i][0]);
        }

        dijkstra(destination);

        for(int i=0;i<sources.length;i++){
            if(dis[sources[i]] == 1000000){
                answer[i] = -1;
            }else{
                answer[i] = dis[sources[i]];
            }
        }
        return answer;
    }

    static void dijkstra(int des){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(des);
        dis[des] = 0;

        while(!q.isEmpty()){
            int n = q.poll();

            for(int i=0; i<map[n].size(); i++){
                int d = map[n].get(i);
                if(dis[d] > dis[n] + 1){
                    dis[d] = dis[n] + 1;
                    q.add(d);
                }
            }
        }
    }
}
