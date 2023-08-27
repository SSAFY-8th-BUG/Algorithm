import java.util.*;

class Solution {
    static Set<Integer>answers;
    static int N;
    public int solution(int[] a) {
        int answer = 0;
        answers = new HashSet<>();
        N = a.length;
        
        bomb(a);
        answer = answers.size();
        return answer;
    }
    
    static void bomb(int[] a){
        int min=a[0];
        answers.add(min);
        for(int i=0; i<N; i++){
            if(min>a[i]){
                min=a[i];
                answers.add(min);
            }
        }
        
        min=a[N-1];
        answers.add(min);
        for(int i=N-1; i>=0; i--){
            if(min>a[i]){
                min=a[i];
                answers.add(min);
            }
        }
        
    }
}