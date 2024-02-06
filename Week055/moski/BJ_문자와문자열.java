import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_문자와문자열 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = Integer.parseInt(br.readLine());

        System.out.print(str.charAt(n-1));
    }
}
