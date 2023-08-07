package studygroup.Week032;
import java.util.*;
public class PG_섬연결하기 {
        static class Edge{
            int start;
            int end;
            int val;
            public Edge(int start, int end, int val){
                this.start =start;
                this.end = end;
                this.val = val;
            }
        }
        static int[] parent;
        public int solution(int n, int[][] costs) {
            ArrayList<Edge> list = new ArrayList<>();
            for(int i=0; i<costs.length; i++){
                list.add(new Edge(costs[i][0],costs[i][1],costs[i][2]));
            }
            Collections.sort(list,(o1,o2)->{
                return o1.val - o2.val;
            });
            int ans = 0;
            parent = new int[n+1];
            for(int i=1; i<=n; i++){
                parent[i] = i;
            }

            for(Edge e : list){
                if(union(e.start, e.end)){
                    ans += e.val;
                }
            }

            return ans;
        }
        public static int find(int i){
            if(parent[i] == i) return i;
            else return parent[i] = find(parent[i]);
        }
        public static boolean union(int x, int y){
            int fx = find(x);
            int fy = find(y);

            if(fx!=fy){
                if(fx > fy){
                    parent[fx] = fy;
                }else{
                    parent[fy] = fx;
                }
                return true;
            }else{
                return false;
            }
        }
    }
