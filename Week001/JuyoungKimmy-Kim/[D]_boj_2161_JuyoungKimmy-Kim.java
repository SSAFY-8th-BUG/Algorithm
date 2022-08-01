import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 먼저 queue에 모두 넣어 놓고
 * 제일 위에 있는 값부터 하나 빼고 그 다음 값을 빼서 가장 아래에 놓고
 * n번 반복
 * 
 * O(n)
 */

public class D_boj_2161 {

	public static void main(String[] args) {
		Queue<Integer> queue=new LinkedList<>();
		
		Scanner sc=new Scanner(System.in);
	    StringBuilder sb = new StringBuilder();

	     	
		int N=sc.nextInt();
		
		for (int i=1; i<=N; i++)
			queue.add(i);
		
		while (!queue.isEmpty()) {
			sb.append(queue.poll()).append(" ");
			if (!queue.isEmpty())
				queue.add(queue.poll());
		}
		
		System.out.println(sb.toString());
 
	}

}
