import java.util.*;

class Solution {
    static String str;
    static int len;
    public int solution(String s) {
        str = s;
        len = s.length();
        int answer = len;
    
        for(int l=1; l<=len/2; l++){
            int count=0;
            double dl = (double)l;
            int ei = (int)Math.ceil(len/dl);
            for(int i=0; i<ei-1; i++){
                int cnt=1;
                while(i+1<ei){
                    boolean isSame =is_same(i,l); //동일한지
                    if(!isSame){
                        if(i+1 == ei-1){ //마지막인경우
                            int left = len%l;
                            if(left==0)left=l;
                            count+=left;
                        }
                        break;
                    }
                    cnt++;
                    i++;
                }
                if(cnt>1){ //압축함
                    count+=Integer.toString(cnt).length();
                    count+=l;
                }else{
                    count+=l;
                }

            }
            answer = Math.min(answer,count);

        }
        return answer;
    }
    
    boolean is_same(int idx, int l){
        int idx1 = idx*l;
        int idx2 = idx1+l;
        for(int i=0; i<l; i++){
            if(idx2+i>=len)return false;
            if(str.charAt(idx1+i)!=str.charAt(idx2+i))
                return false;
        }
        return true;
    }
}