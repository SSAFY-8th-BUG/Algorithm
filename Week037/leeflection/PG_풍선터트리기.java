package studygroup.Week032;
import java.util.*;
public class PG_풍선터트리기 {
    static HashSet<Integer> set;
    public int solution(int[] a) {
        int answer = 0;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        HashSet<Integer> hs = new HashSet<>();
        for(int i=0;i<a.length;i++){
            min1=Math.min(min1,a[i]);
            min2=Math.min(min2,a[a.length-1-i]);
            hs.add(min1);
            hs.add(min2);

        }
        return hs.size();
    }
}
