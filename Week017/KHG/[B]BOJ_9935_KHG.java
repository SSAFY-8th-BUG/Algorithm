import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {
	
	static Stack<Character> stack;
	static String st;
	static char[] bomb;
	static int lb;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = br.readLine();
        bomb = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        
        stack = new Stack<>();
        lb = bomb.length;
        
        for(int i=0; i<st.length(); i++) {

        	if(stack.size()<lb-1) {
        		stack.push(st.charAt(i));
        		continue;
        	}
        	
        	if(st.charAt(i)==bomb[lb-1]) { //폭탄의 마지막과 같으면
        		if(check()) { //문자열이 폭탄과 같으면
        			for(int j=0; j<lb-1; j++) {
        				stack.pop();
        			}
        		}else {
        			stack.push(st.charAt(i));
        		}
        	}else {
        		stack.push(st.charAt(i));
        	}
        }
        
        if(stack.size()==0) {
        	System.out.println("FRULA");
        }else {
        	for(char c : stack) {
        		sb.append(c);
        	}
        	System.out.println(sb);
        }
        
    }
	static boolean check() { //stack의 끝부분이 폭탄과 같은지 체크
		int sl=stack.size();
		for(int i=1; i<lb; i++) {
			if(bomb[lb-i-1]!=stack.elementAt(sl-i)) {
				return false;
			}
		}
		return true;
	}
	
	
	
}