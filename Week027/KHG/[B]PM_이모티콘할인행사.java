import java.util.*;

class Solution {
    static int[] answer, emoticons;
    static int N,M;
    static int[][] users;
    static int[] sales;
    public int[] solution(int[][] users2, int[] emoticons2) {
        answer = new int[]{0,0};
        N = users2.length;
        M = emoticons2.length;
        users = users2;
        emoticons = emoticons2;
        
        sales = new int[M];
        makeSales(0);
        return answer;
    }
    
    //경우의 수 만들기
    static void makeSales(int idx){ 
        if(idx==M){
            cal(); //경우의 수에 맞는 계산하기
            return;
        }
        for(int s=10; s<=40; s+=10){
            sales[idx]=s;
            makeSales(idx+1);
        }
    }
    
    static void cal(){
        //유저별 사용할 금액계산
        int[] sums = new int[N];
        for(int i=0; i<N; i++){
            int[] user = users[i];
            for(int j=0; j<M; j++){
                if(sales[j]>=user[0]){
                    int price = emoticons[j]*(100-sales[j])/100;
                    sums[i]+=price;
                }
            }
        }
        
        //유저별 플러스 가입수 세기
        int cnt=0;
        int expense=0;
        for(int i=0; i<N; i++){
            if(sums[i]>=users[i][1]){
                cnt+=1;
            }else{
                expense+=sums[i];
            }
        }
        
        //answer 최신화
        if(cnt>answer[0]){
            answer[0]=cnt;
            answer[1]=expense;
        }else if(cnt==answer[0] && expense>answer[1]){
            answer[1]=expense;
        }
    }
}