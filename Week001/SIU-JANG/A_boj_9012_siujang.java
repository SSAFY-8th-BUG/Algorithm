package a;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class A_boj_9012_siujang {
	
	static int TC;
	static String str, ans;
	static Stack<Character> st;

	public static void main(String[] args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			str = br.readLine();
			st = new Stack<>();
			
			int i;
			for (i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(') {
					st.add('(');
				} else {
					if (st.isEmpty()) {
						ans = "NO";
						break;
					}
					
					st.pop();
				}
			}
			
			if (i >= str.length()) {
				if (!st.isEmpty()) {
					ans = "NO";
				} else {
					ans = "YES";
				}
			}
			
			System.out.println(ans);
		}
	}
}
