import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
 
public class Main {
    
    static String input;
    static Deque<Character> stack;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        stack = new ArrayDeque<>();
 
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push('(');
                continue;
            }
            if (input.charAt(i) == ')') {
                stack.pop();
 
                if (input.charAt(i - 1) == '(') {
                    result += stack.size();
                } else {
                    result++;
                }
            }
        }
 
        System.out.println(result);
    }
 
}