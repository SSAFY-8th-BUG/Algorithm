import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_그대로출력하기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        while(!str.equals("\n")){
            System.out.println(str);
            if(br.ready()){
                str = br.readLine();
            }else{
                break;
            }

        }

        // Scanner in = new Scanner(System.in);
        // while(in.hasNext()) {
        //   System.out.println(in.nextLine());
        // }
    }
}
