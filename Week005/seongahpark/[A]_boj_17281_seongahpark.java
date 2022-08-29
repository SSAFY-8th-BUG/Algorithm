package study;
import java.util.*;
import java.io.*;

public class Main {
    static int N, res;
    static int[][] players;
    static boolean[] select;
    static int[] lineUp;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
 
        players = new int[N + 1][10];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        select = new boolean[10];
        lineUp = new int[10];
 
        select[4] = true; // 4번 타자는 1번으로 고정되어있음.
        lineUp[4] = 1;
 
        res = 0;
        perm(2);
 
        System.out.println(res);
    }
 
    // 순열
    public static void perm(int num) {
        if (num == 10) {
            playBaseBall();
            return;
        }
 
        for (int i = 1; i <= 9; i++) {
            if (select[i]) {
                continue;
            }
            select[i] = true;
            lineUp[i] = num;
            perm(num + 1);
            select[i] = false;
        }
    }
 
    // 야구 경기
    public static void playBaseBall() {
        int score = 0;
        int start = 1; 
        boolean[] base; // 홈, 1루, 2루, 3루
 
        for (int i = 1; i <= N; i++) { 
            int outCnt = 0;
            base = new boolean[4]; 
 
            outer: while (true) {
                for (int j = start; j <= 9; j++) {
                    int hitter = players[i][lineUp[j]]; // 타자의 행동.
 
                    switch (hitter) {
                    case 0: // out
                        outCnt++;
                        break;
                    case 1: // 1루타
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3) { // 홈으로 들어올 경우
                                    score++; 
                                    base[k] = false; 
                                    continue;
                                }
 
                                // 1, 2루에 경우 1루씩 진루
                                base[k] = false;
                                base[k + 1] = true;
                            }
                        }
                        base[1] = true; // 홈에서 1루로 진루
                        break;
                    case 2: // 2루타
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3 || k == 2) { // 3루 혹은 2루 -> 홈
                                    score++; 
                                    base[k] = false;
                                    continue;
                                }
 
                                // 1루 -> 2루씩 진루
                                base[k] = false;
                                base[k + 2] = true;
                            }
                        }
                        base[2] = true; // 홈에서 2루로 진루
                        break;
                    case 3: // 3루타
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) { // 홈 제외 모든 선수는 홈으로 들어옴
                                score++;
                                base[k] = false;
                            }
                        }
 
                        base[3] = true; // 홈에서 3루로 진루
                        break;
                    case 4: // 홈런
                        for (int k = 1; k <= 3; k++) {
                            if (base[k]) {
                                score++; // 주자 1명 당 점수 1점씩 획득
                                base[k] = false;
                            }
                        }
                        score++; // 홈런친 타자의 점수 1점 추가.
                        break;
                    }
 
                    if (outCnt == 3) { // 아웃 카운트가 3개 일 경우
                        start = j + 1;
                        if (start == 10) {
                            start = 1;
                        }
                        break outer;
                    }
                }
                // 1~9번까지 타자가 한 이닝에 전부 안타를 쳐서 아웃카운트가 발생하지 않게 되면
                // 무한 루프를 돌기때문에 1로 초기화해야 함.
                start = 1;
            }
        }
 
        res = Math.max(res, score);
    }
 
}

