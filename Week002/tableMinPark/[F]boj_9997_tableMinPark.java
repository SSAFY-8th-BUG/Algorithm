// 시간초과
// 비트마스크를 통해서 풀어야한다.
// 모든 알파벳이 포함되어야 한다.

import java.io.*;

// 시간복잡도 -> O(2^n)
// 공간복잡도 -> O(n)
public class boj_9997_tableMinPark {

    static int N;
    static final int alp = (1 << 26) - 1;   // 첫자리 1, 0이 26개인 이진수 - 1 = 1이 26개인 이진수
    static int[] wordAlp;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        wordAlp = new int[N];   // 단어별 알파벳 갯수를 저장하기 위한 배열
        
        for (int i = 0; i < N; i++){
            char[] input = br.readLine().toCharArray();
            for (char in : input){
                // // | >> 두 정수를 한 비트씩 비교하면서 양쪽 비트 중 하나라도 1이면 결과가 1이고 나머지는 0을 반환
                wordAlp[i] |= 1 << in-'a';      // 알파벳 번호에 해당하는 이진수 자리가 1로 바뀜 (0 | 1 ==> 1)
            }
        }
        answer = 0;
        make(0, 0);

        System.out.println(answer);
        br.close();
    }

    static void make(int depth, int mask){
        if (depth == N){
            if (mask == alp) answer++;
            return;
        }
        make(depth + 1, mask | wordAlp[depth]);
        make(depth + 1, mask);
    }
}

// 부분집합
/* 
import java.io.*;
import java.util.*;

public class boj_9997_tableMinPark {

    static String[] arr;
    static boolean[] v;
    static int answer;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new String[N];
        v = new boolean[N];
        
        for (int i = 0; i < N; i++) arr[i] = br.readLine();

        answer = 0;
        make(0);

        System.out.println(answer);
        br.close();
    }

    static void make(int depth){
        if (depth == N){
            Set<Character> al = new HashSet<>();
            for (int i = 0; i < N; i++){
                if (!v[i]) continue;
                char[] str = arr[i].toCharArray();
                for (char s : str) al.add(s);
                if (al.size() == 26) {
                    answer++;
                    break;
                }
            }
            return;
        }

        v[depth] = true;
        make(depth + 1);
        v[depth] = false;
        make(depth + 1);
    }
}
*/