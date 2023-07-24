import java.util.*;

class Solution {
    static String numberStr;
    static int answer, len;
    static boolean[] visited;
    static boolean[] dupCheck;
    public int solution(String numbers) {
        answer = 0;
        len = numbers.length();
        visited = new boolean[len];
        dupCheck = new boolean[100000000];
        numberStr = numbers;
        rec(0,"");
        return answer;
    }
    
    static void rec(int idx, String numStr){
        if(numStr.length()!=0){
            isPrime(numStr);
        }
        if(idx ==len) return;
        for(int i=0; i<len; i++){
            if(visited[i]) continue;
            if(numStr.length()==0 && numberStr.charAt(i)=='0')continue;
            visited[i]=true;
            rec(idx+1, numStr + numberStr.charAt(i));
            visited[i]=false;
        }
    }
    
    static void isPrime(String numStr){
        int num = Integer.parseInt(numStr);
        //System.out.println(numStr);
        if(dupCheck[num])return;
        dupCheck[num]=true;
        if(num<=1)return;
        for(int i=2; i<=(int)Math.sqrt(num); i++){
            if (num%i==0) return;
        }
        answer++;
    }
}