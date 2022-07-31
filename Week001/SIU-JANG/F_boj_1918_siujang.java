package a;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class F_boj_1918_siujang {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		Stack<Character> st = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				st.add('(');
			} else if (str.charAt(i) == ')') {
				while (st.peek() != '(') {
					System.out.print(st.pop());
				}
				st.pop();
			} else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				System.out.print(str.charAt(i));
			} else {
				switch(str.charAt(i)) {
					case '*':
					case '/':
						while (!st.isEmpty() && (st.peek() == '*' || st.peek() == '/')) {
							System.out.print(st.pop());
						}
						st.add(str.charAt(i));
						break;
					case '+':
					case '-':
						while (!st.isEmpty() && st.peek() != '(') {
							System.out.print(st.pop());
						}
						st.add(str.charAt(i));
						break;
				}
			}
		}
		
		while (!st.isEmpty()) {
			System.out.print(st.pop());
		}
	}
}