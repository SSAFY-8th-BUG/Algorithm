import java.util.*;

class Solution {
    static List<Integer> graph;
    static int len;
    static double[] dp, answer;
    public double[] solution(int k, int[][] ranges) {
        
        getGraph(k);
        getDP();
        getAnswer(ranges);
        
        
        return answer;
    }
    
    void getGraph(int k){
        graph = new ArrayList<>();
        graph.add(k);
        while(k!=1){
            if(k%2==0){
                k/=2;
            }
            else{
                k=k*3+2-1;
            }
            graph.add(k);
        }
        
        len = graph.size();
    }
    void getDP(){
        dp = new double[len];
        for(int i=1; i<len; i++){
            int n1 = graph.get(i-1);
            int n2 = graph.get(i);
            double num = (n1+n2)/2.0;
            dp[i]= dp[i-1]+num;
        }
    }
    
    void getAnswer(int[][] ranges){
        int length = ranges.length;
        answer = new double[length];
        for(int i=0; i<length; i++){
            int i1 = ranges[i][0];
            int i2 = len + ranges[i][1]-1;
            double num = 0.0;
            if(i2<i1)num = -1.0;
            else num = dp[i2]-dp[i1];
            answer[i] = num;
        }
    }
}