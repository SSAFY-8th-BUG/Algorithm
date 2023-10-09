class Solution {
    public int[] solution(long begin, long end) {
        int len = (int)(end-begin)+1;
        int[] answer = new int[len];
        for(long i=begin; i<=end; i++){
            answer[(int)(i-begin)]=block(i);
        }
        return answer;
    }
    
    int block(long num){
        if(num==1L)return 0;
        int ret=1;
        for(long i=2L; i<= Math.sqrt(num); i++){
            if(num%i==0){
                if(num/i<=10000000)
                    return (int)(num/i);
                else
                    ret=(int)i;
            }
        }
        
        return ret;
    }
}