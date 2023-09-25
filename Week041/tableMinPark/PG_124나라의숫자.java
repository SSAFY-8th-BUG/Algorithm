import java.util.*;

class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();

        while(n > 0) {
            int next = n % 3;
            if (next == 0) {
                next = 4;
                n--;
            }

            sb.insert(0, next);
            n /= 3;
        }

        return sb.toString();
    }
}