class Solution {
    public String solution(int n) {
        String answer = "";
        
        while(n>0){
            if(n%3==1){
                answer = "1" + answer;
            }else if(n%3==2){
                answer = "2" + answer;
            }else{
                answer = "4" + answer;
            }
            if(n%3==0) n-=3;
            else n-=(n%3);
            n/=3;
        }
        return answer;
    }
}