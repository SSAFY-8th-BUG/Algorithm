import java.util.*;

class Solution {
    static List<int[]> ans; 
    public int[][] solution(int n) {
        ans = new ArrayList<>();
        
        
        
        hanoi(n, 1, 3, 2);
        int[][] answer = new int[ans.size()][2];
        for(int i=0; i<ans.size(); i++){
            answer[i][0]=ans.get(i)[0];
            answer[i][1]=ans.get(i)[1];
        }
        
        return answer;
    }
    
    private void hanoi(int n, int from, int to, int via){
        int[] move = {from, to};
        
        if(n == 1) {
            ans.add(move);
        } else {
            hanoi(n - 1, from, via, to);
            ans.add(move);
            hanoi(n - 1, via, to, from);   
        }
    }
}