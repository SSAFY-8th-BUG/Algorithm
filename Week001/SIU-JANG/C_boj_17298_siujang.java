package a;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class C_boj_17298_siujang {
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		int[] array = new int[N];
		int[] ans = new int[N];
		Stack<Integer> s = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = N - 1; i >= 0; i--) {
			while (!s.isEmpty() && array[i] >= s.peek()) {
				s.pop();
			}
			
			if (s.isEmpty()) {
				ans[i] = -1;
			} else {
				ans[i] = s.peek();
			}
			
			s.add(array[i]);
		}
		
		for (int a : ans) {
			bw.write(a + " ");
		}
		
		bw.flush();
	}
}