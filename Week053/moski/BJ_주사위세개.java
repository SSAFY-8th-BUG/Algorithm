import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_주사위세개 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if(a==b){
            if(b==c){
                System.out.println(10000+a*1000);
            }else{
                System.out.println(1000+a*100);
            }
        }else{
            if(b==c){ //a!=b , b==c
                System.out.println(1000+b*100);
            }else if(a==c){ // a!=b, a==c
                System.out.println(1000+a*100);
            }else{
                System.out.println(Math.max(a,Math.max(b,c))*100);
            }
        }

    }
}
