import java.util.*;

public class PG_수식최대화 {
    static String op[][] = {
        { "+", "-", "*" },
        { "+", "*", "-" },
        { "-", "*", "+" },
        { "-", "+", "*" },
        { "*", "-", "+" },
        { "*", "+", "-" } };
    static List<String> exp = new ArrayList<>();

    public long solution(String expression) {
        long answer = 0;

        int idx = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*') {
                exp.add(expression.substring(idx, i));
                exp.add(String.valueOf(expression.charAt(i)));
                idx = i + 1;
            }
        }
        exp.add(expression.substring(idx));

        for(int i=0; i<6;i++){
            List<String> list = new ArrayList<>(exp);
            for(int j=0;j<3;j++){
                for(int k=0;k<list.size();k++){
                    if(op[i][j].equals(list.get(k))){
                        long n1 = Long.parseLong(list.get(k-1));
                        long n2 = Long.parseLong(list.get(k+1));
                        long n3 = 0;
                        if(op[i][j].equals("+")){
                            n3 = n1+n2;
                        }else if(op[i][j].equals("-")){
                            n3 = n1-n2;
                        }else{
                            n3 = n1*n2;
                        }

                        list.set(k-1, String.valueOf(n3));
                        list.remove(k);
                        list.remove(k);
                        k--;
                    }
                }

            }

            answer = Math.max(answer, Math.abs(Long.parseLong(list.get(0))));
        }

        return answer;
    }
}