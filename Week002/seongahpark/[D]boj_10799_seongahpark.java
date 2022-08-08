import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		Stack<Character> s = new Stack<>();
		int res = 0;
		
		// '('를 만날 때는 push, ')'를 만날 때는 pop인데 레이저인지 파이프인지 체크
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == '(') s.push(str.charAt(i));
			else if(str.charAt(i) == ')' && str.charAt(i-1) == '(') {
				// 레이저
				s.pop();
				res += s.size(); // 스택에 쌓여있는 ( 의 개수만큼 더해줌
			}else {
				// 닫혀서 잘린 개수 추가
				s.pop();
				res++;
			}
		}
		
		System.out.println(res);
	}

}
