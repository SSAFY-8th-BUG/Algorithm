import java.util.*;

class Solution {
    static int N,M;
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
        N = data.length;
        M = data[0].length;
        Arrays.sort(data, (o1,o2)->o1[col-1]==o2[col-1]?o2[0]-o1[0]:o1[col-1]-o2[col-1]);
        // for(int i =0; i<N; i++){
        //     System.out.println(Arrays.toString(data[i]));
        // }
        
        int answer = 0;
        for(int i = row_begin; i<=row_end; i++){
            int[] row = data[i-1];
            int num=0;
            for(int j=0; j<M; j++){
                num+=(row[j]%i);
            }
            if(i==row_begin) answer=num;
            else answer = answer ^ num;
        }
        
        
        return answer;
    }
}