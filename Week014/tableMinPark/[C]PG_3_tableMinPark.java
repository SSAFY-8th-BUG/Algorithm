import java.util.*;

// 다시 풀어보기
public class PG_3_tableMinPark {
    public static void main(String[] args) {
        System.out.println(solution(10, 10, new int[][]{{10,15,2,1,2},{20,20,3,3,4}}));
    }

    static class P {
        int al;
        int co;
        int cost;
        
        public P(int al, int co, int cost) {
            this.al = al;
            this.co = co;
            this.cost = cost;
        }
    }

    static int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0, maxCop = 0;
        for (int[] p : problems) {
            maxAlp = Math.max(p[0], maxAlp);
            maxCop = Math.max(p[1], maxCop);
        }
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int al = alp; al <= maxAlp; al++) {
            for (int co = cop; co <= maxCop; co++) {
                if (co == cop) {
                    dp[al][co] = al;
                } else if (al == alp) {
                    dp[al][co] = co;
                } else {
                    dp[al][co] = Math.min(dp[al - 1][co], dp[al][co - 1]) + 1;
                }
            }
        }
        
        Queue<P> que = new LinkedList<>();
        que.offer(new P(alp, cop, 0));
        
        while (!que.isEmpty()) {
            P P = que.poll();
            
            if (dp[P.al][P.co] < P.cost) {
                continue;
            }

            int alpReq, copReq, alpRwd, copRwd, cost;
            int nextAlp, nextCop;
            for (int[] p : problems) {
                alpReq = p[0];
                copReq = p[1];
                alpRwd = p[2];
                copRwd = p[3];
                cost = p[4];
                nextAlp = Math.min(maxAlp, P.al + alpRwd);
                nextCop = Math.min(maxCop, P.co + copRwd);
                
                if (!(alpReq <= P.al && copReq <= P.co) ||
                   dp[nextAlp][nextCop] <= P.cost + cost) {
                    continue;
                }
                
                dp[nextAlp][nextCop] = P.cost + cost;
                que.offer(new P(nextAlp, nextCop, P.cost + cost));
            }
            
            nextAlp = Math.min(maxAlp, P.al + 1);
            nextCop = Math.min(maxCop, P.co + 1);
            if (dp[nextAlp][P.co] > P.cost + 1) {
                dp[nextAlp][P.co] = P.cost + 1;
                que.offer(new P(nextAlp, P.co, P.cost + 1));
            }
            
            if (dp[P.al][nextCop] > P.cost + 1) {
                dp[P.al][nextCop] = P.cost + 1;
                que.offer(new P(P.al, nextCop, P.cost + 1));
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}