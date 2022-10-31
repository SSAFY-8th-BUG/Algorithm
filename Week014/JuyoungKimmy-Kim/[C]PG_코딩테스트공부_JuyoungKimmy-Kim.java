import java.util.*;

// problems --> 필요알고력, 필요코딩력, 증가알고력, 증가코딩력
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp=0, maxCop=0;
        
        // 문제를 푸는데 필요한 가장 높은 알고력, 코딩력 조사
        for (int[] p : problems) {
            maxAlp=Math.max(p[0], maxAlp);
            maxCop=Math.max(p[1], maxCop);
        }
        
        // 이미 가지고 있는 알고력, 코딩력으로 모든 문제를 풀 수도 있으므로
        // max값들과 비교
        alp=Math.min(alp, maxAlp);
        cop=Math.min(cop, maxCop);
        
        // dp[알고력][코딩력] --> 이때가 되기까지 필요한 최소시간
        int[][] dp=new int[maxAlp+1][maxCop+1];
        for (int al=alp; al<=maxAlp; al++) {
            for (int co=cop; co<=maxCop; co++) {
                if (al==alp) {
                    dp[al][co]=co;
                } else if (co==cop) {
                    dp[al][co]=al;
                } else {
                    dp[al][co]=Math.min(dp[al-1][co], dp[al][co-1])+1;
                }
            }
        }
        
        Queue <Point> queue=new ArrayDeque<>();
        queue.offer (new Point (alp, cop, 0));
        
        while (!queue.isEmpty ()) {
            Point point=queue.poll();
            
            if (dp[point.al][point.co]<point.cost) continue;
            
            int alpReq, copReq, alpRwd,copRwd, cost;
            int nextAlp, nextCop;
            for (int[] p : problems) {
                alpReq=p[0];
                copReq=p[1];
                alpRwd=p[2];
                copRwd=p[3];
                cost=p[4];
                
                // point.al_alpRwd --> 현재 작업을 수행했을 때 바뀌는 알고력
                nextAlp=Math.min(maxAlp, point.al+alpRwd);
                nextCop=Math.min(maxCop, point.co+copRwd);
                
                // 현재 알고력, 코딩력으로 풀 수 없는 문제이거나,
                // 이미 이 알고력, 코딩력으로 문제를 해결한 경우
                if (!(alpReq<=point.al && copReq<=point.co)  ||
                   dp[nextAlp][nextCop] <= point.cost+cost) continue;
                
                dp[nextAlp][nextCop]=point.cost+cost;
                queue.offer (new Point (nextAlp, nextCop, point.cost+cost));            
            }
            
            // 알고력, 코딩력을 높이기
            nextAlp=Math.min(maxAlp, point.al+1);
            nextCop=Math.min(maxCop, point.co+1);
            
            // 알고력을 높이는 경우
            if (dp[nextAlp][point.co] > point.cost+1) {
                dp[nextAlp][point.co]=point.cost+1;
                queue.offer (new Point (nextAlp, point.co, point.cost+1));
            }
            
            // 코딩력을 높이는 경우
            if (dp[point.al][nextCop]>point.cost+1) {
                dp[point.al][nextCop]=point.cost+1;
                queue.offer (new Point (point.al, nextCop, point.cost+1));
            }
            
        }
        
        return dp[maxAlp][maxCop];
    }
    
    static class Point {
        int al, co, cost;
        
        Point (int al, int co, int cost) {
            this.al=al;
            this.co=co;
            this.cost=cost;
        }
    }
}

