package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* N=9 인 경우
 * 0 1 2 3 4 5 6 7 8
 * 3 + 8 * 7 - 9 * 2
 * 
 * 추가할 수 있는 괄호쌍의 개수는 => 총 N/2-1개
 * e.g. (8*7) (7-9) (9*2) => 총3개 (제일 앞은 하나 마나)
 * 
 * 마지막으로 괄호를 추가할 수 있는 index는 N-3 (숫자 9위치)
 * 
 * (8*7) 위치에 괄호가 추가되었을 경우 8의 index인 2에 표시를 해줌 -> selected[2]=true
 * 그 후 다음 괄호가 추가될 수 있는 위치는 4증가한 6번 index
 * ===>dfs (cnt+1, idx+4);
 * 
 * (8*7) 위치에 괄호가 추가되지 않았을 경우 다음 괄호가 추가될 수 있는 위치는 2증가한 4번 index
 * ===>dfs (cnt, idx+2);
 * 
 */
public class BOJ16637 {

	static int N, bracket;
	static int ans=Integer.MIN_VALUE;
	static String str;

	static boolean[] selected;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		selected=new boolean[N];
		str=br.readLine();
		
		bracket=N/2-1; //추가할 수 있는 최대 bracket 쌍 수 (제일 처음 쌍 빼고)
		
		if (N==1) {
			System.out.println(str.charAt(0)-'0');
			return ;
		}
		
		dfs (0,2);
		System.out.println(ans);
		
	}
	private static void dfs (int cnt, int idx) {
		
		//매번 식 검사
		calc();
		
		//추가할 수 있는 최대 괄호 쌍의 개수를 넘거나, 더 이상 괄호를 추가할 수 없는 위치에 도달했을 때
		if (bracket==cnt || idx>N-3) return ;

		//bracket 추가
		selected[idx]=true;
		dfs (cnt+1, idx+4);
		
		//bracket 추가하지 않았을 때 
		selected[idx]=false;
		dfs (cnt, idx+2);
		
	}
	private static void calc () {
		
		int sum=str.charAt(0)-'0';		//첫 번째 숫자 넣고 시작함
		int i=1;
		while (true) {
			
			if (i==str.length()) break;
			
			char op=str.charAt(i++);
			int num=0;
			
			// select 되었다는 것 -> 괄호가 나왔다는 것
			// 안에 것 먼저 계산 
			if (selected[i]) {
				int n1=str.charAt(i++)-'0';
				char c=str.charAt(i++);
				int n2=str.charAt(i++)-'0';
				
				switch (c) {
				case '+' : num=n1+n2; break;
				case '-' : num=n1-n2; break;
				case '*' : num=n1*n2; break;
				}
			
			} else num=str.charAt(i++)-'0';	
			
			switch (op) {
			case '+' : sum+=num; break;
			case '-' : sum-=num; break;
			case '*' : sum*=num; break;
			}
		}
		
		if (sum>ans) ans=sum;
	}
}
