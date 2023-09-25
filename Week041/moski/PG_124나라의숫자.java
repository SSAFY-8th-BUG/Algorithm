
public class PG_124나라의숫자 {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        int N = n;

        while(N!=0){
            int d = N%3;
            if(d == 0){
                N = N/3 - 1;
            }else{
                N = N/3;
            }

            if(d == 0) sb.insert(0, "4");
            else if(d == 1) sb.insert(0, "1");
            else if(d == 2) sb.insert(0, "2");
        }

        return sb.toString();
    }
}
