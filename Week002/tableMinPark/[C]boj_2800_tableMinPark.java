import java.io.*;
import java.util.*;

// 시간복잡도 -> O(2^n)
// 공간복잡도 -> O(2^n)
public class boj_2800_tableMinPark {

    // 괄호쌍 인덱스를 저장하기 위한 클래스
    static class Pair{
        int l;
        int r;
        public Pair(int l, int r){
            this.l = l;     // '('
            this.r = r;     // ')'
        }
    }

    static List<String> answer;     // 각 상황별 문자열을 저장하기 위한 리스트
    static List<Pair> arr;          // 괄호 쌍정보를 저장하기 위한 리스트
    static boolean[] v;             // 부분집합을 위한 방문배열
    static char[] input;            // 문자열 입력값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        input = br.readLine().toCharArray();

        arr = new ArrayList<>();        
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length; i++){         // 스택을 이용해 괄호의 쌍 인덱스를 구하여 Pari 객체생성 
            if (input[i] == '(') stack.push(i);
            else if (input[i] == ')') {
                arr.add(new Pair(stack.pop(), i));
            }
        }           

        answer = new ArrayList<>();
        v = new boolean[arr.size()];

        make(0);        // 부분집합을 구해서 괄호 경우의수를 모두 구함

        Collections.sort(answer);       // 결과값을 사전순으로 정렬

        StringBuilder sb = new StringBuilder();
        for (String ans : answer) sb.append(ans).append("\n");

        System.out.println(sb.toString().trim());
        br.close();
    }

    static void make(int depth){
        if (depth == arr.size()){
            boolean[] check = new boolean[input.length];    // 괄호의 사용 유무를 보기 위한 논리배열

            int count = 0;
            for (int i = 0; i < arr.size(); i++){           // 사용하지 않는 괄호를 check 배열에서 true
                if (v[i]) continue;
                count++;                                    // 몇개의 괄호를 사용하는지 새기 위함
                check[arr.get(i).l] = true;                 // 왼쪽괄호 true
                check[arr.get(i).r] = true;                 // 오른쪽괄호 true
            }
            
            if (count == 0) return;                         // 어떤괄호도 선택되지 않았을 때 중지 (All false상황)

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length; i++){         // 입력받은 문자열을 돌면서
                if (check[i]) continue;                     // 사용하지 않는 괄호는 패스
                sb.append(input[i]);                        // 사용하는 괄호와 문자는 입력
            }
            if (!answer.contains(sb.toString())) answer.add(sb.toString()); // 중복확인하여 배열에 저장
            return;
        }
        v[depth] = true;        // 부분집합 
        make(depth + 1);        
        v[depth] = false;
        make(depth + 1);
    }
}
