class Solution {
    static int[][] scores;
    public int solution(int[][] scores2) {
        int answer = 0;
        scores = scores2;
        answer = getRank();
        return answer;
    }
    
    static int getRank(){
        int rank = 1;
        int[] myScore = scores[0];
        int mySum = scores[0][0]+scores[0][1];
        for(int i=1; i<scores.length; i++){
            int[] score = scores[i];
            if(score[0]>myScore[0] && score[1]>myScore[1])
                return -1;
            if(score[0]+score[1] > mySum && check(i)) rank++;
        }
        
        return rank;
    }
    
    static boolean check(int idx){
        for(int i=0; i<scores.length; i++){
            if(scores[i][0] > scores[idx][0] && scores[i][1] > scores[idx][1]) 
                return false;
        }
        return true;
    }
    
}