import java.util.*;
public class PG_외벽점검 {
    static int answer;
    static int weakSize;
    // dist size
    static int distSize;
    static int[] tmp;
    static boolean[] checked;
    static int[] d;
    static int[][] weakCase;
    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        weakSize = weak.length;
        distSize = dist.length;
        tmp = new int[distSize];
        checked = new boolean[distSize];
        d = new int[distSize];
        weakCase = new int[weakSize][weakSize];

        for(int i=0;i<distSize;i++){
            d[i] = dist[i];
        }

        // 단방향 탐색을 위해 배열 저장
        for(int i=0;i<weakSize;i++){
            for(int j=0;j<weakSize;j++){
                weakCase[i][j] = weak[j];

                if(j <= i-1) weakCase[i][j] += n;

            }

            // for(int j=0;j<weakSize;j++){
            //     System.out.print(weakCase[i][j] + " ");
            // }
            // System.out.println();
            Arrays.sort(weakCase[i]);
        }

        // 순열로 배정 순서 정하기
        perm(0);


        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    static void perm(int idx){
        if(idx == distSize){
            // 몇명으로 외벽 체크가 가능할지 테스트
            int cnt = checkWall();

            if(answer > cnt){
                answer = cnt;
            }

            return;
        }

        for(int i=0;i<distSize;i++){
            if(checked[i]) continue;
            checked[i] = true;
            tmp[idx] = d[i];
            perm(idx+1);
            checked[i] = false;
        }
    }

    static int checkWall(){
        // 현재 배열로 외벽을 차례로 체크 하는 법
        int cnt = Integer.MAX_VALUE;
        for(int i=0;i<weakSize;i++){
            // 벽 좌표
            int wIdx = 0;
            // 작업자 좌표
            int pIdx = 0;

            // 지금까지 체크한 외벽 범위
            int chk = -1;
            while(wIdx < weakSize){

                // 범위가 현재까지 체크한 양보다 작은 경우
                if(chk < weakCase[i][wIdx]){
                    // 만약 작업자를 다 썻음에도 체크 못할시
                    if(pIdx >= distSize) break;
                    chk = weakCase[i][wIdx] + tmp[pIdx];
                    // 다음 작업자 대기
                    pIdx++;
                }
                wIdx++;
            }
            // if(i == 3){
            //     for(int k=0;k<weakSize;k++){
            //         System.out.print(weakCase[i][k] + " ");
            //     }
            //     System.out.println(" cnt : " + cnt + " person : " + pIdx);
            // }
            // 마지막까지 돌았을 때 모든 범위를 클리어했을 때
            if(wIdx == weakSize && chk >= weakCase[i][wIdx-1]){
                if(pIdx <= distSize){
                    if(cnt > pIdx){
                        // for(int k=0;k<weakSize;k++){
                        //     System.out.print(weakCase[i][k]);
                        // }
                        // System.out.println(" cnt : " + cnt + " person : " + pIdx);
                        cnt = pIdx;
                    }
                }
            }

        }

        return cnt;
    }
}
