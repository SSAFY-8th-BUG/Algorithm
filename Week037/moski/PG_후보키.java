package Week037.moski;

import java.util.*;
public class PG_후보키 {
    static String[][] map;
    static boolean[] visited;
    static HashSet<String> set = new HashSet<>();

    public int solution(String[][] relation) {
        int answer = 0;

        map = relation;
        for(int i=1; i<=relation[0].length; i++){
            visited = new boolean[relation[0].length];
            perm(0,0,i);
        }

        answer = set.size();

        return answer;
    }

    static boolean check(String cols){

        for(String str : set){
            boolean ok = true;
            for(int i=0; i<str.length(); i++){
                if(!cols.contains(Character.toString(str.charAt(i)))){
                    ok = false;
                }
            }

            if(ok){
                return false;
            }
        }

        int[] checkNum = new int[cols.length()];
        int idx = 0;

        for(int i=0;i<visited.length;i++){
            if(visited[i]){
                checkNum[idx] = i;
                idx++;
            }
        }

        HashSet<String> checkSet = new HashSet<>();

        for(int i=0; i<map.length;i++){
            String value = "";
            for(int j=0; j<checkNum.length;j++){
                value += map[i][checkNum[j]];
            }

            if(checkSet.contains(value)){
                return false;
            }else{
                checkSet.add(value);
            }
        }

        return true;
    }

    static void perm(int idx, int cnt, int max){
        if(cnt == max){
            String cols = "";
            for (int i = 0; i < visited.length; i++) {
                if(visited[i]){
                    cols += i;
                }
            }

            if(check(cols)){
                set.add(cols);
            }
            return;
        }

        if(idx >= visited.length) return;

        visited[idx] = true;
        perm(idx + 1, cnt + 1, max);
        visited[idx] = false;
        perm(idx + 1, cnt, max);
    }
}
