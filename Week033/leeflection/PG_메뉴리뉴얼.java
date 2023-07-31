package studygroup.Week032;
import java.util.*;
public class PG_메뉴리뉴얼 {

    public Map<String,Integer> map;
    public int max = 0;

    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> ans = new ArrayList<>();
        for(int c : course){
            map = new HashMap<>();
            max = 0;

            for(String order: orders){
                char[] strs = order.toCharArray();
                Arrays.sort(strs);
                order = String.valueOf(strs);
                checkOrder(0,0,order,"",c);
            }

            for(String key : map.keySet()){
                int value = map.get(key);
                if(value > 1 && max == value){
                    ans.add(key);
                }
            }
        }

        Collections.sort(ans);
        String[] answer = ans.toArray(new String[ans.size()]);

        return answer;
    }

    public void checkOrder(int depth,int idx, String order,String cur, int end){
        if(depth == end){
            map.put(cur,map.getOrDefault(cur,0)+1);
            max = Math.max(max,map.get(cur));
            return;
        }

        for(int i = idx; i < order.length(); i++){
            checkOrder(depth+1,i+1,order,cur + order.charAt(i),end);
        }
    }
}
