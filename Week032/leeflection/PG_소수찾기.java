package studygroup.Week031;
import java.util.*;
public class PG_소수찾기 {
    static char[] arr;
    static int ans;
    static boolean[] visited;
    static HashSet<Integer> set;
    public int solution(String numbers) {
        int answer = 0;
        arr = numbers.toCharArray();
        set = new HashSet<>();
        visited= new boolean[arr.length];
        dfs(0,"");
        ArrayList<Integer> list = new ArrayList<>(set);
        for(int i : list){
            if(i==0 || i==1) continue;
            if(i==2){
                ans++;
                continue;
            }
            boolean ch = true;
            for(int j=2; j<=(int)Math.sqrt(i); j++){
                if(i%j==0){
                    ch = false;
                    break;
                }
            }
            if(ch) ans++;
        }
        return ans;
    }
    public static void dfs(int depth,String s){
        if(s.length() > 0){
            int n = Integer.parseInt(s);
            set.add(n);
        }
        if(depth == arr.length){
            return;
        }
        for(int i=0; i<arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(depth+1,s+arr[i]);
                visited[i] = false;
            }
        }

    }
}
