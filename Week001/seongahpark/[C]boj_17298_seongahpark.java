import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Stack<Integer> s = new Stack<>();
		int [] res = new int[1000001];
		
		String [] arr = br.readLine().split(" ");
		
		for(int i=n-1; i>=0; i--) {
			int num = Integer.parseInt(arr[i]);
			while(!s.empty() && s.peek() <= num) s.pop();
			if(s.empty()) res[i] = -1;
			else res[i] = s.peek();
			
			s.push(num);
		}
		
        // 이렇게 출력 안해주면 시간초과 뜸
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			sb.append(res[i] + " ");
		}
		System.out.println(sb);
	}

}
