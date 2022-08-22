import java.io.*;

// 시간복잡도 -> O(2^N)
// 공간복잡도 -> O(2^N)

public class boj_16637_tableMinPark {
	
	static int answer;
	static int[] nums;
	static char[] oper;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		oper = new char[N / 2 + 1];
		nums = new int[N / 2 + 1];
		
		char[] input = br.readLine().toCharArray();
		nums[0] = input[0];
		for (int i = 0, j = 0; i < N; i += 2, j++) nums[j] = input[i]-'0';
		for (int i = 1, j = 1; i < N; i += 2, j++) oper[j] = input[i];

		answer = Integer.MIN_VALUE;
		recur(1, nums[0]);
		
		System.out.println(answer);
		br.close();
	}
	
	static void recur(int idx, int sum) {
		if (idx == oper.length) {
			answer = Math.max(answer, sum);
			return;
		}
		
		recur(idx + 1, calc(sum, nums[idx], oper[idx]));
		if (idx + 1 >= oper.length) return;
		recur(idx + 2, calc(sum, calc(nums[idx], nums[idx + 1], oper[idx + 1]), oper[idx]));
	}
	
	static int calc (int a, int b, char oper) {
		int result = 0;
		switch(oper) {
			case '*': result = a * b; break;
			case '+': result = a + b; break;
			case '-': result = a - b; break;
		}
		return result;
	}
}
