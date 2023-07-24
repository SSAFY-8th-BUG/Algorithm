import java.util.*;

class Solution {
    static char[] num;
    static int N;
    static boolean[] v;
    static Set<Integer> pri;

    public int solution(String numbers) {
        num = numbers.toCharArray();
        N = num.length;
        v = new boolean[N];
        pri = new HashSet<>();

        solve(0, "");

        return pri.size();
    }

    static void solve(int d, String now) {
        if (d == N) {
            return;
        }

        for (int idx = 0; idx < N; idx++) {
            if (v[idx]) continue;
            v[idx] = true;
            String next = now + num[idx];
            int n = Integer.parseInt(next);
            if (isPrime(n)) {
                System.out.println(n);
                pri.add(n);
            }
            solve(d + 1,  next);
            v[idx] = false;
        }
    }
    /*
        소수 판별법 1
        1을 제외한 모든 자연수 중에서 자신을 제외한
        또 다른 자연수로 나누어떨어지는지 확인하는 방법
    */
    // static boolean isPrime(int n) {
    //     if (n <= 1) {
    //         return false;
    //     }
    //     for (int i = 2; i < n; i++) {
    //         if (n % i == 0) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    /*
        소수 판별법 2
        √n 까지의 수 중 1을 제외하고 그 자연수의 약수가 있는지 확인하는 방법
    */
    static boolean isPrime(int n) {
        if (n < 4) {
            return n <= 1 ? false : true;
        }

        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}