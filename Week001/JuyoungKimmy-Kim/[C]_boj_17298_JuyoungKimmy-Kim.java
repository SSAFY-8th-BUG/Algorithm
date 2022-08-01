import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * O(n)
 * 
 * 
 */
public class C_boj_17298_2 {

	static int N;
	static int[] intArray;
	static int[] ret;
	public static void main(String[] args) throws IOException{

		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		intArray=new int[N];
		ret=new int[N];
		Arrays.fill(ret, -1);
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<N; i++) 
			intArray[i]=Integer.parseInt(st.nextToken());
		
		Stack<Integer> stack=new Stack<>();
		
		/*
		 * 
		 * 1. Stack에는 index를 저장함
		 * 2. 배열 0번째에 저장되어 있는 숫자부터 차례대로 계산
		 * 3. 현재 탐색하는 숫자(더 오른쪽에 있는 숫자)가 스택에 들어가있는 인덱스가 가리키는 숫자 (더 왼쪽에 있는 숫자)보다
		 * 	  큰 경우 현재 숫자가 스택에 있는 인덱스가 가리키는 숫자의 오큰수
		 * 
		 */
		for (int i=0; i<N; i++) {
			
			while (!stack.isEmpty() 
					&& intArray[i]>intArray[stack.peek()]) {
				ret[stack.pop()]=intArray[i];
			}
			
			stack.add(i);
		}
		
	     StringBuilder sb = new StringBuilder();
	     for (int i=0; i<N; i++) sb.append(ret[i]).append(" ");
	     	System.out.println(sb.toString());
	}

}
