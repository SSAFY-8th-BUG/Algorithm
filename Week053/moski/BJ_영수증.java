import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_영수증 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sum += a * b;
        }

        System.out.println(X == sum ? "Yes" : "No");
    }
}
