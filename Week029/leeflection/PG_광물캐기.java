package studygroup.Week029;
import java.util.*;

public class PG_광물캐기 {
        static ArrayList<String> list;
        static boolean visited[];
        static int ans = Integer.MAX_VALUE;
        public int solution(int[] picks, String[] minerals) {
            int answer = 0;
            list = new ArrayList<>();
            for(int i=0; i<picks.length; i++){
                if(i==0){
                    for(int j=0; j<picks[i]; j++){
                        list.add("diamond");
                    }
                }else if(i==1){
                    for(int j=0; j<picks[i]; j++){
                        list.add("iron");
                    }
                }else{
                    for(int j=0; j<picks[i]; j++){
                        list.add("stone");
                    }
                }
            }
            visited = new boolean[list.size()];
            dfs(0,0,minerals,0);


            return ans;
        }
        static void dfs(int depth, int start,String[] minerals, int piro) {
            if (depth == list.size()) {
                ans = Math.min(ans, piro);
                return;
            }
            if (start == minerals.length) {
                ans = Math.min(ans, piro);
                return;
            }
            if (piro >= ans) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    String str = list.get(i);
                    int p = 0;
                    int c = 0;
                    for (int j = start; j < start + 5; j++) {
                        if (j == minerals.length) break;
                        c++;
                        if (str.equals("diamond")) {
                            p += 1;
                        } else if (str.equals("iron")) {
                            if (minerals[j].equals("diamond")) {
                                p += 5;
                            } else {
                                p += 1;
                            }
                        } else {
                            if (minerals[j].equals("diamond")) {
                                p += 25;
                            } else if (minerals[j].equals("iron")) {
                                p += 5;
                            } else {
                                p += 1;
                            }
                        }
                    }
                    dfs(depth + 1, start + c, minerals, piro + p);
                    visited[i] = false;
                }
            }
        }
}
