// 정규표현식 문제
// 블로그참고함 (정규표현식 사용하는 방법)
import java.io.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(1)
public class boj_1013_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String v = "(100+1+|01)+";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++){
            String str = br.readLine();
            if (str.matches(v)) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
        br.close();
    }
}
