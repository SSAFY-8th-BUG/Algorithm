import java.util.*;

class Solution {
    static int answer, minusTime, leftLen;
    static long lastK;
    public int solution(int[] food_times, long k) {
        answer = 0;
        
        eatAlmost(food_times, k);
        //System.out.println(minusTime + " "+lastK + " "+leftLen);
        if(answer==-1)return -1;
        
        for(int i=0; i<food_times.length; i++){
            if(minusTime>=food_times[i])continue;
            if(lastK==0){
                answer = i+1;
                return answer;
            }
            lastK--;
            
        }
        
        return answer;
    }
    
    void eatAlmost(int[] food_times, long k){ //먹을수 있는만큼 먹어보고 남은 k구하기
        int[] times = food_times.clone();
        Arrays.sort(times);
        
        int len = food_times.length;
        int prevTime=0;
        long ksum=0L;
        
        for(int i=0; i<food_times.length; i++){
            if((times[i]-prevTime)*len + ksum >k){ //더 못먹음
                if(i!=0) {
                    minusTime = prevTime;
                    leftLen = len;
                }
                lastK = (k-ksum)%((long)len); //나머지 k
                return;
            }
            ksum += (times[i]-prevTime)*((long)len);
            prevTime=times[i];
            len--;
            if(len==0){
                answer = -1;
                return;
            }
            
        }
        answer=-1;
        
    }
    
    
}