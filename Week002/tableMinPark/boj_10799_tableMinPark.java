import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_10799_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        int answer = 0;

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == '(') stack.push(arr[i]);  // '(' 이면 스택에 삽입
            else {                                  // ')' 일 때
                if (arr[i - 1] == '(') {            // 바로 앞 문자가 '(' 일 때는 절단기 이기 때문에
                    stack.pop();                    // 바로 앞 '(' 를 pop하고 
                    answer += stack.size();         // 현재 스택에 들어있는 '(' 개수만큼 막대 추가
                } else {                            // 절단기가 아니라 막대의 끝일 때
                    stack.pop();                    // '('를 pop하고
                    answer += 1;                    // 끝나는 부분이기 때문에 + 1 (막대의 끝은 잘려있기 때문에)
                }
            }
        }

        System.out.println(answer);
        br.close();
    }
}
