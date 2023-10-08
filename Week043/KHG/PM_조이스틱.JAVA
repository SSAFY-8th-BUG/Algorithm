import java.util.*;
class Solution {
    static int answer, len;
    static char[] lst;
    static String name;
    public int solution(String name) {
        answer = Integer.MAX_VALUE;
        len = name.length();
        lst = new char[len];
        for(int i=0; i<len; i++){
            lst[i]='A';
        }
        this.name= name;
        if(check()) return 0;
        dfs(0,0,0);
        return answer;
    }
    
    void dfs(int idx, int sum, int dr){
        if(lst[idx] == name.charAt(idx)){ //알파벳 같으면
            if(sum+1<answer){
                if(dr==1) //이전에 ▶였으면
                    dfs((idx+1)%len, sum+1, 1); // ▶
                else if(dr==-1) //이전에 ◀였으면
                    dfs(Math.floorMod(idx-1,len), sum+1, -1); // ◀
                else{
                    dfs((idx+1)%len, sum+1, 1); // ▶
                    dfs(Math.floorMod(idx-1,len), sum+1, -1); // ◀
                }
            }
        }else{ //다르면
            //▲ ▼ 수행
            int cnt = Math.abs(name.charAt(idx) - lst[idx]);
            if(cnt>13){
                cnt = 26-cnt;
            }
            
            if(cnt+sum < answer){
                lst[idx] = name.charAt(idx);
                if(check()){ //전체가 동일
                    answer = cnt+sum;
                    lst[idx] = 'A';
                    return;
                }
                if(cnt+sum+1<answer){ 
                    dfs((idx+1)%len, sum+1+cnt, 1);  // ▶
                    dfs(Math.floorMod(idx-1,len), sum+1+cnt, -1); //◀
                }
                lst[idx] = 'A';
            }
            
            
        }
    }
    
    boolean check(){
        for(int i=0; i<len; i++){
            if(name.charAt(i)!=lst[i])
                return false;
        }
        return true;
    }
}