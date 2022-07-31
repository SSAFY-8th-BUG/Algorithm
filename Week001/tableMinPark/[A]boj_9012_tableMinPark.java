import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_9012_tableMinPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        // '(' 은 짝을 맞춰볼 필요가 없기 때문에 스택에 무조건 삽입
        // ')' 은 짝을 맞춰봐야 하기 때문에 스택에 '(' 가 존재하는지 확인하여 짝을 확인해야함 
        for (int t = 1; t <= T; t++){
            // 괄호를 넣을 스택을 선언
            Stack<Character> stack = new Stack<>();

            // toCharArray를 이용해 입력받은 괄호를 char배열로 변환
            char[] arr = br.readLine().toCharArray();
            
            // 성공여부를 판단할 boolean타입 변수 선언
            boolean check = true;
            for (int i = 0; i < arr.length; i++){
                if (arr[i] == '(') stack.add(arr[i]);               // '(' 일 경우 스택에 삽입
                else {                                              // ')' 일 경우
                    if (stack.isEmpty() || stack.pop() == ')'){     // 스택이 비어있으면 ')' 를 맞출 '(' 가 없기 때문에 실패
                        check = false;                              // 스택의 젤 위의 요소가 ')' 이면 짝이 맞지 않기 때문에 실패
                        break;                                      // 실패한 경우 check 변수를 false로 바꾸고 for문을 종료
                    }
                }
            }
            if (!stack.isEmpty()) check = false;                    // for문이 끝나고 스택이 비어있지 않으면 짝이 맞지 않기 때문에 실패
            sb.append(check ? "YES" : "NO").append("\n");      // check 변수를 확인하여 "YES" or "NO" 로 출력
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}