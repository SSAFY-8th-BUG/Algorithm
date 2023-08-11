import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int maxi=A.length-1;
        int mini=0;
        for(int i=A.length-1; i>=0; i--){
            //System.out.println(A[i] + " "+B[maxi]);
            if(A[i]>=B[maxi]) {
                mini++;
            }else{
                maxi--;
                answer++;
            }
        }
        return answer;
    }
}