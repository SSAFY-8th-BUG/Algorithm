import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 
 * O(n) -> n은 주어지는 문자열의 길이
 * 
 */
public class A_boj_9012 {

	static int T;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			
			Stack<Character> stack=new Stack<>();
			
			String line=br.readLine();
			
			boolean flag=true;
			for (int i=0; i<line.length(); i++) {
				
				char op=line.charAt(i);
							
				// #1. 여는 괄호일 경우 stack에 push
				if (op=='(') stack.push(op);
				
				/*
				 *  #2. 닫는 괄호 1개는 여는 괄호 1개와 매칭 되어야 함
				 *  	닫는 괄호가 나왔는데 스택이 비어있다면 실패
				 */
				else if (op==')') {
					if (stack.isEmpty()) {
						flag=false;
						break;
					}
					stack.pop();		
				}
			}
			
			if (flag==false || !stack.isEmpty()) {
				System.out.println("NO");
			}
			else
				System.out.println("YES");
		}
	}
}
