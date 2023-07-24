import java.util.*;
class Solution {
    static int answer;
    static double cover;
    public int solution(int n, int[] stations, int w) {
        answer=0;
        cover =(double) w*2+1;
    
        int start=0;
        for(int st : stations){
            int s1 = st-w;
            int s2 = st+w;
            int len = s1 - start-1;
            if(len<=0){
                start = s2;
                continue;
            }
            answer+=getNum(len);
            start = s2;
        }
        
        //끝 나머지 처리
        if(start<n){
            int len = n - start;
            answer+=getNum(len);
        }
        return answer;
    }
    
    static int getNum(int len){
        return (int)Math.ceil((double)len/cover);
    }
    
    
}