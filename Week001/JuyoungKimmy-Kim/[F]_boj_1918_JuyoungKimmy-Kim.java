import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class F_boj_1918 {
	
	//연산자 우선 순위 
	public static int[] precedence= {1,1,2,2,-1,-1};
	public static char []op={'+','-','*','/','(',')'};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		String line=br.readLine();
		StringBuilder sb=new StringBuilder();
		
		Stack<Character> stack=new Stack<>();
		
		for (int i=0; i<line.length(); i++) {
			char ch=line.charAt(i);
			
			// #1. 글자이면 바로 출력
			if (ch>='A' && ch<='Z') 
				sb.append(ch);
			
			// #2. stack이 비어있거나 여는 괄호면 push
			else if (stack.isEmpty() || ch=='(')
				stack.push(ch);
			
			// #3. 그 외 다른 연산자 or 닫는 괄호
			else {
				
				// #3-1. 연산자 우선순위가 현재 스택의 top보다 크다면 바로 push
				if (prece(ch)>prece(stack.peek())) 
					stack.push(ch);
				
				//# 3-2. 현재 스택의 top에 있는 연산자보다 우선 순위가 낮다면
				//		 스택의 top에 있는 값보다 우선 순위가 높아질 때까지 빼면서 출력 
				//		 여는 괄호가 나오면 멈추고 출력하지 않음
				
				else {
					while (!stack.isEmpty() && prece(ch)<=prece(stack.peek())) {
							char c=stack.pop();
							if (c=='(') break;
							
							sb.append(c);
					}
					
					// # 3-3. 스택에 있는 값을 다 pop하고 나면 연산자를 스택에 push
					if (ch!=')') stack.push(ch);
				}
			}
		}
		
		// # 4. 나머지 stack에 있는 연산자 출력
		while (!stack.isEmpty()) 
			sb.append(stack.pop());

		System.out.println(sb);

	}
	
	static int prece(char ch) {
		for (int i=0; i<op.length; i++) {
			if (op[i]==ch)
				return precedence[i];
		}
		return -1;
	}

}
