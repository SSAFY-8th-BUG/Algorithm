package studygroup.Week032;
import java.util.*;
import java.util.regex.Pattern;

public class PG_불량사용자 {
    static int N;
    static int M;
    static int count;
    static HashSet<String> ans;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        N = banned_id.length;
        M = user_id.length;
        for(int i=0; i<banned_id.length; i++){
            ArrayList<Integer> tmpList = new ArrayList<>();
            String target = banned_id[i];
            for(int j=0; j<user_id.length; j++){
                String id = user_id[j];
                if(isResemble(id , target)){
                    tmpList.add(j);
                }
            }
            list.add(tmpList);
        }
        ans = new HashSet<>();
        dfs(0,new HashSet<Integer>());

        return ans.size();
    }
    public static boolean isResemble(String id, String target){
        String pattern = target.replace('*', '.');
        return Pattern.matches(pattern,id);
    }
    public static void dfs(int depth, HashSet<Integer> set){
        if(depth == N){
            String a = "";
            for(int i : set){
                a += String.valueOf(i);
            }
            ans.add(a);
            return;
        }
        for(int i : list.get(depth)){
            if(set.contains(i)) continue;
            set.add(i);
            dfs(depth+1, set);
            set.remove(i);
        }
    }
}
