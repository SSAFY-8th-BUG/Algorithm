import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_코딩은체육과목입니다 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (N>0){
            sb.append("long ");
            N -= 4;
        }
        sb.append("int");

        System.out.println(sb.toString());
    }
}
