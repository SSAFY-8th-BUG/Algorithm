package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

// #1. 한 쌍의 괄호의 여는 index, 닫는 index를 저장하는 class
class Bracket {
	int start;
	int end;

	Bracket (int start) {
		this.start=start;
	}
}

public class BOJ2800 {
	
	static int N;
	static boolean[] removed;
	static String str;
	static List<Bracket> brList=new ArrayList<>();
	static Set<String> ret=new TreeSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
		str=br.readLine();
		N=str.length();
		removed=new boolean[N];
		
		Stack<Integer> stack=new Stack<>();
		
		int cnt=0;
		for (int i=0; i<N; i++) {
			char c=str.charAt(i);
			
			// #2. 여는 괄호가 나오면 새로운 Bracket class를 만들어 줌
			if (c=='(') {
				brList.add(new Bracket(i));
				
				//# 2-2. 현재 만든 Bracket이 몇 번 째 Bracket 인지 stack에 저장
				stack.push(cnt);
				cnt++;
			}

			// #3. 닫는 괄호가 나오면 가장 최근에 나왔던 여는 괄호와 쌍을 만들어줌
			else if (c==')') 
				brList.get(stack.pop()).end=i;
		}
		
		check(0,0);
		
		for (String s : ret)
			System.out.println(s);
		
	}
	
	//#4. Bracket 쌍을 하나씩 지워가면서 판별
	static void check (int cnt, int s) {
		// #4-2. 기저조건 -> 마지막 bracket 까지 다 확인했을 경우
		if (cnt==brList.size()) {
			
			// #4-3. 원래 주어진 식과 같은 식인지 확인
			boolean flag=false;
			
			StringBuilder sb=new StringBuilder ();
			
			for (int i=0; i<N; i++) {
				if (!removed[i]) sb.append(str.charAt(i));
				else flag=true;
			}
			
			if (flag) ret.add(sb.toString());
			return ;

		}
		
		for (int i=s; i<brList.size(); i++) {
			
			removed[brList.get(cnt).start]=true;
			removed[brList.get(cnt).end]=true;
			
			check (cnt+1, i+1);
			
			removed[brList.get(cnt).start]=false;
			removed[brList.get(cnt).end]=false;
			
			check(cnt+1, i+1);
		}
	}

}
