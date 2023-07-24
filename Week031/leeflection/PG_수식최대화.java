package studygroup.Week031;
import java.util.*;
public class PG_수식최대화 {
    /**
     숫자 , + - *의 우선순위를 두고
     **/
        public static long solution(String expression) {
            long answer = Long.MIN_VALUE;
            String op[][] = { { "+", "-", "*" }, { "+", "*", "-" }, { "-", "*", "+" },
                    { "-", "+", "*" }, { "*", "-", "+" }, { "*", "+", "-" } };

            ArrayList<String> list = new ArrayList<String>();
            int start = 0;
            for (int i = 0; i < expression.length(); i++) {
                //연산자를 만나면
                if (!Character.isDigit(expression.charAt(i))) {
                    //숫자만 자르기
                    list.add(expression.substring(start, i));
                    //연산자 추가하기
                    list.add(expression.charAt(i) + "");
                    start = i + 1;
                }
            }
            //남은거 추가
            list.add(expression.substring(start));
            //우선순위 경우의 수
            for (int i = 0; i < op.length; i++) {
                ArrayList<String> sub_list = new ArrayList<String>(list);
                //우선순위 하나씩 꺼내기
                for (int k = 0; k < 3; k++) {
                    //파싱된 리스트를 순회
                    for (int j = 0; j < sub_list.size(); j++) {
                        //우선순위에 해당하는 값을 찾으면
                        if (op[i][k].equals(sub_list.get(j))) {
                            //앞에값과 뒤에값 연산한 결과로 바꿔치기한다.
                            sub_list.set(j - 1, calc(sub_list.get(j - 1), sub_list.get(j), sub_list.get(j + 1)));
                            //뒤에값 지우고
                            sub_list.remove(j);
                            //연산자도 지운다.
                            sub_list.remove(j);
                            j--;
                        }
                    }
                }
                answer = Math.max(answer, Math.abs(Long.parseLong(sub_list.get(0))));
            }

            return answer;
        }

        private static String calc(String num1, String op, String num2) {
            long n1 = Long.parseLong(num1);
            long n2 = Long.parseLong(num2);

            if (op.equals("+"))
                return n1 + n2 + "";
            else if (op.equals("-"))
                return n1 - n2 + "";

            return n1 * n2 + "";
        }
    }

