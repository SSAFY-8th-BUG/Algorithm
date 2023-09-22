class Solution {
    static int[] lst;
    public long solution(int[] weights) {
        lst = new int[1001];
        long answer = 0;
        for(int weight : weights){
            answer += lst[weight];
            if(weight*2<=1000) answer+=lst[weight*2];
            if(weight%2==0) answer+=lst[weight/2];
            if(weight%2==0 && weight/2*3<=1000) answer+=lst[weight/2*3];
            if(weight%3==0) answer+=lst[weight/3*2];
            if(weight%3==0 && weight/3*4<=1000) answer+=lst[weight/3*4];
            if(weight%4==0) answer+=lst[weight/4*3];
            
            lst[weight]+=1;
        }
        return answer;
    }
}