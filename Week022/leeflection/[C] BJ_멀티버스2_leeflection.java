package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ18869_멀티버스2 {
    public static void main(String[] args) throws Exception {
        int M, N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int[][] list = new int[M][N];
        int[][] subList = new int[M][N];
        int[][] value = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                list[i][j] = Integer.parseInt(st.nextToken());
                subList[i][j] = list[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            Arrays.sort(subList[i]);
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                value[i][j] = Arrays.binarySearch(subList[i], list[i][j]);
            }
        }

        int cnt = 0;

        for (int i = 0; i < M - 1; i++) {
            for (int j = i + 1; j < M; j++) {
                int cnt1 = 0;
                for (int k = 0; k < N; k++) {
                    if (value[i][k] == value[j][k]) cnt1++;
                    else break;
                }
                if (cnt1 != N) continue;
                else cnt++;
            }
        }

        System.out.print(cnt);
    }
}