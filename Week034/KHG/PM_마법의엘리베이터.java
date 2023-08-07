import java.util.*;

class Solution {
    static int answer;
    public int solution(int storey) {
        answer = storey;
        
        rec(storey, 1, 0);
        return answer;
    }
    
    static void rec(int storey, int m, int sum){
        if(m>storey){
            answer = Math.min(answer,sum);
            return;
        }
        int num=(storey%(m*10))/m;
        if(num>5){
            rec(storey+m*10, m*10, sum+10-num);
        }else if(num<5){
            rec(storey, m*10, sum+num);
        }else{
            rec(storey, m*10, sum+num);
            rec(storey+m*10, m*10, sum+10-num);
        }
    }
    
}