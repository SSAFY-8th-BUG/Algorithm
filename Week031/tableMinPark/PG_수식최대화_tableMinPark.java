import java.util.*;

class Solution {
    static List<Long> num;
    static List<Character> operation;
    static char[][] oper = {
            {'+', '-', '*'},
            {'+', '*', '-'},
            {'-', '+', '*'},
            {'-', '*', '+'},
            {'*', '-', '+'},
            {'*', '+', '-'}
    };
    public long solution(String expression) {
        long answer = 0;

        for (char[] op : oper) {
            num = new ArrayList<>();
            operation = new ArrayList<>();

            String temp = "";
            for (char ex : expression.toCharArray()) {
                if (ex == '+' || ex == '-' || ex == '*') {
                    num.add(Long.parseLong(temp));
                    temp = "";
                    operation.add(ex);
                } else {
                    temp += ex;
                }
            }
            num.add(Long.parseLong(temp));

            for (int i = 0; i < 3; i++) {
                char next = op[i];

                for (int j = 0; j < operation.size(); j++) {
                    char now = operation.get(j);

                    if (next != now) continue;

                    long a = num.get(j);
                    long b = num.get(j + 1);
                    long sum = 0;

                    if (now == '+')
                        sum = a + b;
                    else if (now == '-')
                        sum = a - b;
                    else if (now == '*')
                        sum = a * b;

                    num.set(j, sum);
                    num.remove(j + 1);
                    operation.remove(j);

                    j--;
                }
            }


            answer = Math.max(answer, Math.abs(num.get(0)));
        }

        return answer;
    }
}