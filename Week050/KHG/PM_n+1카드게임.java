import java.util.*;

class Solution {
    static int N;
    static boolean[] have;
    static int answer;
    static int[] cards, cardRound;
    public int solution(int coin, int[] cards) {
        answer = 0;
        N=cards.length;
        this.cards = cards;
        have = new boolean[N+1];
        cardRound = new int[N+1];
        for(int i=0; i<N; i++){
            if(i<N/3){
                have[cards[i]]=true;
                cardRound[cards[i]]=1;
            }else{
                cardRound[cards[i]] = (i-N/3)/2+2;
            }
            
        }
        /*for(int i=1; i<=N; i++){
            System.out.print(i +":"+cardRound[i] +"  ");
        }System.out.println();*/
        
    
        answer = game(coin);
        return answer;
    }
    
    int game(int coin){
        int round=1;
        for(int idx=N/3; idx<N; idx+=2){
            int c1=cards[idx];
            int c2=cards[idx+1];
            have[c1]=true;
            have[c2]=true;
            
            /*System.out.print(round +":" + " "+coin+" = ");
            for(int i=1; i<=N; i++){
                if(have[i])
                    System.out.print(i+ ", ");
            }System.out.println();*/
            
            int minUseCoin=3;
            int useI=-1;
            for(int i=1; i<=N/2; i++){ //coin최소로 쓰고 다음단계 가는거 찾음
                if(have[i] && have[N+1-i]){
                    int useCoin=0;
                    if(cardRound[i] >1)
                        useCoin++;
                    if(cardRound[N+1-i] >1)
                        useCoin++;
                    if(useCoin>coin) continue;
                    if(useCoin<minUseCoin){
                        minUseCoin=useCoin;
                        useI=i;
                    }
                }
            }
            
            if(useI!=-1){
                have[useI]=false;
                have[N+1-useI]=false;
                coin-=minUseCoin;
                //System.out.println(useI+" "+(N+1-useI) +" 소진");
            }else{
                return round;
            }
            round++;
        }
        
        return round;
    }
    
}