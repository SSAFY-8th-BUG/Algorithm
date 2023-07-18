import java.util.*;
public class PG_소수찾기 {
    static char[] number;
    static char[] p;
    static boolean[] visited;
    static int answer;
    static Set<Integer> duSet = new HashSet<>();

    public int solution(String numbers) {
        answer = 0;

        number = new char[numbers.length()];
        p = new char[numbers.length()];

        number = numbers.toCharArray();

        for(int i=1;i<= numbers.length();i++){
            visited = new boolean[numbers.length()];

            perm(0, i);
        }

        answer = duSet.size();
        return answer;
    }

    static void perm(int idx, int num){
        if(idx == num){
            int n = makeNum(num);
            if(n == 0 || n == 1){
                return;
            }
            if(isPrime(n)){
                duSet.add(n);
            }
        }

        for(int i=0;i<number.length;i++){
            if(!visited[i]){
                visited[i] = true;
                p[idx] = number[i];
                perm(idx+1, num);
                visited[i] = false;
            }
        }
    }

    static int makeNum(int num){
        int n = p[0] - '0';
        for(int i=1; i<num; i++){
            n *= 10;
            n += p[i] - '0';
        }
        // System.out.println(n);
        return n;
    }

    static boolean isPrime(int num){
        for(int i=2; i*i<=num; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}
