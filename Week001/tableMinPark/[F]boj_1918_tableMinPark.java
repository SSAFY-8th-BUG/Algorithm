import java.io.*;
import java.util.*;

// 시간복잡도 - O(n)
// 공간복잡도 - O(n)
public class boj_1918_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] arr = br.readLine().toCharArray();

        Stack<Character> s = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char a : arr){            
            if (a < 65){
                if (a == ')'){          // ')'가 나왔을 때 '(' 가 나올 떄까지 pop 하고 '(' 기호까지 pop
                    while(!s.isEmpty() && s.peek() != '('){
                        sb.append(s.pop());
                    }
                    s.pop();
                } else if (a == '('){   // '(' 가 나왔을 때는 push (괄호 경계선 확인)
                    s.push(a);
                } else {        // 현재 연산자의 우선순위랑 같은 연산자가 나올때까지 스택에서 pop (우선순위가 큰 연산자가 먼저 계산되어야한다.)
                    while(!s.isEmpty()){
                        if ((s.peek() == '+' || s.peek() == '-') && (a == '*' || a == '/')) break;  // 스택의 가장 위 항목이 우선순위가 낮아지면 멈춤
                        if (s.peek() == '(') break;                                                 // '(' 가 나오면 멈춤
                        sb.append(s.pop());                                                         // 스택에서 pop
                    }
                    s.push(a);      // 자신보다 높은 우선순위의 연산자를 pop 하고 마지막에 push
                }
                System.out.println(sb.toString());
            } else sb.append(a);        // 피연산자는 바로 출력
        }

        while(!s.isEmpty()) sb.append(s.pop());     // 남은기호가 있으면 싹 다 pop
        System.out.println(sb.toString());
    }
}
