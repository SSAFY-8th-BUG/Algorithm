import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int len=number.length();
        
        for(int i=0; i<len; i++){
            if(k==len-i)break;
            boolean del=false;
            for(int j=1; j<=k; j++){ //k개 만큼 비교
                if(i+j==len)break;
                if(number.charAt(i)<number.charAt(i+j)){
                    del=true;
                    break;
                }else{
                    continue;
                }
                
            }
            if(!del){ //제거하지 않음
                answer+=number.charAt(i);
            }else{ //제거
                k--;
                if(len==0)break;
            }
        }
        return answer;
    }
}