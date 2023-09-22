package studygroup.Week040;
import java.util.*;
public class PG_시소짝꿍 {
    public long solution(int[] weights) {
        long ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        Arrays.sort(weights);

        for(int i=0; i<weights.length; i++){
            int num = weights[i];
            if(map.containsKey(num)) ans += map.get(num);
            for(int j=2; j<=4; j++){
                if(num % j == 0){
                    int mok = num/j;
                    if(map.containsKey(mok * (j-1))){
                        ans += map.get(mok * (j-1));
                    }
                }
            }
            map.put(num,map.getOrDefault(num,0)+1);
        }
        return ans;
    }
}
