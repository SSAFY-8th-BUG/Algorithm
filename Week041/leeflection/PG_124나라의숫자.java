package studygroup.Week041;
import java.util.*;

public class PG_124나라의숫자 {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();

        while(n > 0) {
            int remainder = n%3;
            if(remainder == 0) {
                sb.append(4);
            } else if(remainder == 1) {
                sb.append(1);
            } else {
                sb.append(2);
            }
            if(remainder == 0)  {
                n -= 1;
            }
            n /= 3;
        }

        return sb.reverse().toString();
    }
}

