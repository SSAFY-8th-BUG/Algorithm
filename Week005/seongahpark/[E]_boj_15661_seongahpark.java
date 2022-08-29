package study;
import java.util.*;
import java.io.*;

public class Main {
    static int N, res = Integer.MAX_VALUE;
    static int[][] arr;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visit = new boolean[N];
        
        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        subset(0, 0);
        System.out.println(res);
    }

    static void subset(int depth, int index) {
    	// 기저 조건
        if (depth == N) {
            check();
            return;
        }
        
        visit[depth] = true;
        subset(depth + 1, index);
        visit[depth] = false;
        subset(depth + 1, index);
    }

    private static void check() {
        int start = 0;
        int link = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visit[i] != visit[j])
                    continue;
                if (visit[i])
                    start += arr[i][j] + arr[j][i];
                else
                    link += arr[i][j] + arr[j][i];
            }
        }
        int tmp = Math.abs(start - link);
        if (tmp < res)
            res = tmp;
    }
}
