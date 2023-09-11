package studygroup.Week039;
import java.util.*;
public class PG_다단계칫솔판매 {
    static HashMap<String, String> map;
    static HashMap<String, Integer> cost;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        map = new HashMap<>();
        cost = new HashMap<>();
        for(int i=0; i<enroll.length; i++){
            String name = enroll[i];
            String ref = referral[i];
            map.put(name, ref);
            cost.put(name,0);
        }

        for(int i=0; i<seller.length; i++){
            String name = seller[i];
            int c = amount[i] * 100;

            dfs(name,c);
        }
        int[] ans = new int[enroll.length];
        for(int i=0; i<enroll.length; i++){
            ans[i] = cost.get(enroll[i]);
        }

        return ans;
    }
    static void dfs(String name, int amt){
        if(amt == 0){
            return;
        }
        if(name.equals("-")){
            return;
        }else{
            int per = (int)(amt*0.1);
            cost.put(name, cost.get(name) + (amt - per));

            String next = map.get(name);
            dfs(next, per);
        }
    }
}
