package studygroup.Week27;
import java.util.*;
/**
 가입자를 최대한 늘리자
 판매액을 최대한 늘리자

 n명의 사용자들에게 m개를 할인하여 판매
 10%,20%,30%,40%

 이모티콘을 사거나 이모티콘 플러스 서비스에 가입
 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매
 구매 비용의 합이 일정 가격 이상되면 모두 취소하고 이모티콘 플러스 가입

 **/
public class PG_이모티콘할인행사 {
    static class Ans{
        int cost;
        int user;
        public Ans(int cost, int user){
            this.cost = cost;
            this.user = user;
        }
    }
    static ArrayList<Ans> list;
    static int N,M;
    static int[] disc = {10,20,30,40};
    static int[][] user;
    static int[] emoticon;
    static int[] ans = new int[2];
    static int[] candi;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        N = users.length;
        M = emoticons.length;
        user = users;
        emoticon = emoticons;
        candi = new int[M];
        list = new ArrayList<>();
        dfs(0);
        Collections.sort(list,(p1,p2)->{
            if(p1.user == p2.user){
                return p2.cost - p1.cost;
            }else{
                return p2.user - p1.user;
            }
        });
        ans[0] = list.get(0).user;
        ans[1] = list.get(0).cost;
        return ans;
    }
    public static void dfs(int depth){
        if(depth == M){
            int u = 0;
            int val = 0;
            //유저 만큼 돌면서
            for(int i=0; i<N; i++){
                int c = 0;
                //이모티콘 갯수만큼 돌면서
                for(int j=0; j<M; j++){
                    if(user[i][0] <= candi[j]){
                        //한 유저가 산 금액 누적
                        c += emoticon[j] * (100-candi[j]) / 100;
                    }
                }
                //해당 할인율 배열로 가입한 유저 수
                if(c >= user[i][1]){
                    u++;
                //가입은 안했지만 이모티콘을 산 금액
                }else{
                    val += c;
                }
            }
            list.add(new Ans(val,u));
            return;
        }
        for(int i=0; i<4; i++){
            candi[depth] = disc[i];
            dfs(depth+1);
        }
    }
}
