package a;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class D_boj_2161_siujang {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		LinkedList<Integer> ll = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			ll.add(i);
		}
		
		while (ll.size() > 1) {
			System.out.println(ll.poll());
			
			int top = ll.poll();
			ll.addLast(top);
		}
		
		System.out.println(ll.poll());
	}
}