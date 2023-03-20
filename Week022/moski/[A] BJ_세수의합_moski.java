package baekjoon.세수의합_2295;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    static int N, ans;
    static int[] U;
    static Set<Integer> sum = new HashSet<>();
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ans = 0;
        U = new int[N];

        for (int i = 0; i < N; i++) {
            U[i] = Integer.parseInt(br.readLine());
        }
        // a + b = k - c
        for (int i = 0; i < N; i++) {
            int a = U[i];
            for (int j = 0; j < N; j++) {
                int b = U[j];
                sum.add(a+b);
            }
        }

        list = sum.stream().sorted().collect(Collectors.toList());

        Arrays.sort(U);
        // k - c
        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                int target = U[i] - U[j];
                if(search(target)) {
                    ans = Math.max(ans, U[i]);
                }
            }
        }

        System.out.println(ans);

    }

    static boolean search(int target) {
        int start = 0;
        int end = list.size()-1;

        while(start<=end) {
            int mid = (start+end)/2;

            if(list.get(mid) == target) {
                return true;
            }
            else if(list.get(mid) < target) {
                start = mid + 1;
            }else if(list.get(mid) > target) {
                end = mid - 1;
            }
        }
        return false;
    }
}