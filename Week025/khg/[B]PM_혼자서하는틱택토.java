import java.util.*;

class Solution {
    static long[] dp1, dp2;
    public long solution(int[] sequence) {
        long answer = -999999L;
        int len = sequence.length;
        dp1 = new long[len+1];
        dp2 = new long[len+1];
        
        
        for(int i=0; i<len; i++){
            long num1, num2;
            if(i%2==0){
                num1 = sequence[i];
                num2 = -sequence[i];
                
            }else{
                num1 = -sequence[i];
                num2 = sequence[i];
            }
            
            if(i==0) {
                dp1[i]=num1;
                dp2[i]=num2;
                answer = Math.max(answer, dp1[i]);
                answer = Math.max(answer, dp2[i]);
            }
            else{
                dp1[i] = Math.max(num1, num1 + dp1[i-1]);
                dp2[i] = Math.max(num2, num2 + dp2[i-1]);
                answer = Math.max(answer, dp1[i]);
                answer = Math.max(answer, dp2[i]);
            }
        }
        //System.out.println(Arrays.toString(dp2));
        return answer;
    }
}