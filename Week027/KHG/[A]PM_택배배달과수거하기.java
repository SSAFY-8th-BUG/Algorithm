import java.util.*;

class Solution {
    static int C, N;
    static Long answer;
    static List<Home> deliv, pick;
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        answer=0L;
        C=cap; N=n;
        deliv = new ArrayList<>();
        pick = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(deliveries[i]!=0){
                deliv.add(new Home(i+1, deliveries[i]));
            }
            if(pickups[i]!=0){
                pick.add(new Home(i+1, pickups[i]));
            }
        }
        
        move();
        
        return answer;
    }
    
    static void move(){
        while(deliv.size()>0 || pick.size()>0){
            int idx = doDelivery();
            
            doPickUp(idx);
        }
    }
    
    static int doDelivery(){
        if(deliv.size()==0) return 0;
        int i = deliv.size()-1; //마지막집
        int didx = deliv.get(i).idx;
        answer+=(didx)*2;
        int cap = C;
        while(deliv.size()>0 && cap>0){
            Home cur = deliv.get(i);
            if(cur.num <= cap){
                cap -= cur.num;
                deliv.remove(i);
                i--;
            }else{ //가진거 모두 배달
                cur.num -= cap;
                cap=0;
                break;
            }
        }
        
        return didx;
    }
    
    static void doPickUp(int didx){
        if(pick.size()==0) return;
        int i=pick.size()-1; //pickup할 마지막집
        if(didx < pick.get(i).idx){ //pickup하러 더 이동해야하는 경우
            answer += (pick.get(i).idx - didx)*2;
        }
        int cap = C;
        while(pick.size()>0 && cap>0){
            Home cur = pick.get(i);
            if(cur.num <= cap){
                cap -= cur.num;
                pick.remove(i);
                i--;
            }else{ //최대 회수 완료
                cur.num -= cap;
                cap=0;
                break;
            }
        }
    }
    
    static class Home{
        int idx;
        int num;
        Home(int idx, int num){
            this.idx = idx;
            this.num = num;
        }
    }
}