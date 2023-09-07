class Solution {
    static int len, answer;
    static boolean[] opened;
    public int[] cards;
    public int solution(int[] cards2) {
        answer=0;
        len=cards2.length;
        opened = new boolean[len];
        cards = cards2;
        
        for(int i=0; i<len; i++){ 
            opened[i]=true;
            rec(1,1,0,i); //1번 그룹 시작
            opened[i]=false;
        }
        
        return answer;
    }
    
    void rec(int group, int cnt1, int cnt2, int idx ){
        int idx2 = cards[idx]-1;
        if(opened[idx2]){ //이미 연 상자
            if(group==1){ //1번 그룹
                if(cnt1==len)return;
                else if(cnt1 *(len-cnt1)<=answer)return;
                for(int i=0; i<len; i++){ //2번 그룹 시작
                    if(opened[i])continue;
                    opened[i]=true;
                    rec(2, cnt1, 1, i);    
                    opened[i]=false;
                }
                
            }else{ //2번 그룹
                answer = Math.max(answer, cnt1*cnt2);
                return;
            }
        }else{ //열지않은 상자
            opened[idx2]=true;
            if(group==1){
                rec(group, cnt1+1, cnt2, idx2);
            }else{
                rec(group, cnt1, cnt2+1, idx2);
            }
            opened[idx2]=false;
        }
    }
}