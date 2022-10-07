import java.io.*;
import java.util.*;

public class swea_2115_tableMinPark {

    static int T, N, M, C, answer;
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            v = new boolean[N][N];
            for (int y = 0; y < N; y++){
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            for (int y = 0; y < N; y++){
                for (int x = 0; x <= N - M; x++){

                    int A = 0;
                    int ASum = 0;
                    // A의 최대합구하기
                    for (int mask = 1; mask < 1 << M; mask++){
                        int sum = 0;
                        int mulSum = 0;
                        for (int i = 0; i < M; i++){
                            if ((mask & 1 << i) != 0){
                                sum += map[y][x + i];
                                mulSum += map[y][x + i] * map[y][x + i];
                            }
                        }
                        if (sum > C) continue;
                        if (sum > A) ASum = Math.max(ASum, mulSum);
                    }

                    // A의 방문체크
                    for (int i = x; i < x + M; i++) v[y][i] = true;
                    // B의 최대합구하기
                    int BSum = solve();
                    // A의 방문체크해제
                    for (int i = x; i < x + M; i++) v[y][i] = false;

                    answer = Math.max(answer, ASum + BSum);
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static int solve(){
        int BSum = 0;
        for (int y = 0; y < N; y++){
            for (int x = 0; x <= N - M; x++){
                if (check(y, x)){   
                    int B = 0;                 
                    for (int mask = 1; mask < 1 << M; mask++){
                        int sum = 0;
                        int mulSum = 0;
                        for (int i = 0; i < M; i++){
                            if ((mask & 1 << i) != 0){
                                sum += map[y][x + i];
                                mulSum += map[y][x + i] * map[y][x + i];
                            }
                        }
                        if (sum > C) continue;
                        if (sum > B) BSum = Math.max(BSum, mulSum);
                    }
                }
            }
        }
        return BSum;
    }

    // A랑 겹치는지 확인하는 함수
    static boolean check(int y, int x){    
        for (int i = x; i < x + M; i++){
            if (v[y][i]) return false;
        }
        return true;
    }
}